
public class SquareSum {
	public static void MD(int size){
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
				System.out.print(array[j][i] + "  ");

			}
			System.out.println("");
		}



	}

	public static void main (String args[]){
		MD(Integer.parseInt(args[0]));
	}


}
