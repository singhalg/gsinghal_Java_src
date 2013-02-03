
//============================ Comparator ======================
//
// Class so that the sort routine can be told to sort based on 
// x-coordinate or y-coordinate.  The object based to sort is of
// type Comparator which has a single function comp to compare
// two XYpoints.  This has one implmentation XComparator for
// which comp does the comparison based on the x-coordinate and 
// an implementation YComparator for which comp does the comparison 
// based on the y-coordinate

interface Comparator {
   public boolean comp(XYPoint p1, XYPoint p2);
}
