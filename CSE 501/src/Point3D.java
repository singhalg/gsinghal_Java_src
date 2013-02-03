//TA Extension 4 passed
package lab4;
/**
 * Name: Gaurav Singhal	
 * Lab Section: 501N A
 * Date: 20 Feb, 2010
 * Vector.java
 * CSE 131 Lab 4
 */
public class Point3D {

	private double xCoord = 0;
	private double yCoord = 0;
	private double zCoord = 0;
	
	public Point3D(double x, double y, double z){
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
				
	}
	
	/**
	 * 
	 * @returns  the X coordinate
	 */
	public double getX(){
		return xCoord;

	}
	/**
	 * 
	 * @returns  the Y coordinate
	 */
	public double getY(){
		return yCoord;

	}
	
	/**
	 * 
	 * @returns  the Z coordinate
	 */
	public double getZ(){
		return zCoord;

	}
	
	/**
	 * @return the X, Y and Z coordinates of a point in the form of a String
	 * (X,Y,z)
	 */
	public String toString(){

		return "("+ xCoord + ","+ yCoord + "," +zCoord+")";
	}

	
	/**
	 * 
	 * @param Vector some
	 * @returns a new point with the X coordinate as the sum of X component of one vector and X coordinate of this point
	 * same for Y and Z coordinates 
	 */
	public Point3D plus (Vector3D some){
		Point3D newPoint = new Point3D(some.getDeltaX()+ xCoord, some.getDeltaY()+ yCoord, some.getDeltaZ()+ zCoord);
		return newPoint;

	}
	
	/**

	 * @param Point3D another
	 * @returns a new 3D Vector with the X component as the sum of X coordinate of this point and another point
	 * same for Y and Z components 
	 */
	public Vector3D minus (Point3D another){
		Vector3D newVector = new Vector3D (this.xCoord - another.xCoord, this.yCoord - another.yCoord, this.zCoord - another.zCoord);
		return newVector;

	}
	
	
	/**
	 * @param Point 3D another
	 * @returns the linear distance between two points in 3D space, which forms a 3D Vector
	 * then, magnitude() method is applied to the newly defined 3D vector
	 */
	public double distance(Point3D another){

		Vector3D dist = minus(another);

		return dist.magnitude();


	}
	
}
