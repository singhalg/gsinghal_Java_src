
public class square {
	public static double pyth (double abc, double def)
	{ 
		return Math.sqrt(sq(abc)+sq(def));
	}
	 public double h = pyth(5.0, 6.0);
	
	private static double sq(double base){
		return base*base;
	}
}
