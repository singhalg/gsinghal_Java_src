
import java.util.ArrayList;
import java.util.Vector;

import java.util.Iterator;

// EM_motif.java

public class EM_motif {

	final String nucleotides = "ACGT";
	final int numNucleotides = nucleotides.length();
	Vector<Sequence> sequences;
	int num_seqs;
	final int motif_length = 8;
	double[][] pwm = new double[numNucleotides][motif_length];
	Iterator<Sequence> iterSeq = null;

	//Iterator<Sequence> iterSeq = sequences.iterator();


	//Constructor
	public EM_motif(Vector<Sequence> seqs){
		sequences = seqs;
		num_seqs = sequences.size();
		this.iterSeq = seqs.iterator();
	}


	//Returns the PWM
	public double[][] getPWM(){
		return pwm;
	}

	/**
	 * Initializes a new PWM 
	 * @return
	 */
	public double[][] initializePwm(){

		double[][]initPwm = new double[numNucleotides][motif_length];

		// just initilizing all the numbers to 0, so no null pointers occur.
		for(int i =0; i< initPwm.length; i++){
			for(int j = 0 ;  j < initPwm[i].length; j++){
				initPwm[i][j] = 0;
			}
		}

		// this loop fills the initPwm
		do{
			Sequence aSeq = iterSeq.next();
			for(int i =0; i < aSeq.length()- motif_length+1; i++){
				for(int j = 0; j < motif_length; j++){
					initPwm[nucleotides.indexOf(aSeq.getBody().charAt(i+j))][j]++;
				}
			}
		}while(iterSeq.hasNext());

		//calculating the Normalization factor which is the sum of base counts over all bases at a particular position in motif
		double []deno = new double[motif_length];
		for(int i = 0; i < motif_length; i++){
			for (int j = 0; j <numNucleotides; j++ ){
				deno[i]+= initPwm[j][i];
			}
		}

		// now normalizing the initPwm so that it returns probabilities in Log space and not counts
		for(int i =0; i< initPwm.length; i++){
			for(int j = 0 ;  j < initPwm[i].length; j++){
				initPwm[i][j] = Math.log(initPwm[i][j]/deno[i]);
			}
		}
		return initPwm;
	}





	/***
	 * 	
	 * @param initpwm - the initial PWM returned by initPwm method.
	 * @returns 
	 */
	public ArrayList<Double>[] expectation(double[][] initpwm){

		ArrayList<Double>[] expNums = new ArrayList[num_seqs]; // expNum is not a pwm
		for(int i =0; i< expNums.length; i++){ 
			expNums[i] = new ArrayList<Double>();
		}


		for(int i =0; i< expNums.length; i++){ // iterate over all the sequences
			String currentSeq = sequences.elementAt(i).getBody(); 

			for(int j = 0; j < currentSeq.length()-motif_length+1; j++){ // for each sequence, travel through all the 8 base pair windows
				double probs = 0; // we calculate the prodOfProb for every 8 base pair window in the sequence

				for(int k = 0; k < motif_length; k++){
					probs+= initpwm[ nucleotides.indexOf( currentSeq.charAt(j+k))]  [k];
				}
				expNums[i].add(probs);
			}
		}


		double[]normFactor = new double[num_seqs]; // this is sum over all the elements of expNums[i]
		for (int i = 0; i < num_seqs; i++){

			Double[] logProbs = new Double[expNums[i].size()];

			expNums[i].toArray(logProbs);
			normFactor[i] = logsum(logProbs);
		}


		ArrayList<Double>[] normExpNums = new ArrayList[num_seqs]; // expNum is not a pwm	
		for(int i =0; i< normExpNums.length; i++){ 
			normExpNums[i] = new ArrayList<Double>();
		}

		//now normalizing each element of expNums[i] wrt normFactor[i] and putting the result into a new ArrayList normExpNums
		for (int i = 0; i < num_seqs; i++){
			for(int j = 0; j < expNums[i].size(); j++){
				normExpNums[i].add(expNums[i].get(j)- normFactor[i]);
			}
		}
		return normExpNums;
	}

	/**
	 * 
	 * @param expArray
	 * @return
	 */
	public double[][] maximize(ArrayList<Double>[] expArray){
		double[][] maxPwm = new double[numNucleotides][motif_length]; 
		// just initializing a new maxPwm
		for(int i =0; i< maxPwm.length; i++){
			for(int j = 0 ;  j < maxPwm[i].length; j++){
				maxPwm[i][j] = 0;
			}
		}

		for(int i =0; i< expArray.length; i++){ // iterate over all the sequences
			String currentSeq = sequences.elementAt(i).getBody(); 
			for(int j = 0; j < currentSeq.length()-motif_length+1; j++){ // for each sequence, travel through all the 8 base pair windows
				String aMotif = currentSeq.substring(j, j+motif_length);
				for(int k = 0 ; k < aMotif.length(); k++){
					Double[] logArray = { maxPwm[nucleotides.indexOf(aMotif.charAt(k))][k], expArray[i].get(j)} ;
					maxPwm[nucleotides.indexOf(aMotif.charAt(k))][k] = logsum(logArray);
				}
			}
		}

		// sumMaxPerPos is the sum of maxPwm elements over all bases for each motif position
		double[] sumMaxPerPos  = new double[motif_length];
		for(int i = 0; i < motif_length; i++){
			Double[]maxAtBasesPerPos = new Double[numNucleotides];
			for(int k = 0; k < numNucleotides; k++){
				maxAtBasesPerPos[k]=maxPwm[k][i];
			}
			sumMaxPerPos[i] = logsum(maxAtBasesPerPos);
		}
		
		//normalizing maxPwm
		for(int i = 0; i < motif_length; i++){
			for(int k = 0; k < numNucleotides; k++){
				maxPwm[k][i]= maxPwm[k][i]-sumMaxPerPos[i];
			}
		}
		return maxPwm;
	}

	/**
	 * 
	 * @param oldPwm is  initPwm 
	 * @param newPwm is maxPwm
	 * @return 
	 */
	public double difference(double[][] oldPwm, double[][]newPwm){
		double diff = 0;
		for (int i = 0; i< oldPwm.length; i++){
			for(int j = 0; j < oldPwm[i].length; j++){
				diff+= Math.abs( Math.exp(oldPwm[i][j])-Math.exp(newPwm[i][j]));
			}
		}
		return diff;
	}


	//Returns the index corresponding to the character given.
	private static int getIndex(char c) {
		switch (c) {
		case 'A':
		case 'a':
			return 0;
		case 'C':
		case 'c':
			return 1;
		case 'G':
		case 'g':
			return 2;
		case 'T':
		case 't':
			return 3;
		default:
			return 4;
		}
	}


	public static double logsum(Double[] logp) {
		double max = logp[0];
		for (int x = 1; x < logp.length; x++)
			if (logp[x] > max)
				max = logp[x];
		double sum = 0.0;
		for (int x = 0; x < logp.length; x++)
			if (logp[x] > max - 50.)
				sum += Math.exp(logp[x] - max);
		sum = Math.log(sum) + max;
		return sum;
	}

}
