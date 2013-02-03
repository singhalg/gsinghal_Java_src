package julia;

/**
 * Complex number, an immutable class
 * @author cytron
 *
 */
public class Complex {
	
private double real;
private double imaginary;

	/**
	 * A complex number
	 * @param real part
	 * @param imaginary part
	 */
	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public double getReal() {
		return real; // FIXME
	}

	public double getImaginary() {
		return imaginary; // FIXME
	}
	
	/**
	 * Return a new Complex number that is the sum of this
	 *   and the other one
	 * @param other
	 * @return sum of this and the other Complex numbers
	 */
	public Complex plus (Complex other) {
		double newreal = this.real + other.real;
		double newimaginary = this.imaginary+ other.imaginary;
		Complex newComplex = new Complex (newreal, newimaginary);
		
		return newComplex; // FIXME
	}

	/**
	 * Return the difference of this and the other Complex numbers
	 * @param other
	 * @return this - other
	 */
	public Complex minus(Complex other) {
		double newreal = this.real - other.real;
		double newimaginary = this.imaginary- other.imaginary;
		Complex newComplex = new Complex (newreal, newimaginary);
		
		return newComplex; // FIXME
		
		}
	
	/**
	 * Return a new Complex number that is the product of this
	 *   and the other number.
	 * @param other
	 * @return this * other
	 */
	public Complex times(Complex other) {
		// (a,b).(c,d)=(ac - bd,ad + bc) 
		double newreal = (this.real * other.real) - (this.imaginary*other.imaginary);
		double newimaginary = (this.real* other.imaginary) - (this.imaginary* other.real);
		Complex newComplex = new Complex (newreal, newimaginary);
		
		return newComplex; // FIXME
		
		
	}
	
	/**
	 * Return the distance between (0,0) and this Complex number
	 * @return distance from (0,0)
	 */
	public double abs() { 
		
		
		return Math.sqrt(Math.pow(this.real,2)  + Math.pow(this.imaginary, 2)); // FIXME
	}
	
	public String toString() {
		return "("+ getReal() + " + " + getImaginary() + "i)";
	}
}
