import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;


public class Dijkstra {
	public static final double INF = Double.MAX_VALUE;
	public static HashMap<String, Airport> nodes = new HashMap<String, Airport>();


	public static void main(String args[]) throws Exception{
		

		GraphEx g = new GraphEx();
		ArrayList<Flight> flights = readFlightData();
		ArrayList<Airport> airports = readAirportData();



		Iterator<Airport> airportIter = airports.iterator();
		while(airportIter.hasNext()){
			Airport anAirport = airportIter.next();
			g.addVertex(anAirport);
		}

		Iterator<Flight> flightIter = flights.iterator();
		while(flightIter.hasNext()){
			Flight aFlight = flightIter.next();
			//System.out.println(aFlight.toString());
			g.addEdge(aFlight);
		}

		System.out.println(g.toString());


		
		String s = "BOS";
		String d = "HOU";
		Airport source = nodes.get(s);
		Airport dest = nodes.get(d);
		int arrt = 1100;
		System.out.println(dijkstra(g, source, arrt, dest));

	}

	//Flight a = new Flight(s, d, flightNum, localDepartTime, Depam, localArriveTime,  Arram, airline, 0);

	public static String dijkstra(GraphEx g, Airport source, int arrivalTime, Airport destination ) throws Exception{

		Iterator<Airport> vertices =  g.airportIterator();
		BinaryHeap h = new BinaryHeap(10000); // Q
		BinaryHeap.Tracker[] trackers = new BinaryHeap.Tracker[10000];

		int i = 0;
		int t = 0;
		while(vertices.hasNext()){
			Airport v = vertices.next();
			v.d = INF;
			v.p = null;
			trackers[i] = h.putVertex(v);
			if(v.name == source.name){
				//System.out.println( "source name = " +v.name);
				t = i; // save the index of tracker, if the source Vertex is found
			}

			i++;
		}


		

		System.out.println("============================");
		System.out.println("Graph before setting source to 0");
		System.out.println(g.toString());

		trackers[t].decreaseKey(0); // resetting the d of source vertex to 0
		System.out.println("vcv"+trackers[t]);

		System.out.println("============================");
		System.out.println("Graph after setting source to 0");
		System.out.println(g.toString());
		
		
		double tag1 = h.minimumKey();
		if(tag1==INF){
			shortestPath(g, source, destination);
		}
		Airport sourceAp =  (Airport) h.extractMin();

		Iterator<Flight> adjS = g.outEdges(sourceAp); 

		while(adjS.hasNext()){
			Flight e = adjS.next();

			if(e.deptT.localTime > arrivalTime+60){
				System.out.println(e.toString());
				System.out.println(e.getTail());
				
				Vertex a = e.getTail();
				
				System.out.println(a.tracker.index);
				System.out.println(e.dest.tracker.inHeap());
				System.out.println(e.getTail().tracker.decreaseKey(sourceAp.d + e.weight));
				if(e.getTail().tracker.decreaseKey(sourceAp.d + e.weight)){
					e.getTail().p = sourceAp;
				}
			}

		}
		while(!h.isEmpty()){

			double tag = h.minimumKey();
			if(tag==INF){
				shortestPath(g, source, destination);
			}
			Airport u =  (Airport) h.extractMin();

			Iterator<Flight> adjU = g.outEdges(u); 

			while(adjU.hasNext()){
				Flight e = adjU.next();


				if((e.getTail().tracker.inHeap)&&(e.getTail().tracker.decreaseKey(u.d + e.weight + 60))){
					e.getTail().p = u;
				}
			}



			System.out.println("printing graph after dijkstra \n"+g.toString());



		}
		return shortestPath(g, source, destination);
	}






	public static  String dijkstraSimple(Graph g, Vertex source, Vertex destination) throws Exception{

		Iterator<Vertex> vertices = g.vertexIterator();
		BinaryHeap h = new BinaryHeap(1000); // Q
		BinaryHeap.Tracker[] trackers = new BinaryHeap.Tracker[1000];

		int i = 0;
		int t = 0;
		while(vertices.hasNext()){
			Vertex v = vertices.next();
			v.d = INF;
			v.p = null;
			trackers[i] = h.putVertex(v);
			if(v.name == source.name){
				System.out.println( "source name = " +v.name);
				t = i; // save the index of tracker, if the source Vertex is found
			}

			i++;
		}
		System.out.println("============================");
		System.out.println("Graph before setting source to 0");
		System.out.println(g.toString());

		trackers[t].decreaseKey(0); // resetting the d of source vertex to 0

		System.out.println("============================");
		System.out.println("Graph after setting source to 0");
		System.out.println(g.toString());

		//int j = 0;
		while(!h.isEmpty()){

			double tag = h.minimumKey();
			if(tag==INF){
				shortestPath(g, source, destination);
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

	public static String shortestPath(Graph g, Vertex s, Vertex d){

		String ans = "";

		Vertex curr  = d;
		do{
			ans = curr.toString() + "-->"+ ans;
			curr = curr.p;
		}while(curr.d != 0);
		return s.toString() + " --> " + ans;


	}


	public static String shortestPath(GraphEx g, Airport s, Airport d){

		String ans = "";

		Vertex curr  = d;
		do{
			ans = curr.toString() + "-->"+ ans;
			curr = curr.p;
		}while(curr.d != 0);
		return s.toString() + " --> " + ans;


	}


	public static String printPath(Graph g, Vertex source, Vertex dest){
		String ans = "";
		if (dest.name == source.name){
			ans+= source.toString();
		}
		else if (dest.p == null){
			ans = "No path from " + source.toString() + " to " + dest.toString();
		}
		else {
			printPath(g, source, dest.p);
			ans+= dest.toString();
		}
		return ans;
	}

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
			//return airports;

		}
		return airports ;
	}


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
				//System.out.println(a);
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
