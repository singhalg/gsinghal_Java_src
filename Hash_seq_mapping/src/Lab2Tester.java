import java.util.*;

public class Lab2Tester {

	public static void main(String args[]) throws Exception {
		BucketMapping table = new BucketMapping(4);

		Terminal.println("\nInserting key: cup");
		table.put("cup", new Integer(3));

		Terminal.println("\nInserting key: car");
		table.put("car", new Integer(1));

		Terminal.println("\nInserting key: bat");
		table.put("bat", new Integer(2));

		Terminal.println("\nInserting key: car");
		table.put("car", new Integer(4));

		Terminal.println("\nRemoving key: bat");
		table.remove("bat");

		Terminal.println("\nInserting key: cup");
		table.put("cup", new Integer(5));

		Terminal.println("\nInserting key: box");
		table.put("box", new Integer(6));

		Terminal.println("\nInserting key: box");
		table.put("box", new Integer(6));

		Terminal.println("\nInserting key: box");
		table.put("box", new Integer(6));


		Terminal.println("\nInserting key: abc");
		table.put("abc", new Integer(4));

		Terminal.println("\nInserting key: xcc");
		table.put("xcc", new Integer(4));


		outputBucket(table, "car");
		outputBucket(table, "bat");
		outputBucket(table, "cup");
		outputBucket(table, "box");
	}

	public static void outputBucket(BucketMapping table, String key){
		Terminal.println("\nGet the contents of the bucket associated with " + key);
		if (table.contains(key)) {
			Iterator loc = table.get(key);
			Terminal.print("   " + key + " --> ");
			while (loc.hasNext())
				Terminal.print(loc.next() + " ");
			Terminal.println("");
		}
		else
			Terminal.println("   " + key + " is not in use.");
	}
}

