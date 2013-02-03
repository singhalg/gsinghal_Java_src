
public class prac {

	public static void main (String key){
		int m = 100;
		int hC = hash(hashCode(key));
		int step = stepHash(hashCode(key));
		System.out.println("hash value = " + hC);
		System.out.println("step = " + step);
		System.out.println("m = " +m );
		for (int i = 0 ; i < 10; i ++){
			int j = hC + i*step;
			
			System.out.println("i = " + i + "; j = " +j + "");
		}
	}
	
	public static int hashCode(String s){
		int A = 1952786893;
		int B = 367253;
		int v = B;

		for (int j = 0; j < s.length(); j++){
			char c = s.charAt(j);
			v = A * (v + (int)c + j) + B;
		}
		if (v < 0) v = -v;  //return a positive integer
		return v;
	}

	// Primary hash function using the multiplication method
	//   Computes hashKey*a - floor(k*a) and keeps most two significant digits
	//   of the resulting fraction to return 
	//
	// The below assumes that the hash table is called table

	static int hash(int hashKey){
		double a = (Math.sqrt(5.0)-1)/2;
		double frac = (hashKey * a) - (int)(hashKey * a);
		int m = 100;
		int hashValue = (int) (m * frac);
		return hashValue;                                
	}

	// 
	// Secondary hash function which is guaranteed to be odd.  To esnure that all
	// table entries are visited, this requrires that tableSize is a power of 2. 

	static int stepHash(int hashKey){
		int m = 100;
		int step = (hashKey % (m/2 - 1));
		return 2*step + 1;
	}
}
