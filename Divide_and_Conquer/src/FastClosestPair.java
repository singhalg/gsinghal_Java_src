//@author : Gaurav Singhal

public class FastClosestPair {

	public final static double INF = java.lang.Double.POSITIVE_INFINITY; 

	public static PointPair closestPair(XYPoint[] points) {
		XYPoint pointsByX[] = new XYPoint[points.length];
		XYPoint pointsByY[] = new XYPoint[points.length];
		for (int j = 0; j < points.length; j++) {
			pointsByX[j] = points[j];
			pointsByY[j] = points[j];
		}

		// Ensure sorting precondition for divide-and-conquer algorithm.
		XComparator xcomp = new XComparator();
		YComparator ycomp = new YComparator();
		Sort.msort(pointsByX, xcomp); // sort by x-coord
		Sort.msort(pointsByY, ycomp); // sort by y-coord

		return findClosestPair(pointsByX, pointsByY);
	}

	static PointPair findClosestPair(XYPoint[] pointsByX, XYPoint[] pointsByY) {
		if (pointsByX.length <= 3){ // base case
			return NaiveClosestPair.naiveClosestPair(pointsByX);				
		}
		else{
			// divide step  ==============================================================================

			int lenX = pointsByX.length;
			int lenXL;
			int lenXR;
			if(lenX%2 == 0){
				lenXL =  lenX/2;
				lenXR = lenX/2;
			}
			else{
				lenXL = lenX/2;
				lenXR = (lenX/2)+1;
			}
			
			XYPoint pointsByXL[] = new XYPoint[lenXL];
			XYPoint pointsByYL[] = new XYPoint[lenXL];

			XYPoint pointsByXR[] = new XYPoint[lenXR];
			XYPoint pointsByYR[] = new XYPoint[lenXR];

			for (int i = 0; i < lenX/2; i++ ){
				pointsByXL[i] = pointsByX[i];
			}
			int p = 0;
			for (int i = lenX/2; i < lenX; i++ ){
				pointsByXR[p] = pointsByX[i];
				p++;
			}

			int indexOfYL = 0;
			int indexOfYR = 0;

			for (int i = 0; i < pointsByY.length; i++){
				if (pointsByY[i].leftof(pointsByXR[0])){
					pointsByYL[indexOfYL] = pointsByY[i];
					indexOfYL++;	
				}
				else{
					pointsByYR[indexOfYR] = pointsByY[i];
					indexOfYR++;
				}
			}
			// ===== divide step complete ============================================
			
			
			// this method does the "combine" step of the algorithm
			return combine( pointsByY, pointsByXL, pointsByXR,  pointsByYL,  pointsByYR);

		}
	}


/**
 * This method makes the recursive call to findClosestPair. It performs the "Combine" step in which the closest pair of points from Pl and Pr 
 * are compared with a possible closer pair of points which might lie on the intersection of Pl and Pr.
 * @returns the closest pair of points from among Pl, Pr and in the intersection of Pl and Pr
 */
	public static PointPair combine(XYPoint[] pointsByY, XYPoint[] pointsByXL, XYPoint[] pointsByXR, XYPoint[] pointsByYL, XYPoint[] pointsByYR){

		PointPair bestL = findClosestPair(pointsByXL, pointsByYL);
		PointPair bestR = findClosestPair(pointsByXR, pointsByYR);
		PointPair bestSoFar = null;

		if (bestL.dist <= bestR.dist){
			bestSoFar = bestL; 
		}
		else{
			bestSoFar = bestR;
		}
		double delta = bestSoFar.dist;

		// creating Y'
		XYPoint[] strip = new XYPoint[pointsByY.length];

		double Pcord = pointsByXL[pointsByXL.length-1].x + ((pointsByXR[0].x - pointsByXL[pointsByXL.length-1].x)/2);

		int indexOfStrip = 0;

		for(int i = 0; i < pointsByY.length; i++){
			if( (pointsByY[i].x >= (Pcord - delta)) && (pointsByY[i].x <= (Pcord + delta))){
				strip[indexOfStrip] = pointsByY[i] ;
				indexOfStrip++;
			}
		}

		XYPoint[] newstrip = new XYPoint[indexOfStrip]; // newstrip is strip, but without the null pointers.
		for(int i = 0; i < newstrip.length; i++){
			newstrip[i] = strip[i] ;
		}


		if (newstrip.length == 0){ // if no elements in the strip
			return bestSoFar;
		}
		else{
			return bestInStrip(newstrip, bestSoFar);
		}
	}
/**
 * Helper method for searching for the closest pair in the Y' strip.
 * @param strip, an array containing all those points of Pl and Pr which lie within delta (closest distance so far) from the line dividing Pl and Pr. 
 * @param bestSoFar, the closest PointPair found so far 
 * @return best (a PointPair), if points in best are closer than BestSoFar. Else returns BestSoFar if no better PointPair is found.
 */

	public static PointPair bestInStrip(XYPoint[] strip, PointPair bestSoFar){

		double newDist;
		double delta = bestSoFar.dist;
		double newDelta = bestSoFar.dist;
		PointPair best = bestSoFar;

		for(int j = 0; j < strip.length-1; j++){
			for(int k = j+1; k < strip.length; k++){
				if(Math.abs(strip[k].y - strip[j].y) < delta){ //we look at all the points within the search rectangle of delta X 2*delta
					newDist = strip[k].dist(strip[j]);
					if(newDist<newDelta){
						newDelta = newDist;
						best = new PointPair(strip[k], strip[j]);
					}
				}
			}
		}
		return best;
	}
}


