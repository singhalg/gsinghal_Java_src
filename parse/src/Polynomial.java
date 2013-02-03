// TA Grade: 80 
// TA: toString doesn't work, and you ought to clean it up before resubmitting 
// Now Resubmitting; toString corrected
//package lab6;
//import lab6.*;
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




	// TA: Doesn't work... IndexOutOfBoundsException (-20) 
	//Corrected
	public String toString() {

		Iterator<Double> it = getIterator();
		return helpToString(it, 0);

	}
	/**
	 * helper method for toString method
	 * @param it
	 * @param degree
	 * @return
	 */
	private String helpToString(Iterator<Double> it, int degree){
		if(!it.hasNext())
			return "" ;
		else {
			return " + "+it.next()+ "x^"+degree + helpToString(it, degree+1) ;
		}
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
