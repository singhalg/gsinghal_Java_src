package compbio;
import java.util.Random;

public class EM {

	// the number of experiments, types of experiments, and the number of possible results

	// each experiment is a set of rolls of one of the two types of dice

	// numTypes  =  the die type

	// results = number of rolls with each side
	private int numExperiments, numTypes, numResults;

	// the array of experiment data
	//counts[i][j] = how many times we saw result j in experiment i . 


	private int[][] counts;

	// array of probabilities for picking each type
	protected double[] typeProb;

	// 2D array for the probability of each result for each type
	// dimensions are numTypes and numResults
	protected double[][] resultProb;

	/*
	 * Constructor for EM Solver
	 * Takes in the number of types and the counts array.
	 * The initial probabilities are randomly generated
	 */
	public EM(int numTypes, int[][] counts) {
		this(numTypes, counts, getRandomProbabilities(numTypes,
				counts[0].length), getRandomProbabilities(numTypes));
	}

	/*
	 * Constructor for EM Solver
	 * Takes in the number of types, the counts array
	 * and the initial probabilities
	 */
	public EM(int numTypes, int[][] counts, double[][] startResultProb,
			double[] startTypeProb) {
		this.numTypes = numTypes;
		this.counts = counts;
		// counts is a 2D array, with the first dimension
		// with a length equal to the number of experiments
		numExperiments = counts.length;

		// the second dimension is the number of different
		// possible results
		numResults = counts[0].length;
		typeProb = startTypeProb;
		resultProb = startResultProb;
	}

	/*
	 * Based on the current probabilities for each of the die, calculates
	 * the posterior probability of each type of dice for each experiment
	 * 
	 * i.e. in the returned array, cell[i][j] should be the
	 * probability that experiment i used type j
	 */

	// prior = weights of each side of the dice. One dice is fair, and another dice is weighted with 6 coming up 50% of the time. 
	public double[][] calculatePosteriors() {
		double[][] posterior = new double[numExperiments][numTypes];
		double[] probProd = new double[numTypes]; // probability of result given the hypothesis or Pr(D|H) 
		double logPosterior ;
		// TODO: INSERT CODE HERE
		for (int i = 0; i < posterior.length; i++){ // iterate for each experiment (each experiment is a set of rolls)
			for (int j = 0; j < posterior[i].length; j++){ // iterate for each type of dice or dna; this loop iterates over both the types and calculates the likelihood of result given the hypothesis
				double temp = Math.log(1.0);
				for (int k = 0; k < numResults; k++){ // iterate for each result; in case of dice each result would be the number of counts for a face of the dice
					temp = temp + counts[i][k]* Math.log(resultProb[j][k]);
				}
				probProd[j] = temp; 
			}
			for (int j = 0; j < posterior[i].length; j++){ // now iterating over both the types to get the posterior prob for both types.
				double logNum =   Math.log(typeProb[j]) +  probProd[j];
				double [] logDeno =  {  Math.log(typeProb[j])+ probProd[j] , Math.log(typeProb[1-j]) + probProd[1-j] }; // only works for two types
				logPosterior = logNum - (logsum(logDeno));
				posterior[i][j] = Math.exp(logPosterior);
			}
		}
		return posterior;
	}

	/*
	 * Given the posterior probabilities for all the experiments, 
	 * this method calculates the expected counts for each experiment,
	 * type and result
	 * 
	 * i.e. in the returned array, cell[i][j][k] should be the 
	 * expected number of times in experiment i that you'd expect
	 * result k with type j 
	 */




	public double[][][] calculateExpectedCounts(double[][] posterior) {
		double[][][] expected = new double[numExperiments][numTypes][numResults];
		// TODO: INSERT CODE HERE

		for(int i = 0; i < numExperiments; i++){
			for (int j = 0; j< numTypes; j++){
				for( int k = 0; k < numResults; k++){
					expected[i][j][k] = (posterior[i][j]) + Math.log(counts[i][k]);
				}
			}
		}

		return expected;
	}

	/* 
	 * Given the expected counts for all of the experiments, calculate
	 * the total expected counts
	 * 
	 * i.e. cell[j][k] of the returned array should be the expected number of times
	 * you'd expect result k with type j
	 */
	public double[][] calculateExpectedTotals(double[][][] expectedCounts) {
		double[][] expectedTotal = new double[numTypes][numResults];
		// TODO: INSERT CODE HERE

		for(int j = 0; j < numTypes; j++){
			for(int k = 0; k < numResults; k++){
				int i = 0;
				double [] eC = new double[numExperiments];
				while(i<numExperiments){
					eC[i] = expectedCounts[i][j][k];
					i++;
				}
				expectedTotal[j][k] = logsum(eC);
			}
		}

		return expectedTotal;
	}

	/*
	 * The Maximization Step for Result probabilites
	 * Takes the expected counts for each type and each result as a parameter
	 * Sets the resultProb variables to new maximized variables
	 * 
	 * Returns the change between the values of resultProb before and after maximization
	 * (which is used to measure convergence)
	 */
	public double maximizeWeights(double[][] expectedTotal) {
		double diff = 0.0;
		// TODO: INSERT CODE HERE
		double[] sumweights = new double[numTypes];
		double[][] newPrior = new double[numTypes][numResults];
		for(int j = 0; j < numTypes; j++){
			int k = 0;
			double [] weights = new double[numResults];
			while(k<numResults){
				weights[k] = expectedTotal[j][k];
				k++;
			}
			sumweights[j] = logsum(weights);
		}

		for(int j = 0; j<numTypes; j++){
			for(int k = 0; k< numResults; k++){
				newPrior[j][k] = Math.exp(expectedTotal[j][k] - sumweights[j]);
			}
		}

		for(int a = 0; a < numTypes; a++){
			for(int b = 0; b < numResults; b++){
				diff +=  Math.abs(Math.exp(newPrior[a][b]) - Math.exp(resultProb[a][b]));  // i m not sure about this
			}
		}
		//diff = Math.abs(Math.exp(newPrior[0][0]) - Math.exp(resultProb[0][0]));  // i m not sure about this
		resultProb = newPrior;

		return diff;
	}

	/*
	 * Maximization Step for Type probability
	 * Takes the posterior probability values and 
	 * changes the typeProb array to reflect the maximized
	 * values
	 */
	public void maximizeDistribution(double[][] posterior) {

		double[] posteriorForAType = new double[numExperiments];// this is an array which contains posterior probs for a type (Fair or loaded) for all the experiments
		double[] sumPosterior = new double[numTypes]; // this is for keeping the logsum of posteriorForAType
		double[] newTypeProb = new double[numTypes]; 

		for(int j = 0; j< numTypes; j++){
			for (int i = 0; i < numExperiments; i++){
				posteriorForAType[i] = posterior[i][j];
			}
			sumPosterior[j] = logsum(posteriorForAType);
		}

		double totalType = logsum(sumPosterior);

		for(int j = 0; j<numTypes; j++){
			newTypeProb[j] = Math.exp(sumPosterior[j] - totalType); 
		}
		
		
		typeProb = newTypeProb;


	}



	/* 
	 * Runs Expectation Maximization until 
	 * the change in values is less than the goal amount
	 */
	public void solve(double goal) {
		double diff;
		do {
			double[][] posterior = calculatePosteriors();
			double[][][] expectedCounts = calculateExpectedCounts(posterior);
			double[][] expectedTotal = calculateExpectedTotals(expectedCounts);
			diff = maximizeWeights(expectedTotal);
			maximizeDistribution(posterior);
		} while (diff > goal);
	}

	/*
	 * Calculates the log sum of the log values
	 * in the array. If a value is too small, it
	 * is ignored. Used to prevent underflow
	 * 
	 * if you have an array of numbers ( such as 0.003, 0.004) and logp is the array of their logs( -5.809, -5.521) then this method returns the log of the sum of the original numbers (ln(0.007) = -4.96. 
	 * 
	 * 
	 */
	public static double logsum(double[] logp) {
		double max = logp[0];
		for (int x = 1; x < logp.length; x++)
			if (logp[x] > max)
				max = logp[x];
		//System.out.println("max = " + max);
		double sum = 0.0;
		for (int x = 0; x < logp.length; x++)
			if (logp[x] > max - 50.)
				sum += Math.exp(logp[x] - max);
		//System.out.println("sum = " +sum);
		sum = Math.log(sum) + max;
		return sum;
	}

	/*
	 * Creates random initial probabilities for resultProb
	 */
	private static double[][] getRandomProbabilities(int numTypes,
			int numResults) {
		Random r = new Random();
		double[][] probs = new double[numTypes][numResults];
		for (int i = 0; i < numTypes; i++) {
			double sum = 0;
			for (int j = 0; j < probs[i].length; j++) {
				probs[i][j] = r.nextDouble();
				sum += probs[i][j];
			}
			// normalize 
			for (int j = 0; j < probs[i].length; j++) {
				probs[i][j] /= sum;
			}
		}
		return probs;
	}

	/*
	 * Creates random initial probabilities for typeProb
	 */
	private static double[] getRandomProbabilities(int numTypes) {
		Random r = new Random();
		double[] probs = new double[numTypes];

		double sum = 0;
		for (int j = 0; j < numTypes; j++) {
			probs[j] = r.nextDouble();
			sum += probs[j];
		}
		// normalize 
		for (int j = 0; j < numTypes; j++) {
			probs[j] /= sum;
		}

		return probs;
	}
}
