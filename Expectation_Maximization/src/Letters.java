
public class Letters {
	public static void letter(String line){
		char [] charline ;
		charline = line.toCharArray();
		int lettercount = 0;
		double wc = 1;
		for (int i = 0; i < charline.length; i++ ){
			if (Character.isLetter(charline[i]) == true ){
				lettercount++;
			}
			else {
				wc++;
			}
		}
		System.out.println("Letters: " + lettercount );
		System.out.println("Average Length: " + lettercount/wc);
	}
	
	public static void RootandCosine(String args[]){
		double d = Double.valueOf(args[0]).doubleValue();
		System.out.println(Math.cos(d)+ "   "+  Math.sqrt(d));
	}
	
	public static void main (String args[]){
		//letter(args[0]);
		int a = 5;
		int b = 6;
		double c = Math.sqrt(a*b);
		System.out.println(c);
		Fibonacci(15);
	}
	
	public static void Fibonacci(int n){
		int [] fibo  = new int[n];
		fibo[0] = 1;
		fibo[1] = 1;
		for (int a = 2; a < fibo.length;a++ ){
			fibo[a] = fibo[a-1] + fibo[a-2];
			
		}
		
		for (int a = 0; a < fibo.length;a++ ){
			System.out.print(fibo[a] + "  ");
		}
		
	}
	
	

}
