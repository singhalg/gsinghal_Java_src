//@author : Gaurav Singhal

// This assumes the following classes and methods.  You may modify this as needed.
//   
// Vertex Class
//   constructor that takes a single input which is the name for the vertex
//   a toString method that uses the name provided as input to the constructor
//
// Edge Class
//   constructor that creates an edge defined by a head, tail and weight.  For example
//   for the edge from K -> J, K is the head, J is the tail, and the weight is 3.5
//
// Graph Class
//   constructor that takes no arguments and creates an empty graph
//   addVertex(Vertex v) that adds vertex v to the graph without any edges
//   addEdge(Vertex head,Vertex tail, double weight) adds the head head->tail with
//                      the given weight to the graph
//   numVertices() returns the current number of vertices
//   numEdges() returns the current number of edges
//   toString outputs for each vertex a list of adjacent edges (with the weight)
//         you should use the same method to do this here as you will use when
//         implementing Part 3


/**
 * I also wrote another class called GraphEx especially for handling Flight and Airport classes. 
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
public class GraphTester {
	public static final double INF = Double.MAX_VALUE;
	public static HashMap<String, Airport> nodes = new HashMap<String, Airport>();

	public static void main(String args[])throws Exception {

	// ==== Driver for simple Graph=============
		Terminal.startTranscript("graphTester-output");
		Terminal.readInputFromFile("graph-data.txt");
		HashMap vertices = new HashMap();
		int n = Terminal.readInt();
		int m = Terminal.readInt();
		Graph g = new Graph();
		for (int i=0; i < n; i++){
		    String name = Terminal.readWord();
		    Vertex v = new Vertex(name);
		    vertices.put(name,v);
		    g.addVertex(v);
		}
		for (int j=0; j < m; j++) {
		    Vertex head = (Vertex) vertices.get(Terminal.readWord());
		    Vertex tail = (Vertex) vertices.get(Terminal.readWord());
		    double w = Terminal.readDouble();
		    Edge e = new Edge(head,tail,w);
		    g.addEdge(e);
		}

		Terminal.println("The graph has " + g.numVertices() + " vertices and " +
				 g.numEdges() + " edges.");
		Terminal.println("Adjacencies with weight given in parentheses are as follows:");
		Terminal.println("------------------------------------------------------------");
		Terminal.println(""+g);
		Terminal.stopTranscript();


//		String s = "B";
//		String d = "G";
//		System.out.println("Source Vertex : "+s);
//		System.out.println("Destination Vertex : " + d);
//		System.out.println( "Shortest Path from " + s +" to " + d + " : " );
//		Vertex sC = (Vertex) vertices.get(s);
//		Vertex dT = (Vertex) vertices.get(d);
//		
//		System.out.println(dijkstraSimple(g,sC, dT));
//
//		System.out.println();
		

		// ====== Driver  for Flight data =====================
		GraphEx gx = new GraphEx();

		ArrayList<Airport> airports = readAirportData();
		ArrayList<Flight> flights = readFlightData();


		Iterator<Airport> airportIter = airports.iterator();
		while(airportIter.hasNext()){
			Airport anAirport = airportIter.next();
			gx.addVertex(anAirport);
		}

		Iterator<Flight> flightIter = flights.iterator();
		while(flightIter.hasNext()){
			Flight aFlight = flightIter.next();

			gx.addEdge(aFlight);
		}


		String sc = "PHX";
		String dt = "PVD";
		
		Airport Sc = nodes.get(sc);
		Airport Dt = nodes.get(dt);
		int arrt = 600;
		boolean aorp = false;

		System.out.println(dijkstra(gx, Sc, arrt, Dt, aorp));


	}

	// dijkstra for the simple graph provided in part 2
	/**
	 * This is am implementation of Dijkstra's algorithm for the simple Graph made of Vertices and Edges. 
	 * Given a weighted graph and a source Vertex and destination vertex, it calculates the shorted path from source to 
	 * to every other Vertex
	 * Finally, shortestPath() method returns the String representation of shortest path from source to dest
	 */
	public static  String dijkstraSimple(Graph g, Vertex source, Vertex destination) throws Exception{

		Iterator<Vertex> vertices = g.vertexIterator();
		BinaryHeap h = new BinaryHeap(1000); 
		BinaryHeap.Tracker[] trackers = new BinaryHeap.Tracker[1000];

		int i = 0;
		int t = 0;
		while(vertices.hasNext()){
			Vertex v = vertices.next();
			v.d = INF;
			v.p = null;
			trackers[i] = h.putVertex(v);
			if(v.name == source.name){
				//System.out.println( "source name = " +v.name);
				t = i; // save the index of tracker, if the source Vertex is found
			}

			i++;
		}


		trackers[t].decreaseKey(0); // resetting the weight of source vertex to 0

		while(!h.isEmpty()){

			double tag = h.minimumKey();
			if(tag==INF){
				return shortestPath(g, source, destination);
			}
			Vertex u =  h.extractMin();




			Iterator<Edge> adjU = g.outEdges(u); 

			while(adjU.hasNext()){
				Edge e = adjU.next();
				if((e.getTail().tracker.inHeap)&&(e.getTail().tracker.decreaseKey(u.d + e.weight))){
					e.getTail().p = u;
				}
			}

		}

		System.out.println("printing graph after dijkstra \n"+g.toString());

		return shortestPath(g, source, destination);

	}

	/**
	 * 
	 * This is an implementation of Dijkstra's algorithm for calculating shortest path from a single source Airport
	 * to all other Airports. Finally, the method shortestPath prints the shortest path from source Airport to destination airport. 
	 * @param g
	 * @param source Airport
	 * @param arrivalTime at source Airport
	 * @param destination Airport
	 * @param AorP AM or PM for arrivalTime
	 * @return String representation of shortest path from source to destination
	 * @throws Exception
	 */
	public static String dijkstra(GraphEx g, Airport source, int arrivalTime, Airport destination, boolean AorP ) throws Exception{

		GMTtime locArrTime = new GMTtime(arrivalTime, source.gmtOfst, AorP );


		Iterator<Airport> vertices =  g.airportIterator();
		BinaryHeap h = new BinaryHeap(10000); // Q
		BinaryHeap.Tracker[] trackers = new BinaryHeap.Tracker[10000];

		int i = 0;
		int t = 0;
		while(vertices.hasNext()){
			Airport v = vertices.next();
			v.d = INF;
			v.parent = null;
			trackers[i] = h.putVertex(v);
			if(v.name == source.name){

				t = i; // save the index of tracker, if the source Vertex is found
			}

			i++;
		}



		trackers[t].decreaseKey(0); // resetting the d of source Airport to 0


		double tag1 = h.minimumKey();
		if(tag1==INF){
			return shortestPath(g, source, destination, locArrTime);
		}
		Airport sourceAp =  (Airport) h.extractMin();

		Iterator<Flight> adjS = g.outEdges(sourceAp); 

		while(adjS.hasNext()){
			Flight e = adjS.next();
			if(e.deptT.minutesSince(locArrTime)>60){ // considering only those flights which leave the airport 60 minutes after the arrival time at the source airport
				if((e.getDestAp().tracker.inHeap)&&(e.getDestAp().tracker.decreaseKey(sourceAp.d + e.weight + e.deptT.minutesSince(locArrTime)))){
					e.getDestAp().parent = sourceAp;
					e.getDestAp().arrivalTime = e.arrT; // useful for shortestPath method
					e.getDestAp().arrivingFl = e; // useful for shortestPath method
				}
			}

		}
		while(!h.isEmpty()){

			double tag = h.minimumKey();
			if(tag==INF){
				return "no connectivity " ;//there are no further flights to destination
			}
			Airport u =  (Airport) h.extractMin();

			Iterator<Flight> adjU = g.outEdges(u); 

			while(adjU.hasNext()){
				Flight e = adjU.next();


				if(e.deptT.minutesSince(u.arrivalTime)>60){ // considering only those flights which leave the airport 60 minutes after the arrival time at the source airport
					if((e.getDestAp().tracker.inHeap)&&(e.getDestAp().tracker.decreaseKey(u.d + e.deptT.minutesSince(u.arrivalTime) +e.weight ))){
						e.getDestAp().parent = u;
						e.getDestAp().arrivalTime = e.arrT;
						e.getDestAp().arrivingFl = e;
					}
				}
			}

		}
		return shortestPath(g, source, destination, locArrTime);
	}



	/**
	 * This prints the shortest path for simpleDijkstra method
	 * @param g
	 * @param s
	 * @param d
	 * @return
	 */

	public static String shortestPath(Graph g, Vertex s, Vertex d){

		String ans = "";

		Vertex curr  = d;
		do{
			ans = curr.toString() + " --> "+ ans;
			curr = curr.p;
		}while(curr.d != 0);
		return s.toString() + " --> " + ans;
	}


	/**
	 * This prints the shortest path for dijkstra method (for real flights data)
	 * For this method, I store an arriving flight in each Airport. So, starting with the destination airport, 
	 * it prints the flight arriving to the destination airport, and then moves to the parent airport . Thus iteratively 
	 * prints the connecting flights untill it reaches the source airport. 
	 * 
	 * It calls a special method toStringSp() on each flight, that prints the flight data in a manner suggested in the Lab 4 hand-out.
	 * @param g
	 * @param s
	 * @param d
	 * @param locArrTime
	 * @return
	 */
	public static String shortestPath(GraphEx g, Airport s, Airport d, GMTtime locArrTime){

		String am = "PM";
		if(locArrTime.am){
			am = "AM";
		}

		int totalTime = d.arrivalTime.minutesSince(locArrTime);
		int minutes = totalTime%60;
		int hours = totalTime/60;

		// first 3 lines of output
		String sd = "Source Airport Code : " + s.name + "\n" +
		"Arrvial time at Airport : " + locArrTime.localTime+ " "+ am + "\n"+
		"Destination Airport Code : " + d.name + "\n";

		// printing elapsed time
		String elapsedTime = "Elapsed time since " + s.name + " airport arrival to " + d.name + " arrival is "
		+ hours + " hours and " + minutes + " minutes. " +"\n" + "Itinerary : \n";

		String ans = "";
		Airport curr  = d;
		do{
			ans = curr.arrivingFl.toStringSp() + "\n"+ ans;
			curr = curr.parent;
		}while(curr.arrivingFl != null);

		return  sd + elapsedTime + ans;

	}


	/*
	 * Reader for Airport data
	 */
	public static ArrayList<Airport> readAirportData(){
		ArrayList <Airport> airports = new ArrayList<Airport>();

		BufferedReader r = null;
		try {
			InputStream is = new FileInputStream("airport-data.txt");
			r = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			System.out.println("IOException while opening airport-data.txt\n" + e);
			//return airports;
		}
		try {
			String nextline = r.readLine();
			StringTokenizer st = new StringTokenizer(nextline);
			int numAirports = Integer.parseInt(st.nextToken());
			// numAirports is the number of airports, use it as needed
			for (int i = 0; i < numAirports; i++){
				nextline = r.readLine();
				st = new StringTokenizer(nextline);
				String airportCode = st.nextToken();
				int gmtConv = Integer.parseInt(st.nextToken());
				Airport a = new Airport(airportCode, gmtConv);
				airports.add(a);
				nodes.put(airportCode, a);

			}


		} catch (IOException e) {
			System.out.println("IOException while reading sequence from " +
					"airport-data.txt\n" + e);

		}
		return airports ;
	}


	/*
	 * Reader for Flight data
	 */
	public static ArrayList<Flight> readFlightData(){
		ArrayList <Flight> flights = new ArrayList<Flight>();
		BufferedReader r = null;
		try {
			InputStream is = new FileInputStream("flight-data.txt");
			r = new BufferedReader(new InputStreamReader(is));
		} catch (IOException e) {
			System.out.println("IOException while opening flight-data.txt\n" + e);

		}
		try {
			String nextline = r.readLine();
			while (nextline != null && !nextline.trim().equals("")){ // not end of file or an empty line
				StringTokenizer st = new StringTokenizer(nextline);
				String airline = st.nextToken();
				int flightNum = Integer.parseInt(st.nextToken());
				String sourceAp = st.nextToken();
				int localDepartTime = Integer.parseInt(st.nextToken()); 
				boolean Depam = st.nextToken().equals("A");
				String destAp = st.nextToken();
				int localArriveTime = Integer.parseInt(st.nextToken());
				boolean Arram = st.nextToken().equals("A");
				nextline = r.readLine();

				Airport s = nodes.get(sourceAp);
				Airport d = nodes.get(destAp);

				Flight a = new Flight(s, d, flightNum, localDepartTime, Depam, localArriveTime,  Arram, airline, 0);
				flights.add(a);
			}
		}
		catch (IOException e) {
			System.out.println("IOException while reading sequence from " +
					"flight-data.txt\n" + e);

		}
		return flights;

	}




}
