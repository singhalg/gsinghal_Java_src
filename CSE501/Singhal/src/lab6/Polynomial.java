// TA Grade: 80 
// TA: toString doesn't work, and you ought to clean it up before resubmitting 

package lab6;
import lab6.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Polynomial {

	final private LinkedList<Double> list;



	/**
	 * Constructs a Polynomial with no terms yet.
	 */
	public Polynomial() {
		list = new LinkedList<Double>();


	}


	/*	private Polynomial synthPoly(Double[] coeffs) {
		Polynomial ans = new Polynomial();
		for (double d : coeffs) {
			ans.addTerm(d);
		}
		return ans;}
	 */

	// TA: Doesn't work... IndexOutOfBoundsException (-20)
	public String toString() {

		int index = list.indexOf(list.peekLast());
		System.out.println("index = " +index);
		System.out.println("item at " +index + " = " + list.get(index));
		System.out.println("item at " + (index-1) + " = " + list.get(index-1));
		System.out.println("item at " + (index-2) + " = " + list.get(index-2));

		Double [] listToArray = new Double [this.list.size()];
		this.list.toArray(listToArray);
		for(int i = 0 ; i < listToArray.length; i++){

			System.out.println(i+ "= " + listToArray[i]);
		}
		for(int i = 0 ; i < listToArray.length; i++){
			if ((i ==0 )&& (listToArray[i]>0)){
				System.out.print(listToArray[i]+"x^" + (listToArray.length-1-i) + "  ");
			}
			else if  (listToArray[i]>0){
				System.out.print(" +" +listToArray[i]+"x^" +(listToArray.length-1-i) + "  ");
			}
			else if (listToArray[i]<0){
				System.out.print(listToArray[i]+"x^" +(listToArray.length-1-i) + "  ");
			}
			else {}

		}

		return "the polynomial";
	}



	public Polynomial addTerm(double coeff) {

		list.add(coeff);

		return this;  
	}

	/**
	 * I am initially returning a random double so that
	 *    almost all assertEquals will fail on this method
	 *    until it is implemented.  Be sure to implement this
	 *    method recursively as specified in the lab documentation.
	 * @return the evaluation of this polynomial at x
	 */

	
	// TA: Crazy blocks of commented out code... poor style.
	
	/*
	public double evaluateHelp(double x, int index) {
		double ans;
		if (index<0){
			return list.get(index);
		}
		/**
	 *   7,  5,  -2,  5
	 *  [3]  [2] [1]  [0]
	 *  
	 *   7 + 5x - 2x^2 + 5x^3
	 *



		else{
			 ans = x* (list.get(index)) + evaluateHelp(x,index--);
		return ans;

		}
	}

	public double evaluate(double x){
		while (list.isEmpty()== false){
			newlist.push(list.pop());
		}



		int index = list.indexOf(list.peekLast());
		return list.get(index)+ evaluateHelp(x, index--);

	}*/

	/*
	public double evaluate(double x){


		while (list.isEmpty()== false){
			newlist.push(list.pop());
		}



		int index = list.indexOf(list.peekLast());
		return list.get(index)+ evaluateHelp(x, index--);




	}
	public double evaluateHelp(double x, int index) {

		if (index==0){
			return list.get(index);
		}
		//7 + 5x - 2x^2 + 5x^3  =  7 + x(5 + x(-2 + x(5)))

		/**
	 *   7,  5,  -2,  5
	 *  [0]  [1] [2]  [3]
	 *  
	 *   7 + 5x - 2x^2 + 5x^3
	 */
	/*
		else{

			return   x* (list.get(index)) + evaluateHelp(x,index++);

		}
	}

	 */
	/**
	 * This method does return a new Polynomial that is the
	 *    derivative of the current one.
	 * @return a new Polynomial that is the derivative of this one
	 */


	/*

	public Polynomial derivative() {
		Double [] listToArray = new Double [this.list.size()];
		this.list.toArray(listToArray);
		int lb = 0;
		int ub = listToArray.length - 1;

		while (lb<ub){
			double temp = listToArray[lb];
			listToArray[lb] = listToArray [ub];
			listToArray[ub] = temp;
			lb++;
			ub--;
		}

		for (int i = 1; i< listToArray.length-1; i++){
			listToArray[i]= listToArray[i]*i;
	}

		// to convert listToArray back to polynomial
		Polynomial derived = synthPoly(listToArray); 
		return derived;
	}
	 */
	//7 + 5x - 2x^2 + 5x^3  =  7 + x(5 + x(-2 + x(5)))
/**
 * evaluates the polynomial for a particular value of x
 * 
 */
	public double evaluate(double x){
		Iterator<Double> it = getIterator();
		return evalHelp(x, it);
	}
/**
 * helper method for evaluate 
 * @param x, from the evaluate method
 * @param it, an Iterator for list
 * @return  
 */
	public double evalHelp(double x, Iterator <Double> it){
		if (it.hasNext()!= true){
			return 0;
		}
		else{
			return it.next() + x*evalHelp(x, it);
		}

	}
	
	/**
	 * make a new int i which starts at 1 and increases with the index of the element of the list
	 * multiply the list element at index i with i, and add the terms sequentially to the new polynomial
	 * @return a new Polynomial ans
	 */
	public Polynomial derivative() {
		Polynomial ans = new Polynomial();

		for (int i = 1; i < list.size(); i++){
			ans.addTerm(list.get(i)*i);
		}

		return ans;
	}


	/**
	 * Compute the sum of this and the other Polynomial, returning
	 *    a new Polynomial that represents that sum.
	 * @return a new Polynomial that is the some of this and another
	 */

	/*
	public Polynomial sum(Polynomial another) {

		Double [] thisArray = new Double [this.list.size()];
		this.list.toArray(thisArray);


		Double [] otherArray = new Double [another.list.size()];
		another.list.toArray(otherArray);

		Double sum[] = new Double [Math.max(thisArray.length, otherArray.length)];

		for (int i = 0 ; i < sum.length; i++){
			if (i<thisArray.length)
			{
				sum[i] = thisArray[i];
				}
			if (i<otherArray.length){
				sum[i] = sum[i]+otherArray[i];
			}
		}
		Polynomial sumList = synthPoly(sum); 
		return sumList;

		


	}
	 */
	/**
	 * This is the "equals" method that is called by
	 *    assertEquals(...) from your JUnit test code.
	 *    It must be prepared to compare this Polynomial
	 *    with any other kind of Object (obj).  Eclipse
	 *    automatically generated the code for this method,
	 *    after I told it to use the contained list as the basis
	 *    of equality testing.  I have annotated the code to show
	 *    what is going on.
	 */

	public Polynomial sum(Polynomial another) {

		Polynomial sum = new Polynomial();


		for (int i = 0;  i < Math.max(list.size(), another.list.size()); i++){

			double term1 = 0.0, term2 = 0.0;
			if (i < this.list.size())
				term1 = this.list.get(i);
			if (i < another.list.size())
				term2 = another.list.get(i);


			sum.addTerm(term1+term2);


		}

		return sum;	

	}


	public boolean equals(Object obj) {
		// If the two objects are exactly the same object,
		//    we are required to return true.  The == operator
		//    tests whether they are exactly the same object.
		if (this == obj)
			return true;
		// "this" object cannot be null (or we would have
		//    experienced a null-pointer exception by now), but
		//    obj can be null.  We are required to say the two
		//    objects are not the same if obj is null.
		if (obj == null)
			return false;

		//  The two objects must be Polynomials (or better) to
		//     allow meaningful comparison.
		if (!(obj instanceof Polynomial))
			return false;

		// View the obj reference now as the Polynomial we know
		//   it to be.  This works even if obj is a BetterPolynomial.
		Polynomial other = (Polynomial) obj;

		//
		// If we get here, we have two Polynomial objects,
		//   this and other,
		//   and neither is null.  Eclipse generated code
		//   to make sure that the Polynomial's list references
		//   are non-null, but we can prove they are not null
		//   because the constructor sets them to some object.
		//   I cleaned up that code to make this read better.


		// A LinkedList object is programmed to compare itself
		//   against any other LinkedList object by checking
		//   that the elements in each list agree.

		return this.list.equals(other.list);
	}

	/**
	 * This is needed only for the BetterPolynomial extension.
	 * But you can also use it within this class if you find it
	 * handy.
	 * @return an iterator over the contained LinkedList's elements
	 */
	protected Iterator<Double> getIterator() {
		return list.iterator();


	}

}
