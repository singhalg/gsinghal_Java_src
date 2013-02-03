package lab9;


import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Test;

public abstract class MapTestArray {

	public abstract Map<String,Integer> createMap();

	@Test
	public void testAllMethods() {
		Map<String,Integer> m = createMap();
		System.out.println("test 1");
		
		m.put("meow",2);
		
assertEquals(2, (int) m.get("meow"));
		
		System.out.println("test 2");
		System.out.println(m.toString());
		
	
		m.put("bow wow",8);

		assertEquals(2, (int) m.get("meow"));
		assertEquals(8, (int) m.get("bow wow"));
		
		
		System.out.println("added cats and dogs:\n"); 
		System.out.println(m.toString());
		m.put("oink",5);
		m.put("quack",4);
		m.put("baa",5);
	

		System.out.println("\n--- after adding pigs, ducks, and sheep:\n"+ m.toString());

		m.put("arf",12);
		m.put("neigh", 2);
		m.put("moo",7);	    
		System.out.println("test 3");
		System.out.println("\n--- after adding little dogs, horses, and cows:\n"+ m.toString());

	}
}

