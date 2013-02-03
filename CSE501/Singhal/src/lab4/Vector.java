//TA: 85. No credit for extension because Scale doesn't work. You can resubmit the extension at any time.

package lab4;

/**
 * Name: Gaurav Singhal	
 * Lab Section: 501N A
 * Date: 20 Feb, 2010
 * Vector.java
 * CSE 131 Lab 4
 */

public class Vector {

	private double deltaX = 0;
	private double deltaY = 0;



/**
 * Constructor for Vector class
 * @param componentX
 * @param componentY
 */
	public Vector(double componentX, double componentY){
		this.deltaX = componentX;
		this.deltaY = componentY;

	}

	
	/**
	 * @return the X component of a vector
	 */
	public double getDeltaX (){
		return deltaX;

	}

	
	/**
	 * @return the Y component of a vector
	 */
	public double getDeltaY (){
		return deltaY;

	}

	
	/**
	 * @return the X and Y components of a vector in the form of a String
	 * [X Y]
	 */
	public String toString(){

		return "["+ (int)deltaX + " "+ (int) deltaY + "]";
	}

	
	
	/**
	 * @returns the magnitude
	 * ((X^2)+(Y^2))^1/2
	 */
	public double magnitude(){
		return Math.sqrt(Math.abs(Math.pow(deltaX,2))+ Math.abs(Math.pow(deltaY,2)));
	}

	
	/**
	 * @returns a new Vector with X component equal to negative to X component of this.vector
	 */
	//TA: no need to create the variable xDeflected. Just return the new Vector to save memory
	public Vector deflectX(){
		Vector xDeflected = new Vector(deltaX*(-1), deltaY);
		return xDeflected;
	}

	
	/**
	 * @returns a new Vector with Y component equal to negative to Y component of this.vector
	 */
	public Vector deflectY(){
		Vector yDeflected = new Vector(deltaX, deltaY*(-1));
		return yDeflected;
	}

	
	/**
	 * 
	 * @returns a new Vector with X component equal to the sum of the X components of this vector and other vector
	 * same for Y component
	 */
	public Vector plus(Vector other){
		Vector newV = new Vector( (this.deltaX+ other.deltaX),(this.deltaY+other.deltaY) );

		return newV ;
	}

	
	/**
	 * @returns a new Vector with X component equal to the X components of the other vector subtracted from X component of this vector
	 * same for Y component
	 */
	//TA: using the term this is not necessary in this context since there's no ambiguity.
	//TA: You should use your plus method and just pass it negative values.
	public Vector minus(Vector other){
		Vector newV = new Vector( (this.deltaX - other.deltaX),(this.deltaY - other.deltaY) );

		return newV ;
	}

	
	
	/**
	 * 
	 * @param factor
	 * @returns a new Vector with the X and Y components multiplied by the factor
	 * However, if both X and Y components are 0, then return the factor as the X component, 
	 * and 0 as Y component
	 */
	public Vector scale (double factor){
		
		//TA: this should be your basecase in the Rescale method, not this one because if there is a vector of 0 and it's scaled by any factor, it should remain 0 but this changes it.
		if (this.magnitude()==0){
			Vector scaledVector = new Vector( factor, deltaY);
			return scaledVector;
		}
		else{
		Vector scaledVector = new Vector( deltaX*factor, deltaY*factor);
		return scaledVector;
		}
		
	}

	
	
	/**
	 * 
	 * @param magnitude
	 * @returns a new vector with the magnitude as supplied as the argument 
	 * and the direction of this vector (original vector)
	 * 
	 */
	//TA: Where is your special case? 
	public Vector rescale (double magnitude){

		
		double sf;
		if (this.magnitude()==0){
			sf = magnitude;
		}
		else{
		sf= magnitude/this.magnitude();
		}
		
		Vector rescaled = this.scale(sf);

		
		return rescaled;
	}

	

}