package lab4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Name: Gaurav Singhal	
 * Lab Section: 501N A
 * Date: 20 Feb, 2010
 * Vector.java
 * CSE 131 Lab 4
 */
public class Point3DTest {
	@Test
	public void init(){
		Point3D center = new Point3D(5,3,2);
		assertEquals(5.0, center.getX(), 0.1);
		assertEquals(3.0, center.getY(), 0.1);
		assertEquals(2.0, center.getZ(), 0.1);
		System.out.println(center.toString());
		
	}


	/**
	 * Tests plus(), minus() and distance() methods on 3D points and 3D vectors
	 */
	@Test
	public void Advanced(){
		Point3D p = new Point3D(3,4,1);
		Vector3D v = new Vector3D(5, 6,0);
		Point3D q = new Point3D(2,1, 3);
		
		System.out.println(p.plus(v).toString());
		
		Point3D result = new Point3D(8,10,1);
		
		assertEquals(result.getX(), p.plus(v).getX(), 0.01);
		assertEquals(result.getY(), p.plus(v).getY(), 0.01);
		assertEquals(result.getZ(), p.plus(v).getZ(), 0.01);
		
		
		Vector3D resV = new Vector3D(1,3,-2);
		assertEquals(resV.getDeltaX(), p.minus(q).getDeltaX(), 0.01);
		assertEquals(resV.getDeltaY(), p.minus(q).getDeltaY(), 0.01);
		assertEquals(resV.getDeltaZ(), p.minus(q).getDeltaZ(), 0.01);
		assertEquals(3.7416, p.distance(q), 0.1);
	}

	
}
