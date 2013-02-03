package lab4;
/**
 * Name: Gaurav Singhal	
 * Lab Section: 501N A
 * Date: 20 Feb, 2010
 * Vector.java
 * CSE 131 Lab 4
 */
public class Vector3D {
	
	private double deltaX = 0;
	private double deltaY = 0;
	private double deltaZ = 0;
	
	
	
	/**
	 * Constructor for Vector class
	 * @param x
	 * @param y
	 * @param z
     */
	public Vector3D(double x, double y, double z){
		this.deltaX = x;
		this.deltaY = y;
		this.deltaZ = z;
		
	}
	/**
	 * @return the X component of a 3D vector
	 */
	public double getDeltaX (){
		return deltaX;

	}

	
	/**
	 * @return the Y component of a 3D vector
	 */
	public double getDeltaY (){
		return deltaY;

	}

	/**
	 * @return the Z component of a 3D vector
	 */
	public double getDeltaZ (){
		return deltaZ;

	}

	/**
	 * @return the X,Y and Z components of a 3D vector in the form of a String
	 * [X Y Z]
	 */
	public String toString(){

		return "["+ (int)deltaX + " "+ (int) deltaY + " " + (int) deltaZ +"]";
	}
	
	/**
	 * @returns the magnitude
	 * ((X^2)+(Y^2)+(Z^2)^1/2
	 */
	public double magnitude(){
		return Math.sqrt(Math.abs(Math.pow(deltaX,2))+ Math.abs(Math.pow(deltaY,2))+ Math.abs(Math.pow(deltaZ,2)));
	}
	
	/**
	 * @returns a new 3D Vector with X component equal to negative to X component of this.vector
	 */
	public Vector3D deflectX(){
		Vector3D xDeflected = new Vector3D(deltaX*(-1), deltaY, deltaZ);
		return xDeflected;
	}

	
	/**
	 * @returns a new 3D Vector with Y component equal to negative to Y component of this.vector
	 */
	public Vector3D deflectY(){
		Vector3D yDeflected = new Vector3D(deltaX, deltaY*(-1), deltaZ);
		return yDeflected;
	}

	/**
	 * @returns a new 3D Vector with Z component equal to negative to Z component of this.vector
	 */
	public Vector3D deflectZ(){
		Vector3D zDeflected = new Vector3D(deltaX, deltaY, deltaZ*(-1));
		return zDeflected;
	}
	
	
	/**
	 * 
	 * @returns a new 3D Vector with X component equal to the sum of the X components of this 3D vector and other 3D vector
	 * same for Y and Z components
	 */
	public Vector3D plus(Vector3D other){
		Vector3D newV = new Vector3D( (this.deltaX+ other.deltaX),(this.deltaY+other.deltaY), (this.deltaZ+other.deltaZ) );

		return newV ;
	}
	
	/**
	 * @returns a new 3D Vector with X component equal to the X components of the other vector subtracted from X component of this vector
	 * same for Y and Z component
	 */
	public Vector3D minus(Vector3D other){
		Vector3D newV = new Vector3D( (this.deltaX - other.deltaX),(this.deltaY - other.deltaY), (this.deltaZ - other.deltaZ) );

		return newV ;
	}
	
	
	/**
	 * 
	 * @param factor
	 * @returns a new 3D Vector with the X, Y and Z components multiplied by the factor
	 * However, if all the components are 0, then return the factor as the X component, 
	 * and 0 as Y and Z components
	 */
	public Vector3D scale (double factor){
		//TA: again, this scale method does not work for a vector with value of 0
		
		if (this.magnitude()==0){
			Vector3D scaledVector = new Vector3D( factor, deltaY, deltaZ);
			return scaledVector;
		}
		else{
		Vector3D scaledVector = new Vector3D( deltaX*factor, deltaY*factor, deltaZ*factor);
		return scaledVector;
		}
		
	}
	
	
	/**
	 * 
	 * @param magnitude
	 * @returns a new 3D vector with the magnitude as supplied as the argument 
	 * and the direction of the new 3D vector is same as that of original vector
	 * 
	 */
	public Vector3D rescale (double magnitude){

		
		double sf;
		if (this.magnitude()==0){
			sf = magnitude;
		}
		else{
		sf= magnitude/this.magnitude();
		}
		
		Vector3D rescaled = this.scale(sf);

		
		return rescaled;
	}

	


	
	
	
}
