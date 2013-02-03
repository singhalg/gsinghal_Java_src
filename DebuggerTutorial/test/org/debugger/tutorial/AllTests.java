package org.debugger.tutorial;
import org.junit.runner.*;
import org.junit.runners.*;

@RunWith(Suite.class)
@Suite.SuiteClasses(value={
	MyUtilitiesTest.class,
	PersonTest.class,
	BookTest.class,
	MyLibraryTest.class
	
})

public class AllTests {
	// empty class for JUnit 4 
}
