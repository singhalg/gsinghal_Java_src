import java.awt.*;

//============================== XYPoint ============================
//
// Definition for the XYpoint class.
//
// The variable numpts is a static integer that keeps track of the
//  number of points objects.  Each point has an x-coord, y-coord and
//  a integer variable num which is the current value of numpts (and
//  then numpts is incremented).  The role of num is to consistently
//  break any ties when comparing two points with the same coordinate.
//  For example if there is the point p1=(5,10) with num = 7 and the
//  point p2=(5,4) with num = 2 and they are compared according to
//  their x-coordinates (via the function leftof) then p2 will be
//  reported as leftof p1 since it's number is smaller.  Likewise
//  when using below to compare according to their y-coordinates.

public class XYPoint {
	static int numpts = 0;

	public int x; // internal rep is two integers, x for the

	public int y; //  x-coord and y for the y-coord

	int num;

	public Color color;

	XYPoint() { //constructor that takes no arguments
		x = 0; //  initializes x-coord and y-coord to 0
		y = 0;
		color = Color.black; //  and color to black
		num = numpts++;
	}

	XYPoint(int xcoord, int ycoord) {
		x = xcoord; //initializes x-coord and y-coord to 
		y = ycoord; //  given values and the
		color = Color.black; //  color to black
		num = numpts++;
	}

	public double dist(XYPoint p) { // computes Euclidean distance to p
		double deltax = (x - p.x);
		double deltay = (y - p.y);
		return Math.sqrt(deltax * deltax + deltay * deltay);
	}

	public boolean leftof(XYPoint p) {
		return ((x < p.x) || ((x == p.x) && (num < p.num)));
	}

	public boolean below(XYPoint p) {
		return ((y < p.y) || ((y == p.y) && (num < p.num)));
	}
	
	

	public String toString() {
		return ("(" + x + "," + y + ")");
	}
}
