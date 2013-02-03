public class Sort {

	//=================================== sort =================================
	//
	// Input: array A of XYPoints refs 
	//        compfunc is the function used to compare points
	//
	// Output: Upon completion, array A is sorted (using compfunc)
	//===========================================================================

	public static void msort(XYPoint[] A, Comparator compfunc) {
		XYPoint[] temp = new XYPoint[A.length]; // allocate space for temp array
		mergeSort(A, temp, 0, A.length - 1, compfunc); // call mergeSort
	}

	//============================= mergeSort ==============================
	//
	// Input: array of XYPoints refs A 
	//        array temp used for the merging
	//        integers l and r
	//        compfunc is the function used to compare points
	//
	// Output: Upon completion, the portion of the array A from A[l] to A[r] 
	//           is sorted based on compfunc
	//=======================================================================

	static void mergeSort(XYPoint[] A, //array of indices of sorted order
			XYPoint[] temp, //temporary array for merging
			int l, int r, //sort portion from index l to index r
			Comparator compfunc) { //function to compare two pts
		int i, //index of current location in left half of A
		j, //index of current location in right half of A
		k, //index of current location in temp array
		mid; //index of the last element of the left half of A

		if (l == r)
			return;
		mid = (l + r) / 2; //find the middle
		mergeSort(A, temp, l, mid, compfunc); //recursively sort the left half
		mergeSort(A, temp, mid + 1, r, compfunc); //recursively sort the right half

		//  Now merge the 2 halves of A, until reaching the end of one

		i = k = l;
		j = mid + 1;
		while (i <= mid && j <= r) {
			if (compfunc.comp(A[i], A[j])) { //use compfunc to compare the two points
				temp[k] = A[i];
				i++;
			} else {
				temp[k] = A[j];
				j++;
			}
			k++;
		}

		//  If there are any elements from the left half, copy them to the temp array

		while (i <= mid) {
			temp[k] = A[i];
			i++;
			k++;
		}

		// Now copy temp tempack into A (except those that we know are already there)

		for (int index = l; index <= j - 1; index++)
			A[index] = temp[index];

		return;
	}
}
