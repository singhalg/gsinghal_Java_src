//
// READALIGNER.JAVA
// Main program for short read aligner
// Calling syntax: ReadAligner <reference_file> <read_file>

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
class ReadAlignLinux {

	
	
	
	public static void main(String[] args) throws IOException
	{
		DNASeq reference = null;    // reference sequence

		// Read the reference sequence
		try {
			String title = DNASeq.readFastaTitle(args[0]);

			System.out.println("Reading reference " + title);

			reference = DNASeq.readFastaSeq(args[0]);

			System.out.println("Read reference of length " + 
					reference.length());

		} catch (IOException e) {
			System.out.println("Failed to read reference sequence");
			return;
		}

		Scanner readFile1;
		Scanner readFile2;

		// open the short read file
		try {
			// add the second argument to return only first 100k reads
			readFile1 = ShortRead.openFastqFile(args[1]);
			//readFile2 = ShortRead.openFastqFile(args[1]);
		} catch (IOException e) {
			System.out.println("Failed to read open read file " + args[1]);
			return;
		}




		while(true){
			HashMap<DNASeq, Set<Integer>> alignment = new HashMap<DNASeq, Set<Integer>>();
			//		int limit = 0;
			//
			//		while (limit<2)
			//		{
			ShortRead[] reads;   // array of short reads

			// Read the next set of reads from the readFile
			// (I do 10k at a time; adjust this size as needed).
			try {
				reads = ShortRead.readFastqReads(readFile1, 10000);

				//limit++;
				if (reads.length == 0){
					break;}

				System.out.println("Read " + reads.length + " reads");
			} catch (IOException e) {
				System.out.println("Failed to read short reads");
				return;
			}


			//
			// Your code is called here!
			//
			// 10000 is the initial capacity of this hash table, this is to prevent amortization costs

			ArrayList<ShortRead> readList = new ArrayList<ShortRead>();

			// first set of 6 hash tables
			HashMap<DNASeq, ArrayList<ShortRead>> hmfXXOO = new  HashMap<DNASeq, ArrayList<ShortRead>>();
			HashMap<DNASeq, ArrayList<ShortRead>> hmfOOXX = new  HashMap<DNASeq, ArrayList<ShortRead>>();
			HashMap<DNASeq, ArrayList<ShortRead>> hmfXOOX = new  HashMap<DNASeq, ArrayList<ShortRead>>();
			HashMap<DNASeq, ArrayList<ShortRead>> hmfOXXO = new  HashMap<DNASeq, ArrayList<ShortRead>>();
			HashMap<DNASeq, ArrayList<ShortRead>> hmfXOXO = new  HashMap<DNASeq, ArrayList<ShortRead>>();
			HashMap<DNASeq, ArrayList<ShortRead>> hmfOXOX = new  HashMap<DNASeq, ArrayList<ShortRead>>();


			ArrayList<ShortRead> readList1 = new ArrayList<ShortRead>();

			// second set of 6 hash tables
			HashMap<DNASeq, ArrayList<ShortRead>> hmrXXOO = new  HashMap<DNASeq, ArrayList<ShortRead>>();
			HashMap<DNASeq, ArrayList<ShortRead>> hmrOOXX = new  HashMap<DNASeq, ArrayList<ShortRead>>();
			HashMap<DNASeq, ArrayList<ShortRead>> hmrXOOX = new  HashMap<DNASeq, ArrayList<ShortRead>>();
			HashMap<DNASeq, ArrayList<ShortRead>> hmrOXXO = new  HashMap<DNASeq, ArrayList<ShortRead>>();
			HashMap<DNASeq, ArrayList<ShortRead>> hmrXOXO = new  HashMap<DNASeq, ArrayList<ShortRead>>();
			HashMap<DNASeq, ArrayList<ShortRead>> hmrOXOX = new  HashMap<DNASeq, ArrayList<ShortRead>>();





			// building hashmap for reads 
			for(int i = 0; i < reads.length; i++){

				DNASeq readSubSeqXXOO = reads[i].sequence().getSubseq(0, 14);
				DNASeq readSubSeqOOXX = reads[i].sequence().getSubseq(14, 14);
				DNASeq readSubSeqXOOX = new DNASeq(reads[i].sequence().getSubseq(0, 7).toString()+ reads[i].sequence().getSubseq(21, 7).toString());
				DNASeq readSubSeqOXXO = reads[i].sequence().getSubseq(7, 14);
				DNASeq readSubSeqXOXO = new DNASeq(reads[i].sequence().getSubseq(0, 7).toString() + reads[i].sequence().getSubseq(14, 7).toString());
				DNASeq readSubSeqOXOX = new DNASeq(reads[i].sequence().getSubseq(7, 7).toString() + reads[i].sequence().getSubseq(21, 7).toString());

				DNASeq readSubSeqRXXOO = reads[i].sequence().getSubseq(14, 14).getReverseComplement();
				DNASeq readSubSeqROOXX = reads[i].sequence().getSubseq(0, 14).getReverseComplement();
				DNASeq readSubSeqRXOOX = new DNASeq(reads[i].sequence().getSubseq(21, 7).getReverseComplement().toString()
						+reads[i].sequence().getSubseq(0, 7).getReverseComplement().toString());
				DNASeq readSubSeqROXXO = reads[i].sequence().getSubseq(7, 14).getReverseComplement();
				DNASeq readSubSeqRXOXO = new DNASeq(reads[i].sequence().getSubseq(21, 7).getReverseComplement().toString()
						+reads[i].sequence().getSubseq(7, 7).getReverseComplement().toString());
				DNASeq readSubSeqROXOX = new DNASeq(reads[i].sequence().getSubseq(14, 7).getReverseComplement().toString()+
						reads[i].sequence().getSubseq(0, 7).getReverseComplement().toString());

				
				// building seed table for forward strand of reads
				buildSeedTable(hmfXXOO, readSubSeqXXOO, reads[i]);
				buildSeedTable(hmfOOXX, readSubSeqOOXX, reads[i]);
				buildSeedTable(hmfXOXO, readSubSeqXOXO, reads[i]);
				buildSeedTable(hmfOXOX, readSubSeqOXOX, reads[i]);
				buildSeedTable(hmfXOOX, readSubSeqXOOX, reads[i]);
				buildSeedTable(hmfOXXO, readSubSeqOXXO, reads[i]);

				
				//System.out.println("build seed tables for forward strand");

				// now for reverse strand seeds =========
				buildSeedTable(hmrXXOO, readSubSeqRXXOO, reads[i]);
				buildSeedTable(hmrOOXX, readSubSeqROOXX, reads[i]);
				buildSeedTable(hmrXOXO, readSubSeqRXOXO, reads[i]);
				buildSeedTable(hmrOXOX, readSubSeqROXOX, reads[i]);
				buildSeedTable(hmrXOOX, readSubSeqRXOOX, reads[i]);
				buildSeedTable(hmrOXXO, readSubSeqROXXO, reads[i]);

				
				//System.out.println("build seed tables for reverse strand");







			}

			///   now doing alignment 
			// for every 47 bp window in reference, I check if a match occurs with any of the 12 hash tables
			// if a match occurs between the read and the reference at a position, then an entry is created in the 
			// hash table "alignment" with read sequence as the key and reference position (where the match occurs) as the value

			for (int j = 20 ; j < reference.length()-47; j++){ 
				DNASeq refSubSeqFXXOO =  reference.getSubseq(j, 14);
				DNASeq refSubSeqFOOXX =  reference.getSubseq(j+14, 14);
				DNASeq refSubSeqFOXXO =  reference.getSubseq(j+7, 14);
				DNASeq refSubSeqFOXOX =  new DNASeq(reference.getSubseq(j+7, 7).toString() + reference.getSubseq(j+21,7).toString()) ;
				DNASeq refSubSeqFXOXO =  new DNASeq(reference.getSubseq(j, 7).toString() + reference.getSubseq(j+14,7).toString()) ;
				DNASeq refSubSeqFXOOX =  new DNASeq(reference.getSubseq(j, 7).toString() + reference.getSubseq(j+21,7).toString()) ;

				DNASeq refSubSeqRXXOO =  reference.getSubseq(j, 14);
				DNASeq refSubSeqROOXX =  reference.getSubseq(j+14, 14);
				DNASeq refSubSeqROXXO =  reference.getSubseq(j+7, 14);
				DNASeq refSubSeqROXOX =  new DNASeq(reference.getSubseq(j+7, 7).toString() + reference.getSubseq(j+21,7).toString()) ;
				DNASeq refSubSeqRXOXO =  new DNASeq(reference.getSubseq(j, 7).toString() + reference.getSubseq(j+14,7).toString()) ;
				DNASeq refSubSeqRXOOX =  new DNASeq(reference.getSubseq(j, 7).toString() + reference.getSubseq(j+21,7).toString()) ;



				String X = "X";
				String N = "N";

				int forward = 1;
				int reverse = -1;

				doAlignment( reference, refSubSeqFXXOO, forward,j,hmfXXOO, alignment);
				doAlignment( reference, refSubSeqFOOXX, forward,j,hmfOOXX, alignment);
				doAlignment( reference, refSubSeqFXOXO, forward,j,hmfXOXO, alignment);
				doAlignment( reference, refSubSeqFOXOX, forward,j,hmfOXOX, alignment);
				doAlignment( reference, refSubSeqFXOOX, forward,j,hmfXOOX, alignment);
				doAlignment( reference, refSubSeqFOXXO, forward,j,hmfOXXO, alignment);


				
				
				
				doAlignment( reference, refSubSeqRXXOO, reverse,j,hmrXXOO, alignment);
				doAlignment( reference, refSubSeqROOXX, reverse,j,hmrOOXX, alignment);
				doAlignment( reference, refSubSeqRXOXO, reverse,j,hmrXOXO, alignment);
				doAlignment( reference, refSubSeqROXOX, reverse,j,hmrOXOX, alignment);
				doAlignment( reference, refSubSeqRXOOX, reverse,j,hmrXOOX, alignment);
				doAlignment( reference, refSubSeqROXXO, reverse,j,hmrOXXO, alignment);

			}

			
			// for every read in the read set (1000 reads), check which reads have a match in the alignment table
			// if a match is found, the unique placement and confidence is calculated
			for(int i = 0; i < reads.length; i++){
				if(alignment.containsKey(reads[i].sequence())){

					boolean unique = false; // whether this read has unique placement or not


					// an iterator for the positions (in reference sequence) where the read sequence contains the match in alignment table
					Iterator<Integer> positions =  alignment.get(reads[i].sequence()).iterator();

					// printing all the alignments
					//System.out.println(readsAll[i].sequence().toString() + "    " + alignment.get(readsAll[i].sequence()).toString());

					ArrayList<Integer>[] sortedAligns = new ArrayList[6];

					for(int z = 0; z < 6; z++){
						sortedAligns[z] = new ArrayList<Integer>();
					}








					Integer[] uniqueRef = new Integer[2]; // position of unique placement or two best placements

					uniqueRef[0] = -1;
					uniqueRef[1] = -1;
					//	LinkedList<Integer> bestAligns = new LinkedList<Integer>();


					do{
						Integer currentPos = positions.next();

						if (currentPos>0){

							sortedAligns[align(reads[i], reference.getSubseq(currentPos, 47))].add(currentPos);
						}
						else{
							sortedAligns[alignRev(reads[i], reference.getSubseq(Math.abs(currentPos.intValue()), 47))].add(currentPos);
						}

					}while(positions.hasNext());



					double arrPosteriorNum = 0;


					if(sortedAligns[0].size() == 1){
						// found unique best placement
						unique = true;
						uniqueRef[0] = sortedAligns[0].get(0);
						if(sortedAligns[0].get(0).intValue()>0){
							arrPosteriorNum = calcPosterior(reads[i], reference.getSubseq(sortedAligns[0].get(0), 47));
						}
						else{
							arrPosteriorNum = calcPosteriorRev(reads[i], reference.getSubseq(Math.abs(sortedAligns[0].get(0).intValue()), 47));
						}

					}
					else if (sortedAligns[0].size() > 1){
						// many best placements
						unique = false;
						uniqueRef[0] = sortedAligns[0].get(0);
						uniqueRef[1] = sortedAligns[0].get(1);
					}

					else if ((sortedAligns[0].size()== 0)&& (sortedAligns[1].size()==1)){
						unique = true;
						uniqueRef[0] = sortedAligns[1].get(0);
						if(sortedAligns[1].get(0).intValue()>0){
							arrPosteriorNum = calcPosterior(reads[i], reference.getSubseq(sortedAligns[1].get(0), 47));
						}
						else{
							arrPosteriorNum = calcPosteriorRev(reads[i], reference.getSubseq(Math.abs(sortedAligns[1].get(0).intValue()), 47));
						}
					}
					else if(sortedAligns[1].size() > 1){
						unique = false;
						uniqueRef[0] = sortedAligns[1].get(0);
						uniqueRef[1] = sortedAligns[1].get(1);
					}
					else if ((sortedAligns[1].size()== 0)&& (sortedAligns[2].size()==1)){
						unique = true;
						uniqueRef[0] = sortedAligns[2].get(0);
						if(sortedAligns[2].get(0).intValue()>0){
							arrPosteriorNum = calcPosterior(reads[i], reference.getSubseq(sortedAligns[2].get(0), 47));
						}
						else{
							arrPosteriorNum = calcPosteriorRev(reads[i], reference.getSubseq(Math.abs(sortedAligns[2].get(0).intValue()), 47));
						}
					}

					else if(sortedAligns[2].size() > 1){
						unique = false;
						uniqueRef[0] = sortedAligns[2].get(0);
						uniqueRef[1] = sortedAligns[2].get(1);
					}
					else if ((sortedAligns[2].size()== 0)&& (sortedAligns[3].size()==1)){
						unique = true;
						uniqueRef[0] = sortedAligns[3].get(0);
						if(sortedAligns[3].get(0).intValue()>0){
							arrPosteriorNum = calcPosterior(reads[i], reference.getSubseq(sortedAligns[3].get(0), 47));
						}
						else{
							arrPosteriorNum = calcPosteriorRev(reads[i], reference.getSubseq(Math.abs(sortedAligns[3].get(0).intValue()), 47));
						}
					}

					else if(sortedAligns[3].size() > 1){
						unique = false;
						uniqueRef[0] = sortedAligns[3].get(0);
						uniqueRef[1] = sortedAligns[3].get(1);
					}

					else if ((sortedAligns[3].size()== 0)&& (sortedAligns[4].size()==1)){
						unique = true;
						uniqueRef[0] = sortedAligns[4].get(0);
						if(sortedAligns[4].get(0).intValue()>0){
							arrPosteriorNum = calcPosterior(reads[i], reference.getSubseq(sortedAligns[4].get(0), 47));
						}
						else{
							arrPosteriorNum = calcPosteriorRev(reads[i], reference.getSubseq(Math.abs(sortedAligns[4].get(0).intValue()), 47));
						}
					}

					else if(sortedAligns[4].size() > 1){
						unique = false;
						uniqueRef[0] = sortedAligns[4].get(0);
						uniqueRef[1] = sortedAligns[4].get(1);
					}


					else if ((sortedAligns[4].size()== 0)&& (sortedAligns[5].size()==1)){
						unique = true;
						uniqueRef[0] = sortedAligns[5].get(0);
						if(sortedAligns[5].get(0).intValue()>0){
							arrPosteriorNum = calcPosterior(reads[i], reference.getSubseq(sortedAligns[5].get(0), 47));
						}
						else{
							arrPosteriorNum = calcPosteriorRev(reads[i], reference.getSubseq(Math.abs(sortedAligns[5].get(0).intValue()), 47));
						}
					}

					else if(sortedAligns[5].size() > 1){
						unique = false;
						uniqueRef[0] = sortedAligns[5].get(0);
						uniqueRef[1] = sortedAligns[5].get(1);
					}

					//int len =  sortedAligns[0].size() + sortedAligns[1].size()+ sortedAligns[2].size() + sortedAligns[3].size();

					//calculating posterior for all the placements

					double[][]arrPosterior = new double[6][];

					for(int g = 0; g< arrPosterior.length; g++){
						arrPosterior[g] = new double[sortedAligns[g].size()];
						for(int p = 0; p <arrPosterior[g].length; p++){
							arrPosterior[g][p]= 0;
						}
					}

					if(unique == true){ // calculating posterior only where there is a unique placement
						for(int q = 0; q< sortedAligns.length; q++){

							if(sortedAligns[q].size()>0){
								Iterator<Integer> saIter =   sortedAligns[q].iterator(); 
								int ind = 0;
								do{
									int refPos = saIter.next();
									if(refPos>=0){
										arrPosterior[q][ind] = calcPosterior(reads[i], reference.getSubseq(refPos, 47));
									}
									else{
										arrPosterior[q][ind] = calcPosteriorRev(reads[i], reference.getSubseq(Math.abs(refPos), 47));
									}
									ind++;
								}while(saIter.hasNext());
							}
						}

					}

					double finalPosterior = arrPosteriorNum/sum2DArrays(arrPosterior);

					///sumArray(arrPosterior);


					//double finalPosterior = arrPosterior[0]/sumArray(arrPosterior);

					int qualityScore = (int) ((-10)*Math.log10(1-finalPosterior));
					String[] sign = new String[2];
					sign[0] = "+";
					sign[1] = "+";
					if(uniqueRef[0]<0){
						sign[0] = "-";
					}
					if(uniqueRef[1]<0){
						sign[1] = "-";
					}

					
					String strContent = null;
					// printing the output
					if(unique){
						System.out.println(reads[i].name() + "    " + Math.abs(uniqueRef[0]) + "    " + sign[0] + "    " + qualityScore);
					}
					else{
						System.out.println(reads[i].name() + " !! " + Math.abs(uniqueRef[0]) + "    " + sign[0] + "    " + Math.abs(uniqueRef[1]) + "    " + sign[1]);
					}

//					String strFilePath = "C://eclipseOut//1000ReadsOut.txt";
//					
//					
//					try
//				     {
//				       /*
//				        * To append output to a file, use
//				        * FileOutputStream(String file, booean blnAppend) or
//				        * FileOutputStream(File file, booean blnAppend) constructor.
//				        *
//				        * If blnAppend is true, output will be appended to the existing content
//				        * of the file. If false, file will be overwritten.
//				        */
//				 
//				      FileOutputStream fos = new FileOutputStream(strFilePath, true);
//				      
//				 
//				       fos.write(strContent.getBytes());
//				 
//				      /*
//				       * Close FileOutputStream using,
//				       * void close() method of Java FileOutputStream class.
//				       * 
//				       */ 
//				 
//				       fos.close(); 
//				 
//				     }
//				     catch(FileNotFoundException ex)
//				     {
//				      System.out.println("FileNotFoundException : " + ex);
//				     }
//				     catch(IOException ioe)
//				     {
//				      System.out.println("IOException : " + ioe);
//				     }
//					
					
					
				}
				
				
				
				

			}
		}
	}




	private static void buildSeedTable(HashMap<DNASeq, ArrayList<ShortRead>> hmfXXOO, DNASeq readSubSeqXXOO, ShortRead aread){

		ArrayList<ShortRead> readList = new ArrayList<ShortRead>();
		if(! hmfXXOO.containsKey(readSubSeqXXOO)){ // create a new entry in hmXXOO if it doesnt contain the 
			// subsequence readSubSeq
			hmfXXOO.put(readSubSeqXXOO, readList);
			hmfXXOO.get(readSubSeqXXOO).add(aread);
		}
		else{									// if it already contains the subsequence, then just add
			// the current read to the list of reads mapped to the subsequence 
			hmfXXOO.get(readSubSeqXXOO).add(aread);
		}
	}


	private static void doAlignment(DNASeq reference, DNASeq refSubSeqRXOOX, int orientation, int j, HashMap<DNASeq, ArrayList<ShortRead>> hmrXOOX, HashMap<DNASeq, Set<Integer>> alignment){
		String N = "N";
		String X = "X";

		if(! (refSubSeqRXOOX.toString().contains(N) || refSubSeqRXOOX.toString().contains(X)) ){


			if(hmrXOOX.containsKey(refSubSeqRXOOX)){
				ArrayList<ShortRead> ListOfReads = hmrXOOX.get(refSubSeqRXOOX);
				Iterator<ShortRead> readsMatch = ListOfReads.iterator(); // readsMatch = all those reads which have this feature


				do{

					ShortRead aRead = readsMatch.next(); // aRead is one of the reads which shares a common feature
					// with the reference subsequence at j.
					if (orientation == -1){
						int mismatch = alignRev(aRead, reference.getSubseq(j-19, 47)); // mismatch  = number of mismatches
						if (mismatch <= 5){
							if(alignment.containsKey(aRead.sequence())){		
								alignment.get(aRead.sequence()).add(-(j-19));
							} 
							else{
								alignment.put(aRead.sequence(),new HashSet<Integer>() );
								alignment.get(aRead.sequence()).add(-(j-19));
							}
						}
					}
					else{

						int mismatch = align(aRead, reference.getSubseq(j, 47)); // mismatch  = number of mismatches
						if (mismatch <= 5){
							if(alignment.containsKey(aRead.sequence())){		
								alignment.get(aRead.sequence()).add(j);
							} 
							else{
								alignment.put(aRead.sequence(),new HashSet<Integer>() );
								alignment.get(aRead.sequence()).add(j);
							}
						}

					}



				}while(readsMatch.hasNext());
			}
		}
	}





	private static int align(ShortRead read, DNASeq subseq){
		int mismatch = 0;

		String DNA = subseq.toString();
		String readSeq = read.sequence().toString();
		for(int i = 0; i < DNA.length(); i++){
			if( readSeq.charAt(i)!= DNA.charAt(i)){
				mismatch++;
			}

		}
		return mismatch;
	}

	private static int alignRev(ShortRead read, DNASeq subseq){
		int mismatch = 0;

		String DNA = subseq.toString();
		String readSeq = read.sequence().getReverseComplement().toString();
		for(int i = 0; i < DNA.length(); i++){
			if( readSeq.charAt(i)!= DNA.charAt(i)){
				mismatch++;
			}

		}
		return mismatch;
	}



	private static double calcPosterior(ShortRead read, DNASeq ref){

		String DNA = ref.toString();
		String readSeq = read.sequence().toString();
		float[] errorProb = read.errProbs();

		double posterior = Math.log(1);
		for(int i = 0; i < DNA.length(); i++){
			if( readSeq.charAt(i)!= DNA.charAt(i)){
				posterior+= Math.log(errorProb[i]/3); 
			}
			else{
				posterior+= Math.log((1-errorProb[i]));

			}

		}
		return Math.exp(posterior);

	}


	private static double calcPosteriorRev(ShortRead read, DNASeq ref){

		String DNA = ref.toString();
		String readSeq = read.sequence().getReverseComplement().toString();
		float[] errorProb = read.errProbs();

		double posterior = Math.log(1);
		for(int i = 0; i < DNA.length(); i++){
			if( readSeq.charAt(i)!= DNA.charAt(i)){
				posterior+= Math.log(errorProb[i]/3); 
			}
			else{
				posterior+= Math.log((1-errorProb[i]));

			}

		}
		return Math.exp(posterior);

	}


	private static double sum2DArrays(double[][]arr){
		int q = 0;

		double sum =0;

		for(int a = 0; a< arr.length; a++){

			if((arr[a].length>0)&&(arr[a][0] != 0)){
				sum+=sumArray(arr[a]);
				q++;
			}

			if (q>1) break;
		}

		return sum;
	}

	private static double sumArray(double[] arr){
		double sum = 0;
		for(int a = 0; a< arr.length; a++){
			sum+=arr[a];
		}
		return sum;
	}









}
