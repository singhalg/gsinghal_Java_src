package lab2;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Name: Gaurav	Singhal
 * Lab Section: 501N A
 * Date: Jan 31, 2010
 * Email: gsinghal@wustl.edu
 * RecursionTest.java
 * CSE 131 Lab 2
 */

public class RecursionTest {//there is no "main" class..?

	// Example:
	//@Test
	public void testFactorial() {
		assertEquals(1, Recursion.factorial(1));
		assertEquals(24, Recursion.factorial(4));
	}

	// Your test methods go here.
	//@Test
	public void testAddition() {
		assertEquals(1, 1+0);
		assertEquals(131, 2*60 + 11);
	}

	// test for sumDownBy2
	//@Test
	public void testsumDownBy2(){
		assertEquals(0, Recursion.sumDownBy2(-3)); //testing base case
		assertEquals(20, Recursion.sumDownBy2(8)); //testing for k = 8
	//	System.out.println(Recursion.sumDownBy2(10));
		//System.out.println(Recursion.sumDownBy2(-5));
//
	}

	//@Test
	public void testharmonicSum(){
		assertEquals(0.0, Recursion.harmonicSum(-3), 0.01); //testing base case
		assertEquals(3.10, Recursion.harmonicSum(12), 0.01); //testing for k = 12
		//System.out.println(Recursion.sumDownBy2(10));
		//System.out.println(Recursion.sumDownBy2(-5));

	}

	//@Test
	public void testgeometricSum(){
		assertEquals(1.0, Recursion.geometricSum(0),0.01);
		assertEquals(0.0,Recursion.geometricSum(-5), 0.01);
		assertEquals(1.996094, Recursion.geometricSum(8), 0.01);
	}
//	@Test
	public void testmult(){
		assertEquals(20, Recursion.mult(5,4));
		assertEquals(1,Recursion.mult(1,1));
		assertEquals(20,Recursion.mult(-5,-4));
		assertEquals(-6,Recursion.mult(-3,2));
		assertEquals(-6,Recursion.mult(3,-2));
		assertEquals(0,Recursion.mult(0,0));
		assertEquals(0,Recursion.mult(0,1));
		assertEquals(0,Recursion.mult(-5,0));
		assertEquals(0,Recursion.mult(5,-0));
		//assertEquals(1.996094, Recursion.geometricSum(8), 0.01);

	}

	//@Test
	public void testExpt(){
		assertEquals(625, Recursion.expt(5,4));
		assertEquals(-5,Recursion.expt(-5,1));
		assertEquals(625,Recursion.expt(-5,4));
		assertEquals(1,Recursion.expt(5,0));
		assertEquals(0,Recursion.expt(2,-3));
	}
	//@Test
	public void testLcm(){
		assertEquals (15, Recursion.lcm(5, 3));
		assertEquals (24, Recursion.lcm(8,6));
		assertEquals (0, Recursion.lcm(5,0));
		assertEquals (100, Recursion.lcm(10,100));
	}
	@Test
	public void testLoanLength(){
		Recursion.helpLoanLength(2500000, 0.07,20.0,0);
		//assertEquals(1.0, Recursion.loanLength(1000, 0.1, 1050), 0.1);
		//assertEquals(0.0, Recursion.loanLength(0, 0.9, 50), 0.1);
		//assertEquals(6.0, Recursion.loanLength(1100, 0.1, 200), 0.1);
	}
}

