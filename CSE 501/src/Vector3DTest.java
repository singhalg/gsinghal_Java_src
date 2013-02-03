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

public class Vector3DTest {
	@Test
	public void init() {
		Vector3D v = new Vector3D(4, 3, 2);
		System.out.println(v.toString());
		System.out.println(v.magnitude());
		System.out.println((v.deflectX()).toString());
		System.out.println((v.deflectY()).toString());
		System.out.println((v.deflectZ()).toString());
		assertEquals(4.0,  v.getDeltaX(), .01);
		assertEquals(3.0, v.getDeltaY(), .01);
		assertEquals(2.0, v.getDeltaZ(), .01);
	}

	@Test
	public void arith() {
		Vector3D v = new Vector3D(5, -3, 2);
		Vector3D vPlusV = v.plus(v);
		Vector3D vMinusV = v.minus(v);

		
		
		// Make sure the old vector did not change
		//
		compareVectors(new Vector3D(5, -3, 2), v);
		//
		// Make sure the new vector is right
		//
		compareVectors(new Vector3D(10, -6, 4), vPlusV);
		compareDoubles(10, vPlusV.getDeltaX());
		compareDoubles(-6, vPlusV.getDeltaY());
		compareDoubles(4, vPlusV.getDeltaZ());
		//
		// Test toString visually
		System.out.println("TA check v:      " + v);
		System.out.println("TA check vplusV: " + vPlusV);
	
		// evaluating the minus method
		compareVectors(new Vector3D(0, 0, 0), vMinusV);
		compareDoubles(0, vMinusV.getDeltaX());
		compareDoubles(0, vMinusV.getDeltaY());
		compareDoubles(0, vMinusV.getDeltaZ());
		
	}

	/**
	 * Compare two Vectors JUnit-style, failing if they do not
	 * agree on their x, y and z deltas.
	 * @param one
	 * @param two
	 */
	
	
	public void compareVectors(Vector3D one, Vector3D two) {
		
		compareDoubles(one.getDeltaX(), two.getDeltaX());
		compareDoubles(one.getDeltaY(), two.getDeltaY());
		compareDoubles(one.getDeltaZ(), two.getDeltaZ());
	}


	/**
	 * Why did I write this method?
	 * @param one    one of two doubles to compare
	 * @param other  other of two doubles to compare
	 * 
	 * This method does the "assertEquals" test on two double values, and supplied some tolerance limit for comparison.
	 */
	public void compareDoubles(double one, double other) {
		assertEquals(one, other, 0.01);
	}



	@Test
	public void scale() {
	
		Vector3D v = new Vector3D(2, -3,5);
		Vector3D bigger = v.scale(1.5);
		Vector3D smaller = v.scale(0.75);
		compareDoubles( 3.0,   gx(bigger));
		compareDoubles(-4.5,   gy(bigger));
		compareDoubles(7.5,   gz(bigger));
		compareDoubles( 1.5,  gx(smaller));
		compareDoubles(-2.25,  gy(smaller));
		compareDoubles( 3.75,  gz(smaller));
	
		
		Vector3D zero = new Vector3D(0,0,0);
		Vector3D zeroScaled = zero.scale(2);
		compareDoubles( 0,   gx(zeroScaled));
		compareDoubles(0,   gy(zeroScaled));
		compareDoubles(0,   gy(zeroScaled));
	}

	/**
	 * Why did I write this method?
	 * @param v a vector
	 * @return v's x component
	 */
	public double gx(Vector3D v) {
		return v.getDeltaX();
	}

	/**
	 * Why did I write this method?
	 * @param v a vector
	 * @return v's y component
	 */
	public double gy(Vector3D v) {
		return v.getDeltaY();
	}
	
	public double gz(Vector3D v) {
		return v.getDeltaZ();
	}
	
	@Test
	public void rescale() {
		Vector3D v = new Vector3D(3, 4, 1);
		compareDoubles(5.099, v.magnitude());
		compareDoubles(6.0, gx(v.rescale(10.198039)));
		compareDoubles(8.0, gy(v.rescale(10.198039)));
		compareDoubles(2.0, gz(v.rescale(10.198039)));
		
		Vector3D zero = new Vector3D(0, 0, 0);
		Vector3D rescaled = zero.rescale(5);
		compareDoubles(5, rescaled.magnitude());
		compareDoubles(5.0, gx(rescaled));
		compareDoubles(0, gy(rescaled));
		compareDoubles(0, gz(rescaled));
	}

	@Test
	public void specialCases() {
		Vector3D v = new Vector3D(0, 0,0);
		Vector3D r = v.rescale(5);
		compareDoubles(0, v.magnitude());
		compareDoubles(5, r.magnitude());
		compareDoubles(5, gx(r));
		compareDoubles(0, gy(r));
		compareDoubles(0, gz(r));
	}

}
