//================================= PointPair =============================
//
// Class that holds two points (for example a candidate for the closes
//  pair of points) and their Euclidean distance

class PointPair {
	public XYPoint p1;
	public XYPoint p2;
	public double dist;

	PointPair(XYPoint p1, XYPoint p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.dist = p1.dist(p2);
	}

	public String toString() {
		return (" " + this.p1 + " and " + this.p2 + 
				" (of distance " + this.dist + ")");
	}
}
