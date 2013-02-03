// author : Gaurav Singhal

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.Iterator;


public class SkipListTest {


	public static void main (String args[]) throws IOException{

		SkipList newSkipList = new SkipList(1000);




		Event[] events = parseEvents(args[0]);
		for(int i = 0 ; i < events.length; i++){
			int date = events[i].getDate();
			String description = events[i].getDescription();
			newSkipList.put(date, description);
		}

		

		System.out.println(newSkipList.rangeSearch(-5000, -2000));
		System.out.println(newSkipList.rangeSearch(1776, 1776));
		System.out.println(newSkipList.rangeSearch(1945, 1945));
		System.out.println(newSkipList.rangeSearch(0,100));
		System.out.println(newSkipList.rangeSearch(1770, 1779));

		System.out.println("finding occurance of : last");
		System.out.println(newSkipList.findOccurance("last"));
		System.out.println("finding occurance of : virus");
		System.out.println(newSkipList.findOccurance("virus"));
		System.out.println("finding occurance of : root");
		System.out.println(newSkipList.findOccurance("root"));
		System.out.println("finding occurance of : pi");
		System.out.println(newSkipList.findOccurance("pi"));

		System.out.println("");
		System.out.println("Removing date 1771");
		System.out.println(newSkipList.remove(1771));
		
	
		System.out.println(newSkipList.rangeSearch(1770,1779));
		
		System.out.println("finding occurance of : dioxide");
		System.out.println(newSkipList.findOccurance("dioxide"));
		System.out.println("finding occurance of : nebulae");
		
		System.out.println(newSkipList.findOccurance("nebulae"));
		
		
		
	}



//	public static void main (String args[]) throws IOException{
//
//		SkipList newSkipList = new SkipList(100);
//
//
//
//
//		Event[] events = parseEvents(args[0]);
//		for(int i = 0 ; i < events.length; i++){
//			int date = events[i].getDate();
//			String description = events[i].getDescription();
//			newSkipList.put(date, description);
//		}
//
//
//
//		System.out.println("Testing toString method");
//		System.out.println(newSkipList.toString());
//		System.out.println();
//		System.out.println("Tesing containsKey method");
//		newSkipList.containsKey(-170);
//		System.out.println();
//		newSkipList.containsKey(105);
//		System.out.println();
//		newSkipList.containsKey(100);
//		System.out.println();
//		System.out.println();
//
//		//testing rangeSearch method
//		System.out.println("Testing reangeSearch");
//		System.out.println(newSkipList.rangeSearch(-5000, 0));
//

//
//		// testing rangeSearch after putting multiple events with the same date
//		System.out.println();
//		System.out.println("Testing rangeSearch after putting multiple events with the same date(105)");
//		System.out.println(newSkipList.rangeSearch(-300, 105));
//
//
//		System.out.println();
//		System.out.println("finding occurance of : paper");
//		System.out.println(newSkipList.findOccurance("paper"));
//		
//		
//		
//		System.out.println();
//		System.out.println("finding occurance of : and");
//		System.out.println(newSkipList.findOccurance("and"));
//
//		System.out.println("");
//		System.out.println("Removing date 1876");
//		System.out.println(newSkipList.remove(1876));
//		System.out.println();
//		System.out.println("finding occurance of : Alexander");
//		System.out.println(newSkipList.findOccurance("Alexander"));
//
//		System.out.println();
//		System.out.println("finding occurance of : watson");
//		System.out.println(newSkipList.findOccurance("watson"));
//		
//		
//		System.out.println();
//		System.out.println("Testing rangeSearch after removing the event with the date 1876");
//		System.out.println(newSkipList.rangeSearch(1400,1900 ));
//
//	}

	public static Event[] parseEvents(String filename) throws IOException{

		ArrayList<Event> events = new ArrayList<Event>();
		BufferedReader r;
		// Create a reader for the file
		//
		try {
			InputStream is = new FileInputStream(filename);
			r = new BufferedReader(new InputStreamReader(is));
		}
		catch (IOException e) {
			System.out.println("IOException while opening " + e);
			return null;
		}

		// Parse each line of the file sequentially
		//
		try {
			boolean stop = false;
			while (!stop){
				String nextline = r.readLine();
				if (nextline == null) // end of file
					stop = true;
				else {
					int tabLoc = nextline.indexOf('\t');
					String tempDate = nextline.substring(0,tabLoc);
					int Date = Integer.parseInt(tempDate);
					String description = nextline.substring(tabLoc+1,nextline.length());   

					Event newEvent = new Event(Date, description);
					events.add(newEvent);
					//		          do something with the date and description beforing moving on
				}
			}

		}
		catch (IOException e) {
			System.out.println("IOException while reading" + e);
		}

		Event [] eventsArr = new Event[events.size()];
		events.toArray(eventsArr);


		return eventsArr;
	}





}
