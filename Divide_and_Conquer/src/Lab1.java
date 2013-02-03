import java.util.Date;
import java.util.Scanner;
import java.awt.*;

//=================================== Lab1 ===============================
//
// Input: n the number of points
//
// Output: A pair of closest points 
//
//========================================================================

public class Lab1 {
	public static void main(String[] args) {
//				System.out.println("How many points? ");
//				Scanner sc = new Scanner(System.in);
//				int n = sc.nextInt();
//				if (n < 2) {
//					System.out.println("At least 2 points are needed.");
//					return;
//				}

				int m = 25;
				int n;
				
		// Use this line if you want a different random set of points for each
		//   run.  What this does is use the system clock as the seed to the
		//   random number generator.  However, for your submitted output
		//   use 3.14 * n as the seed as it is currently done. 
		//
		// java.util.Random randseq = new java.util.Random();
		//for (double i = 0; i< 25; i++){
			n = 1310;
				//(int) (m * (Math.pow(2.0,i)));

			java.util.Random randseq = new java.util.Random((long) 3.14 * n);
			System.out.println("With " + n + " points,");
			System.out.println("   For the first random point generator:");
			runAlgs(generateRandomPoints1(n, randseq));
//			System.out.println("   For the second random point generator:");
//			runAlgs(generateRandomPoints2(n, randseq));
		}
	//}

	static void runAlgs(XYPoint[] points) {
		int n = points.length;
		if (n <= 20) {
			System.out.println("   --- The points are:");
			for (int i = 0; i < n; i++)
				System.out.println("         " + points[i]);
			System.out.println("");
		}

//		Date startTime = new Date(); //start clock since data is created
//		PointPair closest = NaiveClosestPair.naiveClosestPair(points);
//		Date endTime = new Date(); //stop clock since closest pair computed
//		System.out.println("   --- The elapsed time for the brute force algorithm is "
//				+ (endTime.getTime() - startTime.getTime()) + " milliseconds,");
//		System.out.println("           and the answer is " + closest);
//		System.out.println("");

		Date startTime = new Date(); //start clock since data is created
		PointPair closest = FastClosestPair.closestPair(points);
		Date endTime = new Date(); //stop clock since closest pair computed
		System.out.println("   --- The elapsed time for divide and conquer is "
				+ (endTime.getTime() - startTime.getTime()) + " milliseconds,");
		System.out.println("           and the answer is " + closest);
		System.out.println("");

		 startTime = new Date(); //start clock since data is created
		 closest = LineSweep.PSclosestPair(points);
		 endTime = new Date(); //stop clock since closest pair computed
		System.out.println("   --- The elapsed time for line sweep algorithm is "
				+ (endTime.getTime() - startTime.getTime()) + " milliseconds,");
		System.out.println("           and the answer is " + closest);
		System.out.println("");
		
		//		Illustrates the use of the Plotter -- this is to help you debug.  You don't
		//          need to submit any output from the Plotter.
		if (closest != null && closest.p1 != null)
			closest.p1.color = Color.red;
		if (closest != null && closest.p2 != null)
			closest.p2.color = Color.red;
		if (n <= 20)
			new Plotter(points, true, "Point Set");
		else if (n <= 1000)
			new Plotter(points, false, "Point Set");

	}

	static XYPoint[] generateRandomPoints1(int nPoints, java.util.Random randseq) {
		XYPoint points[] = new XYPoint[nPoints];
		double x = 0.0;
		double y = 0.0;
		double step = Math.sqrt(nPoints);
		for (int j = 0; j < nPoints; j++) {
			x += 2.715 * Math.abs(randseq.nextGaussian());
			y += step * (randseq.nextDouble() + 1);
			y = y % nPoints;
			points[j] = new XYPoint((int) Math.round(x), (int) Math.round(y));
		}
		return points;
	}

	static XYPoint[] generateRandomPoints2(int nPoints, java.util.Random randseq) {
		XYPoint points[] = new XYPoint[nPoints];
		double x = 0.0;
		double y = 0.0;
		double step = Math.sqrt(nPoints);
		for (int j = 0; j < nPoints; j++) {
			y += 2.715 * Math.abs(randseq.nextGaussian());
			x += step * (randseq.nextDouble() + 1);
			x = x % nPoints;
			points[j] = new XYPoint((int) Math.round(x), (int) Math.round(y));
		}
		return points;
	}
}
