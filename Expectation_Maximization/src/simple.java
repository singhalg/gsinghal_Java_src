
public class simple {

	private String name;
	private int gnome;
	private int numchr;
	
	public simple(String speciesname, int genome, int chromosomes){
		name = speciesname;
		gnome = genome;
		numchr = chromosomes;
		
	}
	
	public double avglen(int genome, int chromosomes){
		return ((genome*1.0)/chromosomes);
	}
	
	public int getgenome(){
		return gnome;
	}
	
	public int getchrnum(){
		return numchr;
	}
	
	public String getname(){
		return name;
	}
	
	public static void main (String args[]){
		int num;
		System.out.println("welcome very very much");
		num = calc("minus", 3, 4);
		System.out.println(num);
		SquareSum(4);
		Letters("i m loving it");
		simple species = new simple("human", 3000000, 30);
		int genome = species.getgenome();
		int chr = species.getchrnum();
		System.out.println(species.avglen(genome, chr));
	}

	public static int calc(String Operation, int one, int two){
		if (Operation=="plus"){
			return (one + two);
		}
		else if (Operation == "minus"){
			return (one - two);
		}
		else throw new IllegalArgumentException("Argument not valid");
	}

	public static void SquareSum(int size){
		int [][]array = new int[size][size];
		for (int i = 0; i < size; i++){
			array[0][i] = 1;
		}
		for (int j = 0; j < size; j++){
			array[j][0] = 1;
		}
		for (int j = 1; j < size; j++){
			for (int i = 1; i < size; i++){
				array[j][i] = array[j-1][i] + array[j][i-1];
			}
		}

		for (int j = 0; j < size; j ++){
			for(int i = 0; i<size; i++){
				System.out.print(array[j][i] + "\t");

			}
			System.out.println("\n");
		}



	}

	public static void Letters(String line){
		char [] charline ;
		charline = line.toCharArray();
//		System.out.println(charline);
//		System.out.println(charline.length);
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
	
	

}


