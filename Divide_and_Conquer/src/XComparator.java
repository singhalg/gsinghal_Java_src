class XComparator implements Comparator {
	public boolean comp(XYPoint p1, XYPoint p2) {
		return (p1.leftof(p2));
	}
}
