//@author : Gaurav Singhal
/**
 * This algorithm is derived from the Plane Sweep Algorithm mentioned in the 
 * chapter "Closest-Point Problems in Computational Geometry" by Michiel Smid in the    
 * Handbook of Computational Geometry. 
 * 
 *
 */


public class LineSweep {
	public static PointPair PSclosestPair(XYPoint[] points) {
		XYPoint pointsByX[] = new XYPoint[points.length];
		for (int j = 0; j < points.length; j++) {
			pointsByX[j] = points[j];
		}
		// Ensure sorting precondition for line sweep algorithm.
		// There is NO NEED to sort the points by y-coord, this saves both on memory and computations
		XComparator xcomp = new XComparator();
		Sort.msort(pointsByX, xcomp); // sort by x-coord
		return PlaneSweepClosestPair(pointsByX);
	}

	static PointPair PlaneSweepClosestPair(XYPoint[] pointsByX) {
		PointPair best = new PointPair(pointsByX[0], pointsByX[1]);  // let us assume the first pair to be best pair
		double delta = best.dist; // delta is the shortest distance
		double newDelta;
		for(int i = 0; i < pointsByX.length-1; i++){ // for each element in pointsByX array
			XYPoint current = pointsByX[i];
			XYPoint Yupper = new XYPoint(current.x, (int) (current.y+delta)); // Yupper determines the upper y-coord of search rectangle
			XYPoint Ylower = new XYPoint(current.x, (int) (current.y-delta)); // Ylower determines the lower y-coord of search rectangle
			XYPoint Xright = new XYPoint ((int)(current.x + delta), current.y);
			int j = i+1; // j starts at i+1
			while((j<pointsByX.length) && (pointsByX[j].leftof(Xright))){ // we only look at those points which lie between current.x and Xright.x
				XYPoint next = pointsByX[j];
				if(next.below(Yupper)&& !next.below(Ylower)){ // we only look at those points which lie between Yupper.x and Ylower.x
					newDelta = next.dist(current);
					if (newDelta<delta){ // if we find a new closest pair, we shorten the search rectangle
						best = new PointPair(next, current);
						delta = best.dist;
						Yupper = new XYPoint(current.x, (int) (current.y-delta)); 
						Ylower = new XYPoint(current.x, (int) (current.y+delta));
						Xright = new XYPoint ((int)(current.x + delta), current.y);
					}
				}
				j++;	
			}
		}

		return best;
	}



}