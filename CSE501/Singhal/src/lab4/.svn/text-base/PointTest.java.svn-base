package lab4;



import static org.junit.Assert.*;
import org.junit.Test;



/**
 * Name: Gaurav Singhal	
 * Lab Section: 501N A
 * Date: 20 Feb, 2010
 * Vector.java
 * CSE 131 Lab 4
 */

public class PointTest {
@Test
public void init(){
	Point center = new Point(5,3);
	assertEquals(5.0, center.getX(), 0.1);
	assertEquals(3.0, center.getY(), 0.1);
	System.out.println(center.toString());
	
}


/**
 * Tests plus(), minus() and distance() methods
 */
@Test
public void Advanced(){
	Point p = new Point(3,4);
	Vector v = new Vector(5, 6);
	Point q = new Point(2,1);
	
	System.out.println(p.plus(v).toString());
	Point result = new Point(8,10);
	assertEquals(result.getX(), p.plus(v).getX(), 0.01);
	
	assertEquals(result.getY(), p.plus(v).getY(), 0.01);
	
	
	Vector resV = new Vector(1,3);
	assertEquals(resV.getDeltaX(), p.minus(q).getDeltaX(), 0.01);
	assertEquals(resV.getDeltaY(), p.minus(q).getDeltaY(), 0.01);
	
	
	assertEquals(3.162, p.distance(q), 0.1);
}

@Test
public void moreAdvanced(){
	Point p = new Point(-3,-4);
	Vector v = new Vector(5, 6);
	Point q = new Point(2,1);
	
	System.out.println(p.plus(v).toString());
	Point result = new Point(2,2);
	assertEquals(result.getX(), p.plus(v).getX(), 0.01);
	
	assertEquals(result.getY(), p.plus(v).getY(), 0.01);
	
	
	Vector resV = new Vector(-5,-5);
	assertEquals(resV.getDeltaX(), p.minus(q).getDeltaX(), 0.01);
	assertEquals(resV.getDeltaY(), p.minus(q).getDeltaY(), 0.01);
	
	
	assertEquals(7.071, p.distance(q), 0.1);
}


}
