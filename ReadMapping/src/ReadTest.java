//
// READTEST.JAVA
// A simple test bench for the ShortRead and DNASeq classes
// Calling syntax: ReadTest <reference_file> <read_file>

import java.io.IOException;
import java.util.Scanner;

class ReadTest {

	public static void main(String [] args)
	{
		DNASeq reference = null;
		ShortRead[] reads = null;

		// Read the reference sequence
		try {
			String title = DNASeq.readFastaTitle(args[0]);

			System.out.println("Reading reference " + title);

			reference = DNASeq.readFastaSeq(args[0]);

			System.out.println("Read sequence of length " + 
					reference.length());

		} catch (IOException e) {
			System.out.println("Failed to read reference sequence");
		}

		// read the short read file
		try {
			Scanner readFile = ShortRead.openFastqFile(args[1]);
			reads = ShortRead.readFastqReads(readFile, 10);

			System.out.println("Read " + reads.length + " reads");
		} catch (IOException e) {
			System.out.println("Failed to read short reads");
		}

		//
		// Examples of how to print and to manipulate reads and sequences
		//

		
		
		
		
		
		System.out.println("" + reference);

		System.out.println("" + reference.getReverseComplement());

		System.out.println("" + reference.getSubseq(0, 14).toString().charAt(0));
		System.out.println("" + reference.getSubseq(3025, 30));
		System.out.println("");
		System.out.println("ddsds" + reads[4].errProbs());
		System.out.println("");
		System.out.println("" + reads[4].getReverseComplement());
		
		double [][]anArray = new double[4][4]; 
		
		anArray[0][0] = 1;
		anArray[0][1] = 1;
		anArray[0][2] = 1;
		anArray[0][3] = 1;
		
		anArray[3][0] = 12;
		anArray[3][1] = 1;
		anArray[3][2] = 5;
		anArray[3][3] = 7;
		
		anArray[2][0] = 2;
		anArray[2][1] = 3;
		anArray[2][2] = 4;
		anArray[2][3] = 1;
		
		
		System.out.println("sum = "+ sum2DArray(anArray));
		
		
		float[] errorProbab = reads[4].errProbs();
		for(int k = 0; k<errorProbab.length; k++ ){
			System.out.println(errorProbab[k]);
		}
	}
	
	private static double sum2DArray(double[][] arr2D){
		double sum = 0;
		int n = 0;
		while(n<2){
			if(arr2D[n].length>0){
				sum+= sumArray(arr2D[n]);
				n++;
			}
		}
		
		return sum;
	}

	private static double sumArray(double[] arr){
		double sum = 0;
		for (int m = 0; m <arr.length; m++ ){
			sum += arr[m];
		}
		return sum;

	}
	
}
