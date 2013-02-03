// author : Gaurav Singhal
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class Event {


	private String eventDesc;
	private int date;

	public Event(int date, String description){
		this.date = date;
		this.eventDesc = description;
	}

	public String getDescription(){
		return this.eventDesc;

	}

	public int getDate(){
		return this.date;
	}

	public String parseEvent(){


		
		StringTokenizer st = new StringTokenizer(eventDesc.toLowerCase());
		String  description = " ";

		while (st.hasMoreTokens()){
			description+= st.nextToken();
          	
		}
		return description;
	}

}
