import java.util.ArrayList;
import java.util.Iterator;

public class MappingRecord {

	ArrayList<Object> bucket;
	String K;
	
	MappingRecord(String key, Object data){
		this.bucket = new ArrayList<Object>();
		this.K = key;
		this.bucket.add(data);
	}
	
	public void add(Object data){
		this.bucket.add(data);
	}
	
	public Iterator<Object> iter(){
		
		return this.bucket.iterator();
	}
	
}
