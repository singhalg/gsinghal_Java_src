//@author : Gaurav Singhal
import java.util.ArrayList;


public class Vertex {

	String name;
	double d; // distance from source
 	Vertex p; // predecessor
	ArrayList<Edge> list = new ArrayList<Edge>();
	public BinaryHeap.Tracker tracker;
	
	public Vertex(String n){
		this.name = n;
		this.d = 0;
		this.p = null;
		
	}
	
	
	public String toString(){
		return this.name;
	}
	

	
	
	public Vertex(double key, String data){
		this.d = key;
		this.name = data;
		this.tracker = null;
	}
	public Vertex(double K, String Data, BinaryHeap.Tracker ind){
		this.d = K;
		this.name = Data;
		this.tracker = ind;
	}
	
	public double getKey(){
		return this.d;
	}
	
	public Vertex getPredecessor(){
		return this.p;
	}
	
	public boolean isGreaterthan(double key){
		if (this.d > key){
			return true;
		}
		else return false;
	}
	
	
	
}
