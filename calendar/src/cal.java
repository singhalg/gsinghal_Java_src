
public class cal {
//	public static void main (String agrs[]){
//
//
//		int date;
//		double temp =  35.5*3 ;
//		int month;
//		int year ;	
//		double logex = Math.pow((Math.exp(Math.log (10))),10);
//
//		System.out.println("logex = "+logex);
//
//		moreExLines();
//
//		String abc = "alpha beta gamma";
//		printArgument(abc);
//
//		System.out.println(temp);
//		
//	
//		for (year = 2010; year<2012; year ++){
//			for (month = 1; month<13; month ++){
//				//	if (month == 1){
//				for (date = 1; date<32; date++){
//
//
//
//					System.out.println("Today the date is : "+month+"/"+date+"/" +year);
//
//					//		}else if (month ==2 ){for (date = 1; date<29; date++){
//				}
//			}
//		}
//	}

	public static void main (String args[]){
		int m = 100;
		int hC = hash(hashCode(args[0]));
		int step = stepHash(hashCode(args[0]));
		System.out.println("hash value = " + hC);
		System.out.println("step = " + step);
		System.out.println("m = " +m );
		for (int i = 0 ; i < 100; i ++){
			int j = (hC + i*step)%m;
			
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
	
	
	public static void exLine(){
		System.out.println();
	}
	public static void moreExLines(){
		exLine();
		exLine();
		exLine();

	}
	public static void printArgument(String argument)
	{System.out.println(argument);

	}

	

}

