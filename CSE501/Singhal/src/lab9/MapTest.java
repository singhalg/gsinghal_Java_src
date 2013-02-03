package lab9;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Test;

public abstract class MapTest {

	public abstract Map<String,Integer> createMap();

	@Test
	public void testAllMethods() {
		Map<String,Integer> m = createMap();
		m.put("meow",2);
		assertEquals(2, (int) m.get("meow"));
		m.put("bow wow",8);

		assertEquals(2, (int) m.get("meow"));
		assertEquals(8, (int) m.get("bow wow"));
		System.out.println("test 1");
		System.out.println(m.toString());
		System.out.println("--- cats and dogs:\n"); //+ m.toString());

		m.put("oink",5);
		m.put("quack",4);
		m.put("baa",5);
		System.out.println("test 2");

		System.out.println("\n--- after adding pigs, ducks, and sheep:\n"+ m.toString());

		m.put("arf",12);
		m.put("neigh", 2);
		m.put("moo",7);	    
		System.out.println("test 3");
		System.out.println("\n--- after adding little dogs, horses, and cows:\n"+ m.toString());
		assertEquals(2, (int) m.get("meow"));
		assertEquals(4, (int) m.get("quack"));
		assertEquals(5, (int) m.get("baa"));
		assertEquals(7, (int) m.get("moo"));
		assertEquals(5, (int) m.get("oink"));
		assertEquals(8, (int) m.get("bow wow"));
		assertEquals(12, (int) m.get("arf"));
		assertEquals(2, (int) m.get("neigh"));
		m.put("oink", m.get("oink")-1);
		assertEquals(4, (int) m.get("oink"));

		System.out.println("\n--- after changing value of oink: \n");
		System.out.println( m.toString());
		assertEquals(true, m.contains("baa"));

		m.remove("baa");

		System.out.println("removed baa");
		System.out.println("\n--- after removing sheep:\n");
		System.out.println( m.toString());

		assertEquals(false, m.contains("baa"));

		try {
			m.get("baa");
			fail("Should get a NoSuchElementException");
		} catch (NoSuchElementException nsee) {
			//		 do nothing because we expect m.get("baa") to throw this exception
		}

		m.remove("meow");

		assertEquals(false, m.contains("meow"));
		System.out.println("removed meow");
		System.out.println(m.toString());


		m.remove("moo");
		assertEquals(false, m.contains("moo"));
		System.out.println("removed moo");
		System.out.println(m.toString());
//=================== Further down, I have added more stuff to test my methods more thoroughly=========================
		System.out.println("########################NOTHING BELOW#############################");
		System.out.println("Further down, I have added more stuff to test my methods more thoroughly");
		assertEquals(true, m.contains("oink"));
		assertEquals(true, m.contains("neigh"));
		assertEquals(true, m.contains("quack"));
		assertEquals(true, m.contains("bow wow"));
		assertEquals(true, m.contains("arf"));
		
		
		
		m.put("baa",5);
		m.put("moo",7);
		m.put("meow",2);
		m.put("bcd",10);
		m.put("bab",13);
		m.put("baa",5);
		m.put("bac",5);
		m.put("bcx",5);
		m.put("bcf",5);
		m.put("bcz",5);
		m.put("bad",5);
		m.put("bae",5);
		m.put("baf",5);
		m.put("bag",5);
		m.put("bafa", 5);
		
		System.out.println("the new big tree is here:");
		System.out.println(m.toString());
		m.remove("bcd");
		
		System.out.println("after removing bcd:");
		System.out.println(m.toString());
		m.remove("bag");
		System.out.println("after removing bag:");
		System.out.println(m.toString());
		
		
		m.remove("bcf");
		System.out.println("after removing bcf:");
		System.out.println(m.toString());
		
		
		m.remove("arf");
		System.out.println("after removing arf:");
		System.out.println(m.toString());
		
		m.remove("oink");
		System.out.println("after removing oink:");
		System.out.println(m.toString());
		assertEquals(false, m.remove("cant"));
		
		
		
	}


}
