//@author : Gaurav Singhal
public class Edge {

	Vertex head;
	Vertex tail;
	double weight;
	
	public Edge(Vertex h, Vertex t, double w ){
		this.head = h;
		this.tail = t;
		this.weight = w;
		
	}
	
	public Vertex getTail(){
		return this.tail;
		
	}
	
	public void setWeight(double newWeight){
		
		this.weight = newWeight;
	}
	
	public Vertex getHead(){
		return this.head;
	}
	
	public double getWeight(){
		return this.weight;
	}
	
	public String toString(){
		return this.head.toString() + "--" +this.weight  + "-->"+ this.tail.toString() +" ; "; 
	}
}
