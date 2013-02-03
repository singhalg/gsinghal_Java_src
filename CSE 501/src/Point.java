/* package lab4;
/**
 * Name: Gaurav Singhal	
 * Lab Section: 501N A
 * Date: 20 Feb, 2010
 * Vector.java
 * CSE 131 Lab 4
 */
public class Point {

	private double xCoord = 0;
	private double yCoord = 0;



	/**
	 * Constructor for Point class
	 * @param pointX
	 * @param pointY
	 */
	public Point(double pointX, double pointY){
		this.xCoord = pointX;
		this.yCoord = pointY;

	}


	/**
	 * 
	 * @returns  the X coordinate
	 */
	public double getX (){
		return xCoord;

	}
	/**
	 * 
	 * @returns  the Y coordinate
	 */
	public double getY (){
		return yCoord;

	}


	/**
	 * @return the X and Y coordinates of a point in the form of a String
	 * (X,Y)
	 */
	public String toString(){

		return "("+ xCoord + ","+ yCoord + ")";
	}


	/**
	 * 
	 * @param one, a Vector
	 * @returns a new point with the X coordinate as the sum of X component of one vector and X coordinate of this point
	 * same for Y coordinate 
	 */
	public Point plus (Vector one){
		Point newPoint = new Point(one.getDeltaX()+ xCoord, one.getDeltaY()+ yCoord);
		return newPoint;

	}



	/**

	 * @param one, a Point
	 * @returns a new Vector with the X component as the sum of X coordinate of this point and another point
	 * same for Y component 
	 */
	public Vector minus (Point another){
		Vector newVector = new Vector (this.xCoord - another.xCoord, this.yCoord - another.yCoord);
		return newVector;

	}


	/**
	 * 
	 * @param another
	 * @returns the linear distance between two points, which form a vector
	 * then, magnitude() method is applied to the newly defined vector
	 */
	public double distance(Point another){

		Vector dist = minus(another);

		return dist.magnitude();


	}

}
