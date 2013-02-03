package lab9;

import java.util.NoSuchElementException;


/**
 * Implements a Map as an array of (key,value) pairs.
 * CSE131 Lab 9
 * Date:
 */


public class ArrayMap <K extends Comparable<K>,V> implements Map<K,V>{
	Pair<K,V>[] Element ;
	int max;

	public ArrayMap(){
		Element = (Pair<K,V>[]) new Pair[100];
		max =0;
	}


	/**
	 * 
	 * @param ll lower bound index of the search space
	 * @param ul upper bound index of the search space
	 * @param key, which is being compared
	 * @return ll, if the key is smaller than the smallest key in the array
	 * 		   ul, if the key is larger than the largest key in the array
	 * 			else, returns the key where the value has to be inserted
	 */
	private int bsPut(int ll, int ul, K key, V value){


		/**
		 *  min := 1;
			max := N; {array size: var A : array [1..N] of integer}
			repeat
		mid := (min + max) div 2;
		if x > A[mid] then
			min := mid + 1
		else 

			max := mid - 1;
			until (A[mid] = x) or (min > max);
		 */


		int mid = (ll+ul)/2;
		while(ll<=ul){
			mid = (ul+ll)/2;
			if(key.compareTo(Element[mid].Key) == 0){
				Element[mid].Value = value;
				return -2;
			}
			else if(key.compareTo(Element[mid].Key) > 0){
				ll = mid+1;

			}
			else{
				ul = mid-1;
			}

		}

		return ul;

		// in all cases, the key is to be inserted betweem max and min. Here, max < min.
		// insert after max
	}





	/**
	 * Inserts a key value pair after the supplied index in the array.
	 * @param index
	 * @param key
	 * @param value
	 */
	private void insert(int index, K key, V value ){
		int i = this.max-1; 
		while (i>index){
			Element[i+1] = Element[i];
			i--;
		}
		Element[index+1] = new Pair<K, V> (key, value);
	}







	public void put(K key, V value) {
		int ll = 0;
		int ul = this.max-1;

		if(ul<0){
			Element[0]= new Pair<K,V> (key, value);
		}

		else if(this.bsPut(ll, ul, key, value)== -2){
			//do nothing
		}

		else{
			insert(this.bsPut(ll, ul, key, value), key, value);
			max++;
		}

	}
	
	private int bsGet(K key){
	int ll = 0;
	int ul = max-1;
		int mid = (ll+ul)/2;
		while(ll<=ul){
			mid = (ul+ll)/2;
			if(key.compareTo(Element[mid].Key) == 0){
				
				return mid;
			}
			else if(key.compareTo(Element[mid].Key) > 0){
				ll = mid+1;

			}
			else{
				ul = mid-1;
			}
	}
		return 0;
	}
	
	public V get(K key) {
		if(bsGet(key)==-2)
			return Element[bsGet(key)].Value;
		else
			return Element[bsGet(key)].Value;
			
	}

	public boolean contains(K key){
		return true; 
	}

	public boolean remove(K key){
		return true;
	}


	



	public String toString() {
		String ans = "";
		for (int i=0; i < max-1; i++) {
			ans = ans +"("+ Element[i].Key+","+ Element[i].Value +")"+ "\t";
			}
		return ans;
	
}


public String toStringAr(Pair<K,V>[] arrayMap, int i){


	if(i==max-1)
		return "";

	else
		return "("+ arrayMap[i].Key + "," + arrayMap[i].Value + ")" + "-->" + toStringAr(arrayMap, i+1);

}
public String toStringArray(){
	int i = 0;
	while(i<max-1){
		System.out.println("("+ Element[i].Key + "," + Element[i].Value + ")" + "-->");
		i++;}

	return "";
}


}
