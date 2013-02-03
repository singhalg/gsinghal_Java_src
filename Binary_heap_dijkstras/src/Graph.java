//@author : Gaurav Singhal

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Graph {

	int numVertex;
	int numEdge;
	HashMap<String, Vertex> vertices = new HashMap<String, Vertex>();
	
	
	public Graph(){
		this.numVertex = 0;
		this.numEdge = 0;
		
	}
	
	public void addVertex(Vertex v){
		vertices.put(v.name, v);
		numVertex++;
	}
	
	public void addEdge(Edge e){
		
		String airportKey = e.getHead().name;
		
		vertices.get(airportKey).list.add(e);
		
	}
	
	
	
	
	
	public int numVertices(){
		return numVertex;
	}
	
	public int numEdges(){
		return numEdge;
	}
	
	public Iterator<Edge> outEdges(Vertex v){
		
		String airportKey = v.name;
		
		ArrayList<Edge> outEdges = vertices.get(airportKey).list;
		
		
		return outEdges.iterator();
	}
	
	public Iterator<Vertex> vertexIterator(){
		return vertices.values().iterator();
		
	}
	
	
	public String toString(){
		String ans = "";
		Iterator<Vertex> v = vertexIterator();
		while(v.hasNext()){
			Vertex vert = v.next();
			ans+= "Vertex " + vert.toString() + "[ " + vert.d+ "]has following edges : " ; 
			Iterator<Edge> edges =   outEdges(vert);
			while(edges.hasNext()){
				ans+= edges.next().toString();
			}
			ans+= "\n";
		}
		
		return ans;
	}
	
	public String toStringSp(){
		String ans = "";
		Iterator<Vertex> v = vertexIterator();
		while(v.hasNext()){
			Vertex vert = v.next();
			ans+= "Vertex " + vert.toString() + "[ " + vert.d+ " ] " + " has the following edges :" ; 
			Iterator<Edge> edges =   outEdges(vert);
			while(edges.hasNext()){
				ans+= edges.next().toString();
			}
			ans+= "\n";
		}
		
		return ans;
	}
}
