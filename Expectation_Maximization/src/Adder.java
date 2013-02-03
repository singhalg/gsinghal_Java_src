
public class Adder {
	public static int calc(String Operation, int one, int two){
		if (Operation.equals("plus")){
			return (one + two);
		}
		else if (Operation.equals("minus")){
			return (one - two);
		}
		else throw new IllegalArgumentException("Argument not valid");
	}

	public static void main (String args[]){
		int num;
		num = calc(args[0], Integer.valueOf(args[1]).intValue(), Integer.valueOf(args[2]).intValue());
		System.out.println(num);
	}
}
