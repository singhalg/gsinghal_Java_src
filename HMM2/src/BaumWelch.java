import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class BaumWelch {
	private static Random r = new Random();

	public static HiddenMarkovModel infer(String s, int numStates,
			double pChange) {
		BaumWelch bw = new BaumWelch(s, numStates);
		double change, oldProb = Double.NEGATIVE_INFINITY;
		do {
			double newProb = bw.maximize();
			change = newProb - oldProb;
			oldProb = newProb;
		} while (change > pChange);
		return bw.hmm;
	}

	private HiddenMarkovModel hmm;

	private HmmState[] states;

	private String observations;

	private char[] alphabet;

	private int N, T;

	public BaumWelch(String s, int numStates) {
		alphabet = getAlphabet(s);
		observations = s;
		T = observations.length();
		N = numStates;

		states = new HmmState[N];
		for (int i = 0; i < N; i++) {
			states[i] = new HmmState(String.valueOf(i));
			states[i].setStartProbability(0.5);
			double[] emission = getNormalizedRandomArray(alphabet.length);
			for (int j = 0; j < alphabet.length; j++) {
				states[i].addEmissionProbability(alphabet[j], emission[j]);
			}
			double[] transition = getNormalizedRandomArray(N);
			for (int j = 0; j < N; j++) {
				states[i].addTransitionProbability(String.valueOf(j),
						transition[j]);
			}
		}

		hmm = new HiddenMarkovModel(states, alphabet);
	}

	public double maximize() {

//############################# CALCULATIONS IN NORMAL SPACE ######################################################33333333

		//
		//		double alpha[][] = new double [N][T];
		//		double beta[][] = new double[N][T];
		//
		//// Calculating alpha
		//		for (int i = 0; i < N; i++){
		//			alpha[i][0] = states[i].getStartProbability()*states[i].getEmissionProbability(observations.charAt(0));
		//		}
		//
		//		for (int j = 1; j < T; j++){
		//			for (int i = 0; i < N; i++){
		//				double sumAlpha = 0;
		//				for (int k = 0 ; k < N; k++){
		//					sumAlpha += alpha[k][j-1] * states[k].getTransitionProbability(states[i].getName());
		//				}
		//				alpha[i][j] = sumAlpha * states[i].getEmissionProbability(observations.charAt(j));
		//			}
		//		}
		//
		//		
		//// Calculating beta		
		//		for (int i = 0; i < N ; i++){
		//			beta[i][T-1] = 1;
		//		}
		//
		//		for (int j = T-2; j>=0; j--){
		//			for (int i = 0; i < N; i++){
		//				double sumBeta = 0;
		//				for (int k = 0; k < N; k++){
		//					sumBeta += beta[k][j+1]*states[i].getTransitionProbability(states[k].getName())*states[k].getEmissionProbability(observations.charAt(j+1)); 
		//				}
		//				beta[i][j] = sumBeta ;
		//			}
		//		}
		//
		////Calculating gamma		
		//		
		//		double [][] gamma = new double [N][T];
		//
		//		for (int i = 0 ; i < N ; i++){
		//			for (int t = 0; t < T; t++){
		//
		//
		//				double ab = 0;
		//				for (int k = 0 ; k < N; k++){
		//
		//					ab+=alpha[k][t]*beta[k][t];
		//				}
		//				gamma[i][t] = (alpha[i][t]*beta[i][t]) / ab;
		//			}
		//
		//		}
		//
		//
		//// Summing gamma over all states for every time t
		//		double [] sumGammaoverN = new double [T] ;
		//
		//		for (int t = 0; t < T; t++){
		//			for (int i = 0; i < N; i++){
		//				sumGammaoverN[t]+= gamma[i][t];
		//
		//			}
		//			// sumGammaoverN[t] should be equal to 1;
		//		}
		//
		//		
		//// Summing gamma over all the time points (1 to T-1) for a state i		
		//		double [] sumGammaoverT = new double [N] ;
		//		for (int i = 0; i < N; i++){
		//			for (int t = 0; t < T-1; t++){
		//				sumGammaoverT[i]+= gamma[i][t];
		//			}
		//		}
		//		
		//// Summing gamma over all the time points (1 to T) for a state i		
		//		double [] sumGammaoverTT = new double [N] ;
		//		for (int i = 0; i < N; i++){
		//			for (int t = 0; t < T; t++){
		//				sumGammaoverTT[i]+= gamma[i][t];
		//
		//			}
		//			//System.out.println(sumGammaoverT[i]);
		//		}
		//
		//
		////Calculating Xi
		//		double [][][]xiNum = new double [N][N][T-1];
		//		double []xiDeno = new double [T-1];
		//
		//		for (int t = 0; t < T-1; t ++){
		//			for (int i = 0; i < N; i++){
		//				for (int j = 0; j < N; j++){
		//					xiNum[i][j][t] = alpha[i][t]*states[i].getTransitionProbability(states[j].getName())*states[j].getEmissionProbability(observations.charAt(t+1))*beta[j][t+1] ;
		//					xiDeno[t] += xiNum[i][j][t];
		//				}
		//			}
		//		}
		//
		//		double [][][]xi = new double [N][N][T-1];
		//		for (int t = 0; t < T-1; t ++){
		//			for (int i = 0; i < N; i++){
		//				for (int j = 0; j < N; j++){
		//					xi[i][j][t] = xiNum[i][j][t]/ xiDeno[t];
		//				}
		//			}
		//		}
		//
		//
		//		double [][] newTransProb = new double [N][N];
		//		for (int i = 0 ; i < N; i++){
		//			for (int j = 0 ; j < N; j ++){
		//				double sumXi = 0;
		//				for (int t = 0 ; t < T-1; t ++){
		//					sumXi += xi[i][j][t]; 
		//				}
		//				newTransProb[i][j] = sumXi/sumGammaoverT[i];
		//			}
		//		}
		//
		//
		//		double []newStartProb = new double [N];
		//		for (int i = 0; i < N; i++){
		//			newStartProb[i] = gamma[i][0];
		//		}
		//
		//		double [][] newEmissionProb = new double [N][alphabet.length];
		//		for ( int i = 0; i <N ; i ++){
		//			for (int k = 0;k < alphabet.length; k++){
		//				double numExp = 0;
		//				for (int t = 0; t < T; t++){
		//					if (observations.charAt(t)==(alphabet[k])){
		//						numExp += gamma[k][t]; 
		//					}
		//				}
		//				newEmissionProb[i][k] = numExp/sumGammaoverTT[i];
		//			}
		//		}
		//
		//		double diff = 0;
		//
		//		for (int i = 0 ; i < N; i++){
		//			for (int j = 0 ; j < N; j ++){
		//				diff += Math.abs ( newTransProb[i][j] - states[i].getTransitionProbability(states[j].getName())) ;
		//			}
		//
		//		}
		//
		//
		//		//Resetting probabilities to new ones
		//
		//
		//		for (int i = 0; i < N; i++){
		//			states[i].setStartProbability(newStartProb[i]);
		//		}
		//		for (int i = 0 ; i < N; i++){
		//			for (int j = 0 ; j < N; j ++){
		//				states[i].addTransitionProbability(states[j].getName(), newTransProb[i][j]);
		//			}
		//		}
		//
		//		for ( int i = 0; i <N ; i ++){
		//			for (int k = 0;k < alphabet.length; k++){
		//				states[i].addEmissionProbability(alphabet[k], newEmissionProb[i][k]);
		//			}
		//		}


		
// =============  CALCULATIONS IN LOG SPACE	============================================
		double alpha[][] = new double [N][T];
		double beta[][] = new double[N][T];

		// Calculating alpha for t = 0
		for (int i = 0; i < N; i++){
			alpha[i][0] = Math.log(states[i].getStartProbability()) + Math.log(states[i].getEmissionProbability(observations.charAt(0)));
		}
		// Calculating alpha for t = 1 to T
		for (int t = 1; t < T; t++){
			for (int i = 0; i < N; i++){
				double [] sumAlpha = new double[N];
				for (int k = 0 ; k < N; k++){
					sumAlpha[k] = alpha[k][t-1] + Math.log(states[k].getTransitionProbability(states[i].getName()));
				}
				alpha[i][t] = logsum(sumAlpha) + Math.log(states[i].getEmissionProbability(observations.charAt(t)));
			}
		}


		// Calculating beta		
		for (int i = 0; i < N ; i++){
			beta[i][T-1] = Math.log(1);
		}

		for (int t = T-2; t>=0; t--){
			for (int i = 0; i < N; i++){
				double [] sumBeta = new double [N];
				for (int k = 0; k < N; k++){
					sumBeta[k] = beta[k][t+1] +    Math.log(states[i].getTransitionProbability(states[k].getName()))  + Math.log(states[k].getEmissionProbability(observations.charAt(t+1))); 
				}
				beta[i][t] = logsum(sumBeta) ;
			}
		}

		//Calculating gamma		

		double [][] gamma = new double [N][T];

		for (int i = 0 ; i < N ; i++){
			for (int t = 0; t < T; t++){
				double [] ab = new double [N];
				for (int k = 0 ; k < N; k++){
					ab[k] = alpha[k][t] + beta[k][t];
				}
				gamma[i][t] = (alpha[i][t]+beta[i][t]) - logsum(ab);
			}
		}


		// Summing gamma over all states for each time t
		double [] sumGammaoverN = new double [T] ;

		for (int t = 0; t < T; t++){
			double []sumG = new double [N];
			for (int i = 0; i < N; i++){
				sumG[i]= gamma[i][t];

			}
			sumGammaoverN[t] = logsum(sumG);
			//System.out.println(Math.exp(sumGammaoverN[t]));// should be equal to 1;
		}


		// Summing gamma over all the time points (1 to T-1) for a state i		
		double [] sumGammaoverT = new double [N] ;
		for (int i = 0; i < N; i++){
			sumGammaoverT[i]= logsum(gamma[i], gamma[i].length-1);
		}

		// Summing gamma over all the time points (1 to T) for a state i		
		double [] sumGammaoverTT = new double [N] ;
		for (int i = 0; i < N; i++){
			sumGammaoverTT[i]= logsum(gamma[i]);
		}


		//Calculating Xi
		double [][][]xiNum = new double [N][N][T-1]; // numerator for calculating Xi
		double []xiDeno = new double [T-1];			// denominator for calculating Xi

		double [] xiNumSumOverj = new double[N];   // this is to do logsum 
		double [] xiNumSum = new double[N];

		for (int t = 0; t < T-1; t ++){
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++){
					xiNum[i][j][t] = alpha[i][t] + Math.log(states[i].getTransitionProbability(states[j].getName()))  + Math.log(states[j].getEmissionProbability(observations.charAt(t+1))) + beta[j][t+1] ;
					xiNumSum[j] = xiNum[i][j][t];
				}
				xiNumSumOverj[i] = logsum(xiNumSum); 
			}
			xiDeno[t] = logsum(xiNumSumOverj);
		}

		double [][][]xi = new double [N][N][T-1];
		double transposeXi[][][] = new double [T-1][N][N];  // transposeXi is used for calculating summation of Xi over all i and j

		for (int t = 0; t < T-1; t ++){
			for (int i = 0; i < N; i++){
				for (int j = 0; j < N; j++){
					xi[i][j][t] = xiNum[i][j][t]- xiDeno[t];
					transposeXi[t][i][j] = xi[i][j][t];
				}
			}
		}

		
		double [][] sumXiOverj = new double [T-1][N];
		double [] sumXiOverij = new double [T-1];
		for (int t = 0; t < T-1; t ++){
			for (int i = 0; i < N; i++){
				sumXiOverj[t][i]= logsum(transposeXi[t][i]);
			}
			sumXiOverij[t] = logsum(sumXiOverj[t]);
			//System.out.println(" sum Xi over i, j = "+Math.exp(sumXiOverij[t])); // this should be equal to 1
		}

		
		

//Calculating new Transition probabilities
		double [][] newTransProb = new double [N][N];
		for (int i = 0 ; i < N; i++){
			for (int j = 0 ; j < N; j ++){
				double [] sumXi = new double [T-1];
				for (int t = 0 ; t < T-1; t ++){
					sumXi[t] = xi[i][j][t]; 
				}
				newTransProb[i][j] = Math.exp(logsum(sumXi) - sumGammaoverT[i]);
			}
		}

		//Calculating new Start probabilities
		double []newStartProb = new double [N];
		for (int i = 0; i < N; i++){
			newStartProb[i] = Math.exp(gamma[i][0]);
		}
		//Calculating new Emission probabilities
		double [][] newEmissionProb = new double [N][alphabet.length];
		for ( int i = 0; i <N ; i ++){
			for (int k = 0;k < alphabet.length; k++){
				double []numExp = new double[T];
				int p = 0;
				for (int t = 0; t < T; t++){
					if (observations.charAt(t)==(alphabet[k])){
						numExp[p] = gamma[i][t];
						p++;
					}
				}
				if (p == 0){
				newEmissionProb[i][k] = 0;
				}
				else newEmissionProb[i][k] = Math.exp(logsum(numExp, p-1)- sumGammaoverTT[i]);
			}
		}

		double diff = 0;

		for (int i = 0 ; i < N; i++){
			for (int j = 0 ; j < N; j ++){
				diff += Math.abs ( newTransProb[i][j] - states[i].getTransitionProbability(states[j].getName())) ;
			}

		}


		//Resetting probabilities to new ones

		for (int i = 0; i < N; i++){
			states[i].setStartProbability(newStartProb[i]);
		}
		for (int i = 0 ; i < N; i++){
			for (int j = 0 ; j < N; j ++){
				states[i].addTransitionProbability(states[j].getName(), newTransProb[i][j]);
			}
		}

		for ( int i = 0; i <N ; i ++){
			for (int k = 0;k < alphabet.length; k++){
				states[i].addEmissionProbability(alphabet[k], newEmissionProb[i][k]);
			}
		}

//@TA : you can un-comment this and print sumGammaoverN and sumXiOverij for any t, here I have used t = 3		
		//System.out.println(" sum Xi over i, j = "+Math.exp(sumXiOverij[6]));
		//System.out.println(" sum gamma over i = "+Math.exp(sumGammaoverN[6]));
		return diff;
	}

	/*
	 * Calculates the log sum of the log values
	 * in the array. If a value is too small, it
	 * is ignored. Used to prevent underflow
	 */
	public static double logsum(double[] logp) {
		return logsum(logp, logp.length);
	}

	public static double logsum(double[] logp, int N) {
		double max = logp[0];
		for (int x = 1; x < N; x++)
			if (logp[x] > max)
				max = logp[x];
		double sum = 0.0;
		for (int x = 0; x < N; x++)
			if (logp[x] > max - 50.)
				sum += exp(logp[x] - max);
		sum = log(sum) + max;
		return sum;
	}

	private static double BASE = 2;

	private static double log(double d) {
		return Math.log(d) / Math.log(BASE);
	}

	private static double exp(double d) {
		return Math.pow(BASE, d);
	}

	private static char[] getAlphabet(String s) {
		Vector<Character> alphabetVector = new Vector<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!alphabetVector.contains(c))
				alphabetVector.add(c);
		}
		Collections.sort(alphabetVector);
		char[] alphabetArr = new char[alphabetVector.size()];
		for (int i = 0; i < alphabetArr.length; i++) {
			alphabetArr[i] = alphabetVector.get(i);
		}
		return alphabetArr;
	}

	private static double[] getNormalizedRandomArray(int n) {
		double[] arr = new double[n];
		double total = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = r.nextDouble();
			total += arr[i];
		}
		for (int i = 0; i < n; i++) {
			arr[i] /= total;
		}
		return arr;
	}




}
