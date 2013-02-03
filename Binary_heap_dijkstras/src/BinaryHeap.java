//@author : Gaurav Singhal

public class BinaryHeap {

	private static int size; // current size of the heap

	private Vertex [] array; 

	public BinaryHeap(int num){


		array = new Vertex[num];
		this.size = 0;

	}

	public BinaryHeap(){

		array = new Vertex[1000];
		this.size = 0;

	}


	public static int size(){
		return size;
	}


// Inner class
	public class Tracker{

		boolean inHeap ;
		int index;
		public Tracker(int a ){

			this.index = a;
			this.inHeap = true;
		}


		public Vertex getItem(){
			return array[this.index];
		}

		public boolean inHeap(){
			return inHeap;
		}

		public void setTo(int num){ // resets the index of tracker
			this.index = num;
		}

		public void removeFromHeap(){
			this.inHeap = false;
		}

	
		
		public boolean decreaseKey(double newKey){
			int i = this.index;
			if (newKey>array[i].d){ 
				return false;
			}
			else{
				array[i].d = newKey;
				while((i >0 )&&(array[(i-1)/2].isGreaterthan(array[i].d))){ // if parent is greater than child, swap
					swap(array[i], array[(i-1)/2], i );
					i = (i-1)/2;
				}
				return true;
			}



		}

		public String toString(){

			return Integer.toString(this.index);
		}



	}



	public BinaryHeap.Tracker put(double key, Object data) throws Exception{
		Vertex newElement = new Vertex(key, (String) data);
		return putVertex(newElement);
	}

	/**
	 * Helper method for put() method
	 * @param v
	 * @return
	 * @throws Exception
	 */
	public BinaryHeap.Tracker putVertex(Vertex v) throws Exception{

		int hole = size;
		
		if (size<array.length){

			
			v.tracker = new BinaryHeap.Tracker(size);
			array[hole] = v;
			while(  (hole > 0)  &&  (array[(hole-1)/2].isGreaterthan(v.getKey()) )   ){
				swap(array[hole], array[(hole-1)/2], hole);
				hole = (hole-1)/2;
			}			
			size++;
			
			
			return v.tracker;
		}

		else{
			throw new Exception("Heap is full");
		}

	}

	/***
	 * 
	 * @param small Vertex with smaller key
	 * @param large Vertex with larger key
	 * @param i of smaller key
	 */
	private void swap(Vertex small, Vertex large,  int i){


		array[i] = large;
		large.tracker.setTo(i);

		array[(i-1)/2] = small;
		small.tracker.setTo((i-1)/2) ;

	}


	public boolean isEmpty(){

		return size==0;
	}


	public double minimumKey(){
		return array[0].d;
	}


	// here I return the whole vertex, rather than returning its associated data
	public Vertex extractMin() throws Exception{

		if (array.length==0){
			throw new Exception("No Elements in the array");
		}
		else{
			Vertex min = array[0];
			array[0].tracker.removeFromHeap();
			array[0] = array[size-1];
			array[0].tracker.setTo(0);
			array[size-1] = null;
			size--;
			minHeapify(0);
			return min;
		}
	}

	/**
	 * rectifies the Min oriented binary queue downwards of array[ i].
	 * @param i
	 */
	public void minHeapify(int i){
		int left = (i*2)+1;
		int right = (i*2)+2;
		int smallest;

		if ((left<size)&&(array[left].d <array[i].d)){
			smallest = left;
		}
		else{
			smallest = i;
		}

		if((right<size)&&(array[right].d < array[smallest].d)){
			smallest = right;
		}

		if(smallest != i){
			swap( array[smallest], array[i], smallest);
			minHeapify(smallest);
		}
	}

	public String toString(){
		String ans = "";
		for(int i = 0; i < size; i++){
			ans+= "[" + array[i].d + ", " + array[i].name + " , Tracker = "+ array[i].tracker.toString()+ " ] ;    "; 
		}
		return ans;
	}



}
