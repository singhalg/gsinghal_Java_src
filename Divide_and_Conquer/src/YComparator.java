class YComparator implements Comparator {
	public boolean comp(XYPoint p1, XYPoint p2) { //compares p1 and p2 based on y-coord
		return (p1.below(p2));
	}
}
