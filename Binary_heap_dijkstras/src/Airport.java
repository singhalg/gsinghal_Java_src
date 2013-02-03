//@author : Gaurav Singhal
import java.util.ArrayList;


public class Airport extends Vertex{

	public int gmtOfst;
	public GMTtime arrivalTime; // time at which we arrive at this airport; this is useful while printing the shortest path
	Airport parent ;
	ArrayList<Flight> list = new ArrayList<Flight>();
	Flight arrivingFl; // Flight which lands into this Airport ; this is useful while printing the shortest path
	
	public Airport(String name, int gmt){
		super(name);
		this.gmtOfst = gmt;
		this.parent = (Airport) super.p;
		
	}
	

	
}