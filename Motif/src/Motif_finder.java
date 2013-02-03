
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

// Motif_finder.java
//
// Syntax: Motif_finder <sequence_file>

public class Motif_finder {
	public static void main(String[] args) throws Exception {

		Vector<Sequence> seqs = Sequence.getSequencesFromFile(args[0]);

		double diff = 0;
		final int motif_length = 8;
		final int numNucleotides = 4;

		EM_motif em = new EM_motif(seqs);


		double[][] initPwm = em.initializePwm();

		double ic = 0;

		double[][]maxPwm = null;

		//Fill in code here
		do{
			ArrayList<Double>[] normExpNums = em.expectation(initPwm);
			maxPwm = em.maximize(normExpNums);
			diff = em.difference(initPwm, maxPwm);
			initPwm = maxPwm;
			//Fill in code here			
		}while(diff>0.001);


		System.out.println("The PWM is:");
		for(int m=0;m<numNucleotides;m++){
			for(int k=0;k<motif_length;k++){
				System.out.print(Math.exp(maxPwm[m][k])+"\t");//Math.exp
			}
			System.out.println();
		}


		//Fill in code here to calculate information content

		double[] information_content = new double[motif_length];
		for(int i = 0; i < motif_length; i++){
			ic = 0;
			for(int j = 0; j < numNucleotides; j++){
				ic +=   Math.exp(maxPwm[j][i])* (maxPwm[j][i] - Math.log(0.25));
			}
			information_content[i] = ic;

		}

		// normMaxPwm = maxPwm in normal space
		double[][] normMaxPwm = new double[numNucleotides][motif_length];
		for(int j = 0; j < numNucleotides; j++){
			for(int i = 0; i < motif_length; i++){
				normMaxPwm[j][i] = Math.exp(maxPwm[j][i]);
			}
		}

		// here i used transpose of normMaxPwm because maxPwm is maxPwm[numNucleoties][motif_length]
		Logo lg = new Logo(transpose(normMaxPwm), information_content);

		//To export the logo to a file
		lg.toFile("Logo.jpg");

		//To display the logo
		lg.displayLogo();

	}




	/**
	 * String representation of 2D array
	 * @param array
	 * @return
	 */
	public static String toString(double[][] array){
		String abc = "";
		for(int i = 0 ; i <array.length; i++){
			for(int j = 0 ; j < array[i].length; j++){
				abc+= array[i][j] + "  ";
			}
			abc+='\n';
		}
		return abc;
	}

	/**
	 * 
	 * @param array
	 * @returns transpose of a 2D array
	 */
	public static double[][] transpose(double[][] array){
		double [][] transposed = new double[array[0].length][array.length];
		for(int i = 0 ; i <array.length; i++){
			for(int j = 0 ; j < array[i].length; j++){
				transposed[j][i]= array[i][j] ;
			}
		}
		return transposed;
	}

}
