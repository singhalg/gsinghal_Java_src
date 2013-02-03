
public class Item {

	public double key;
	public Object data;
	public BinaryHeap.Tracker index;
	
	public Item(double K, Object D){
		this.key = K;
		this.data = D;
		this.index = null;
	}
	public Item(double K, Object D, BinaryHeap.Tracker ind){
		this.key = K;
		this.data = D;
		this.index = ind;
	}
	
	
	
	
	public boolean isGreaterthan(double key){
		if (this.key > key){
			return true;
		}
		else return false;
	}
	
	
}
