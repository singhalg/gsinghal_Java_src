package org.debugger.tutorial;

import org.junit.*;

import static org.junit.Assert.*;

public class PersonTest {

	@Test
	public void testPerson() {
		Person p = new Person();
		assertEquals("unknown name", p.getName());
		assertEquals(3, p.getMaximumBooks());
	}

	@Test
	public void testSetMaximumBooks() {
		Person p1 = new Person();
		p1.setMaximumBooks(10);
		assertEquals(10, p1.getMaximumBooks());
	}

	@Test
	public void testSetName() {
		Person p1 = new Person();
		p1.setName("Fred Flintstone");
		assertEquals("Fred Flintstone", p1.getName());
	}
	
	@Test
	public void testToString() {
		Person p1 = new Person();
		p1.setName("Fred Flintstone");
		p1.setMaximumBooks(7);
		String testString = "Fred Flintstone (7 books)";
		assertEquals(testString, p1.toString());
	}
	
	@Test
	public void testObjectReference() {
		int a = 3;
		int b = a;
		a = 4;
		System.out.println(b);
		Person p1 = new Person();
		p1.setName("William Shakespeare");
		Person p2 = p1;
		p2.setName("Charles Dickens");
		assertTrue("What happened to p1's name?",
				p1.getName().equals("William Shakespeare"));
		
		String s1 = "a string";
		String s2 = "a string";
		assertTrue(s1 == s2);
		s2 = "a string with more";
		s2 = s2.substring(0,8);
		assertFalse(s1 == s2);
		assertTrue(s1.equals(s2));
		
	}

}
