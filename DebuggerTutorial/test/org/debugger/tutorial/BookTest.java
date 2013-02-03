package org.debugger.tutorial;

import org.junit.*;
import static org.junit.Assert.*;

public class BookTest {
	
	@Test
	public void testBook() {
		Book b1 = new Book("Great Expectations");
		assertEquals("Great Expectations", b1.title);
		assertEquals("unknown author", b1.author);
	}

	@Test
	public void  testGetPerson() {
		Book b2 = new Book("War and Peace");
		Person p2 = new Person();
		p2.setName("Elvis");
		
		// method to say book is loaned to this person
		b2.setPerson(p2); 
		
		// get the name from the 
//		Person testPerson = b2.getPerson();
//		String testName = testPerson.getName();
		
		String testName = b2.getPerson().getName();
		
		assertEquals("Elvis", testName);
	}
	
	@Test
	public void testToString() {
		Book b1 = new Book("Great Expectations");
		b1.setAuthor("Charles Dickens");
		assertEquals("Great Expectations by Charles Dickens; Available", 
				b1.toString());
		
		Person p2 = new Person();
		p2.setName("Elvis");
		b1.setPerson(p2);
		assertEquals("Great Expectations by Charles Dickens; Checked out to Elvis", 
				b1.toString());
		
	}

}
