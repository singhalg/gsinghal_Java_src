
public class Alignment {
	public int position;
	public int mismatch;
	
	public Alignment(int pos, int mismatch){
		this.position = pos;
		this.mismatch = mismatch;
	}
	
	public String toString(){
		return (this.position + " " + this.mismatch);
	}
}
