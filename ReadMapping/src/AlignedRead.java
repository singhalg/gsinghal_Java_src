
public class AlignedRead {
	public ShortRead read;
	public Alignment align;
	double posterior;
	
	public AlignedRead(ShortRead aread, Alignment readAlignment, double post ){
		this.read = aread;
		this.align = readAlignment;
		this.posterior = post;
		
	}
	
}
