//TA Grade 080/100
//TA Re
//TA Ext1 0/1
//TA Ext2 0/2

//TA you should work on your style. you lost 15 points to style problems alone

package lab7;

/**
 * 
 * @author Gaurav Singhal
 * @version 1.0
 * CSE131 Lab 7
 * Date: 03/18/2010
 */
public class Matrix {

	private double[][] values;
	

	/**
	 * The Matrix is based on the supplied two-dimensional array of values.
	 * Be sure to make your own copy of the values, so that changes to the
	 *    array outside of this class do not affect your work.
	 * @param values
	 */

	/**
	 * if in[5][4] is a 2D matrix, then what is the value of in.length and in[0].length
	 */
	public Matrix(double[][] in) { // eg  in[5][4]

		/**
		 * making a new array "mat" of a size that is exactly equal to the size of "in"
		 */
		double [][] mat = new double[in.length][in[0].length]; // mat[5][] // GS : corrected

		//TA you obviously understand how arrays are built, but this is incredibly poor style. just instantiate mat as:
		//TA mat=new double[in.length][in[0].length] since we know that all rows have the same length.
		//TA poor style: -5



		/**
		 * assigning elements from in to mat. 
		 */
		for (int i = 0; i< in.length; i++){
			for (int j = 0; j< in[0].length; j++){
				mat[i][j] = in [i][j];
			}
		}
		values = mat;
		//TA you can also substitute values everywhere you have mat, but this works

	}




	/**
	 * You must complete this method, or the equals(Object) test will always
	 *   return false. 
	 * Arrays one and two are considered
	 * equal if and only if:
	 *   1) They have the same shape (number of rows and columns agree)
	 *   2) The contents of the two arrays are the same, element by element
	 * @param one
	 * @param two
	 * @return true iff the arrays have the same shape and contents
	 */
	public static boolean arEq(double[]one, double[] two) {
		boolean ans = true;
		if(one.length != two.length ){
			return false;

		}

		else
		{
			for(int i=0; i<one.length; i++){
				if(one[i]==two[i]){
				}
				else{
					ans = false;

				}
			}
		}

		return ans;
	}

	private static boolean arraysHaveSameShape(double[][] one, double[][] two) { // why is this private

		if((one.length==two.length)&&(one[0].length != two[0].length)) {

			return true;
		}


		else {
			return false;
		}
		//TA this is fine, but again we know that all rows have the same length, since this is a matrix.
		//GS : Corrected
	}


	private static boolean arraysAreEqual(double[][] one, double[][] two) { // why is this private

		if(arraysHaveSameShape (one, two)){
			for(int i = 0; i < one.length; i++){

				for(int j=0; j<one[0].length; j++){

					if(one[i][j]== two[i][j]){
						// can't decide yet, but OK so far
					}
					else {
						System.out.println("Discrepancy at one's " +i + ","+ j + " and two's" + i +","+j);
						System.out.println( "one[i][j](" + one[i][j]+") is not equal to two[i][j] ("+ two[i][j]+")");
						return false;
					}

				}
			}
			return true;
		}

			else {
				System.out.println("Discrepancy in size" );
				return false;
			}


		//GS : Corrected
	//TA this is fine, but again we know that all rows have the same length, since this is a matrix.
}

/**
 * This was generated initially by eclipse, but
 *   eclipse does not know how to compare two-dimensional arrays.
 *   We therefore call arraysAreEequal to do that job.
 */
public boolean equals(Object obj) {
	// If this and obj are the same object, they must be equal
	if (this == obj)
		return true;
	// If obj is null ("this" cannot be null), then they are not equal
	if (obj == null)
		return false;
	// If the two objects are not the same type, they are not equal
	if (getClass() != obj.getClass())
		return false;
	//
	// If we get here, we have two objects of the same type.
	// Calling your helper method to determine the arrays' equality.
	Matrix other = (Matrix) obj;
	return arraysAreEqual(this.values, other.values);
}

public Matrix plus(Matrix other) {
	if(arraysHaveSameShape(this.values, other.values)){
		//TA don't use if(something==true). instead, just use if(something).
		//TA poor style: -5

		//TA furthermore, this will only execute if the two arrays are equal, which is not what you want.
		//TA you want this to execute if they have the same size, even if their elements are different.
		//TA you lucked out with our tests, but i'm still taking off points: -5

		double [][] sum = new double[this.values.length][]; // mat[5][]
		for (int i =0; i < this.values.length; i++){
			sum[i] = new double [this.values[i].length];		// mat[5][4] ; mat[0].length = 4; mat[1].length = 4; mat[2].length = 4 and so on.
		}
		//TA all rows in a matrix have the same length!
		for(int i = 0; i<sum.length; i++){
			for(int j=0; j<sum[i].length; j++){
				sum[i][j] = this.values[i][j]+ other.values[i][j];
			}
		}

		Matrix added = new Matrix(sum);
		return added;
	}
	else{
		throw new IllegalArgumentException("These matrices are not not of equal size");

	}
}

/**
 * Returns a **new Matrix** that is the product of this and the other one.
 * Does not change this Matrix at all.
 * @param other
 * @return
 */
public Matrix times(Matrix other) {
	/**
	 * checking whether columns of this matrix == rows of other matrix 
	 */
	if(this.values[0].length == other.values.length){
		System.out.println("Matrices look good");
		//TA you also don't need to print out all this stuff. but if it makes you happy, go for it
	}
	else{
		throw new IllegalArgumentException("Matrices are incompatible");

	}


	/**
	 * checking whether this matrix has equal no of elements in every row
	 */
	//TA these comments should also be local comments, which use "//"
	for(int i =0; i<this.values.length; i++){
		int count = this.values[0].length;
		if(this.values[i].length!= count){
			//TA WE KNOW THIS WILL ALWAYS BE FALSE, SINCE ALL ROWS IN A MATRIX HAVE THE SAME NUMBER OF COLUMNS!
			//TA poor style: -5
			throw new IllegalArgumentException("This Matrix is bad");
		}
		else{
			System.out.println("This Matrix is good");
		}
	}
	/**
	 * checking whether other matrix is equal no of elements in every row
	 */
	for(int i =0; i<other.values.length; i++){
		int count =other.values[0].length;
		if(other.values[i].length!= count){
			throw new IllegalArgumentException("Other Matrix is bad");
		}
		else{
			System.out.println("Other Matrix is good");
			System.out.println("Now multiplying matrices");	
		}
	}
	/**
	 * now since both the matrices are compatible for multiplication, 
	 * we get their product
	 */

	double [][] product = new double[this.values.length][other.values[0].length];//product matrix is i x j

	/**
	 * as we traverse the product array element[i] by element[j], 
	 * for an element product[i][j], we define k = no of columns of this matrix = no of rows of other matrix 
	 * dp = this[i][k]* other[k][j] from k=0 to k = no of columns of this matrix - 1
	 * add all dp for each product[i][j]
	 * so product[i][j] = this[i][0]* other[0][j] + this[i][1]* other[1][j] + this[i][2]* other[2][j]....+ this[i][k]*other[k][j]
	 * 
	 */


	for(int i=0; i< product.length; i++){
		for(int j=0; j<product[i].length; j++){
			double dp =0; // dotproduct
			for(int k = 0;k< this.values[i].length; k++){
				dp= dp+ this.values[i][k]* other.values[k][j];
			}
			product[i][j]= dp;

		}

	}


	Matrix prod = new Matrix(product);


	return prod;
	//TA poor style, but i can't take off any more points for style alone
}

/**
 * Returns a **new Matrix** that is the transpose of this one.
 * Does not change this Matrix at all.
 * @return
 */
public Matrix transpose() {
	/**
	 * what about those matrices which have different no of elements in each row
	 */

	for(int i =0; i<this.values.length; i++){
		int count = this.values[0].length;
		if(this.values[i].length!= count){
			throw new IllegalArgumentException("This Matrix is bad");
		}
		else{

			System.out.println("This Matrix is good to be transposed");
		}
	}
	double[][]trans = new double[this.values[0].length][this.values.length];
	for(int i = 0; i<this.values.length; i++){
		for(int j = 0; j<this.values[i].length; j++){
			trans[j][i] = this.values[i][j]; // this[2][3] becomes trans[3][2]
		}
	}
	return new Matrix(trans);
	//TA again, poor style but you've already lost all those points

}

/**
 * Modifies this Matrix by scaling row i by the supplied factor.
 * @param i the row to scale, where 0 is the top row
 * @param factor the amount by which to scale each element of row i
 */
public void scaleRow(int i, double factor) {
	if(i<values.length){

		for (int k = 0; k< values[i].length; k++){
			values[i][k] = values[i][k]*factor;
		}}
	else{
		throw new IllegalArgumentException("row "+i+" is out of bound");
	}

}

/**
 * Modifies this matrix by adding row i to row j.  Row 0 is the top row.
 * @param i
 * @param j
 */
public void addRows(int i, int j) {
	if((i<values.length)&&(j<values.length)){
		for(int k =0; k< Math.max(values[i].length, values[j].length); k++)
		{
			double term1 =0;
			double term2 = 0;
			term1 = values[j][k];
			if(k<values[i].length){ // i just want to see if values[i][k] exists or not
				term1 = values[i][k];
			}
			if(k<values[j].length){
				term2 = values[j][k];
			}
			values[j][k] = term1+term2;// can it assign new elements to an array

		}
	}
	else{
		throw new IllegalArgumentException("row index is not good");
	}

}

/**
 * My Columbus Day gift to you.  This returns a String representation of
 * the Matrix.  The contents of each row is separated by a tab (\t)
 * so that columns (kind of) line up.  Each row is separated by a
 * newline (\n) so that the output looks like a matrix.  The output
 * of this method should <i>not</i> be used to compare matrices for
 * equality:  use the .equals(Object) method instead!
 */
public String toString() {
	String ans = "";
	for (int i=0; i < values.length; ++i) {
		ans = ans + "\n";
		// Loop below assumes all rows have the same number of columns
		for (int j=0; j < values[i].length; ++j) {
			ans = ans + values[i][j] + "\t";
		}
	}
	return ans;
}

public String gaussian(){
	Matrix nMatrix = new Matrix(this.values);

	int pc = 0;   // pivotal column
	while (pc<values[0].length-2){
		for(int r = pc+ 1; r<values.length; r++){	

			double m = values[r][pc]/values[pc][pc];
			for (int c = pc; c<values[r].length; c++){
				values[r][c] = values[r][c] - values[pc][c]*m;
			}
		}
		pc++;
	}
	// doing back calculation

	double sol = values[values.length-1][values[0].length-1]/values[values.length-1][values[0].length-2]; // this is the bottom right element of the matrix

	values[values.length-1][values[0].length-1]= sol;  // z = sol , for three variables x,y and z



	return this.toString();
	/*
	 * double m = values[r][0]/values[0][0];
			for (int c = 0; c<values[0].length; c++){
			values[r][c] = values[r][c] - values[0][c]*m;

	 * 
	 * 
	 * 
	 * 
		double m = values[2][0]/values[0][0];
		for (int c = 0; c<values[0].length; c++){
			values[2][c] = values[2][c] - values[0][c]*m;
	 */








}


}
