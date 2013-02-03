//@author : Gaurav Singhal

public class NaiveClosestPair {

	public static final double INF = java.lang.Double.POSITIVE_INFINITY; // i didn't use this

	public static PointPair naiveClosestPair(XYPoint[] points) {
		
		PointPair closestPair = new PointPair(points[0], points[1]); // lets assume that the first pair is the closest
																	// later on we'll replace this closest pair with new 
																	// new closest pair we find
		
		for(int i = 0; i < points.length-1; i++){
			for(int j = i+1; j < points.length; j++){
				PointPair newPP = new PointPair(points[i], points[j]);				
				if(newPP.dist < closestPair.dist){
					closestPair = newPP;
				}
				
			}
		}
		return closestPair;
	}
}
