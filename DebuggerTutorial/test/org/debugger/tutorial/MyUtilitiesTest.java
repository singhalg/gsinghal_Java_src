package org.debugger.tutorial;

import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

public class MyUtilitiesTest {
	
	@Test
	public void saveStringToFile() {
		String saveString = "Out, out, brief candle!\n" +
		"Life's but a walking shadow, a poor player,\n" +
		"That struts and frets his hour upon the stage,\n" +
		"And then is heard no more. It is a tale\n" +
		"Told by an idiot, full of sound and fury,\n" +
		"Signifying nothing.\n";
		
		File testFile = new File("testsavetostring.txt");
		testFile.delete();
		assertFalse("File should not exist",
				testFile.exists());
		
		assertTrue("File should have been saved",
				MyUtilities.saveStringToFile("testsavestring.txt",
						saveString));
		
		String newString = MyUtilities.getStringFromFile(
				"testsavestring.txt");
		assertTrue("Save and get strings should be equal",
				saveString.equals(newString));
	}
	
	public MyLibrary createMyLibrary() {
		Book b1;
		Book b2;
		Person p1;
		Person p2;
		MyLibrary ml;
		
		b1 = new Book("Book1");
		b2 = new Book("Book2");
		p1 = new Person();
		p1.setName("Fred");
		p2 = new Person();
		p2.setName("Sue");
		ml = new MyLibrary("Test");
		
		ml.addBook(b1);
		ml.addBook(b2);
		ml.addPerson(p1);
		ml.addPerson(p2);
		ml.checkOut(b1, p1);
		return ml;
	}
	
	@Test public void saveToSerialFile() {
		MyLibrary startMyLibrary = createMyLibrary();
		String fileName = "testmylibrary.ser";
		File testFile = new File(fileName);
		testFile.delete();
		assertFalse("File should not exist",
				testFile.exists());
		assertTrue("File should have been saved", 
				MyUtilities.saveMyLibraryToSerialFile(
						fileName, startMyLibrary));
		MyLibrary endMyLibrary = 
			MyUtilities.getMyLibraryFromSerialFile(fileName);
		assertEquals("Test", endMyLibrary.getName());
		assertEquals(2, endMyLibrary.getBooks().size());
		assertEquals(2, endMyLibrary.getPeople().size());
		assertEquals("Fred", endMyLibrary.getBooks().
				get(0).getPerson().getName());
//		assertTrue("Person with first book should be Fred",
//				"Fred" == endMyLibrary.getBooks().
//				get(0).getPerson().getName());
	}	
	
	//@Test 
	public void factorial() {
		assertEquals(120, MyUtilities.factorial(5));
	}
	
	//@Test 
	public void longLoop() {
		assertTrue(MyUtilities.longLoop());
	}
}