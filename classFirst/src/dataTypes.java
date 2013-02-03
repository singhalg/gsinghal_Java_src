public class dataTypes {
	
	private static double h = pyth(5.0, 6.0);
	public static double getH() {
		return h;
	}
	public static void main(String args[]) {
		double a = 3.00;

		double b = 2.0;
		
		System.out.println("a-b = " + (a-b));
	//	System.out.println("(a*b++) = " + a*b++ );
	//	System.out.println("value of b is : "+b);
	//	System.out.println("the value of a(" +a + ") * b(" +b+") is : " + a*(b++));
	//	System.out.println("now the value of b is " +b);
/*
		int j = 3;
		int k = 5;
		System.out.println(1/j);
		System.out.println(1.0/j);
		System.out.println(1/( (double)j) );
		System.out.println((double) (1/(j)) );
		System.out.println((double) 1/(j) );
		System.out.println( (double) j/ ((double)k) );
		System.out.println(  j/ ((double)k) );
		System.out.println( (double) j/ (k) );
		System.out.println( (double) (j/ k) );
		
		
		Integer test = new Integer(77);
		System.out.println(test);
		System.out.println(100/test.intValue());
		System.out.println(100/test.doubleValue());
		System.out.println(100/test);
		System.out.println(100/(double)test);
		System.out.println(test.toString());
		
		String str = "77";
		double dStr = Double.valueOf(str);
		System.out.println((dStr * 3));
		boolean ans;
		double a;
		int b;
		double val;
		
		String h = "hi";
		print("h = " + h);
		print("h = " + dataTypes.h);
		
		System.out.println("2 to power 0 is :" + Math.pow(2,0));
		// if (args.length == 2){
		val = square.pyth(Double.valueOf(args[0]), Double.valueOf(args[1]));
		// }
		System.out.println("Length of hypotenuse = " + val);
		// double mm = areaTr(3.0, 5.0);

		// System.out.println(mm);
*/
		/*
		 * a = 22.00;
		 * 
		 * b = 7;
		 * 
		 * double c = a / b; // System.out.println(c);
		 * 
		 * if (c > 4) { ans = true; } else { ans = false;
		 * 
		 * }
		 * 
		 * if (ans == true) { System.out.println(c); System.out.println(ans); }
		 * else {
		 * 
		 * System.out.println(ans);
		 * 
		 * } // System.out.println(methods.mm(3.0,4.0));
		 * 
		 * extraLine(); System.out.println(c); extraLine(); extraLine();
		 * System.out.println(a); extraLine(); extraLine();
		 * System.out.println(b); System.out.println(c);
		 * 
		 * }
		 * 
		 * public static void extraLine() { System.out.println(); } / double
		 * areaTr(double base, double height){ return (0.5baseheight); }
		 */
	}

	public static double pyth(double abc, double def) {
		return Math.sqrt(sq(abc) + sq(def));
	}

	private static double sq(double base) {
		return base * base;
	}
	public static void print(String line)
	{System.out.println(line);

	}

}
