//@author : Gaurav Singhal
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Special class for handling Flights and Airports; since this is not an extension of Graph class, I rewrote a few 
 * methods here.
 * @author Gaurav
 *
 */

public class GraphEx {


	int numAirport;
	int numFlight;
	HashMap<String, Airport> verticesAp = new HashMap<String, Airport>();


	public GraphEx(){
		this.numAirport = 0;
		this.numFlight = 0;

	}

	public void addVertex(Airport v){
		verticesAp.put(v.name, v);
		numAirport++;
	}

	public void addEdge(Flight e){

		String airportKey = e.getHead().name;

		verticesAp.get(airportKey).list.add(e);

	}

	public int numAirports(){
		return numAirport;
	}

	public int numFlight(){
		return numFlight;
	}

	
	public Iterator<Flight> outEdges(Airport v){

		String airportKey = v.name;

		ArrayList<Flight> outEdges = verticesAp.get(airportKey).list;


		return outEdges.iterator();
	}

	
	public Iterator<Airport> airportIterator(){
		return verticesAp.values().iterator();

	}


	public String toString(){
		String ans = "";
		Iterator<Airport> v = airportIterator();
		while(v.hasNext()){
			Airport vert = v.next();
			ans+= "Airport " + vert.toString() + "[ " + vert.d+ "]has following flights : " ; 
			Iterator<Flight> edges =   outEdges(vert);
			while(edges.hasNext()){
				ans+= edges.next().toString();
			}
			ans+= "\n";
		}

		return ans;
	}


}

