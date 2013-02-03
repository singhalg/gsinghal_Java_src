//@author : Gaurav Singhal

public class Flight extends Edge {

	String airline;
	Airport source;
	Airport dest;
	int flightNo;
	
	
	GMTtime deptT; // departure time at source airport
	GMTtime arrT; // arrival time at destination airport
	int flightDur = 0; // not used
	

	public Flight(Airport source, Airport dest, int flightno, int locDepT,boolean depAmPm, int destArrT,  boolean arrAmPm, String airline, int flightDur ){
		super(source, dest, flightDur);
		GMTtime deptTime = new GMTtime(locDepT, source.gmtOfst, depAmPm);
		GMTtime arrTime = new GMTtime(destArrT, dest.gmtOfst, arrAmPm);
		this.weight = arrTime.minutesSince(deptTime);
		this.airline = airline;
		this.flightNo = flightno;
		this.source = source;
		this.dest = dest;
		this.arrT = arrTime;
		this.deptT = deptTime;
	}

	public void setDuration(int duration){

		double nd = duration/1.0;

		this.setWeight(nd);
	}
	
	public Airport getDestAp(){
		return this.dest;
	}

	
	public String toString(){
		
		
		return this.source.toString() + "--" +this.weight  + "-->"+ this.dest.toString() +" ; "; 
	}
	
	
	/**
	 * Special toString method for printing the Flight data in a manner required by the Lab
	 * @return
	 */
	public String toStringSp(){
		String ans = this.airline + " " + this.flightNo + "  (" + this.source.name +"  " + this.deptT.localTime +" "+ amOrpm(this.deptT.am) + 
		" --> " + this.dest.name + "  " + this.arrT.localTime +" "+ amOrpm(this.arrT.am) + ") ";
		return ans;
	}
	
	/**
	 * Helper for toStringSp() method
	 * @param ampm
	 * @return
	 */
	public String amOrpm(boolean ampm){
		if(ampm) return "AM";
		else return "PM";
	}
	
}
