// author : Gaurav Singhal
public class ListItem {


	int date;
	String description;
	ListItem[] forward;
	
	public ListItem(int level, Event anEvent){
	
		this.date = anEvent.getDate();
		this.description = anEvent.parseEvent();
		this.forward = new ListItem[level+1]; // l = currentLevel; if height = 1, level =0 and forward.length = 1
	}
	
	
	public ListItem(int level, int date, String desc){

		this.date = date;
		this.description = desc;
		this.forward = new ListItem[level+1]; // the last of this array points to null
	}
	
	
	public int getListLevel(){
		return forward.length-1;
	}
	
	
	public int getListDate(){
		return this.date;
	}
	public String getListDesc(){
		return this.description;
	}
	
}
