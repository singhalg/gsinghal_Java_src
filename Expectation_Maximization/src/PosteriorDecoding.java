import java.io.File;
import java.util.Scanner;
import java.util.Vector;


public class PosteriorDecoding {

	/*
	 * Usage: java PosteriorDecoding <name of dice data file>
	 * or 
	 * Usage: java PosteriorDecoding
	 * (input data from console, end by hitting control-D)
	 */
	public static void main(String[] args) throws Exception {

		final double SIXTH = 1.0 / 6.0;
		final double[][] dieProbabilities = {
				{ SIXTH, SIXTH, SIXTH, SIXTH, SIXTH, SIXTH },
				{ 0.1, 0.1, 0.1, 0.1, 0.1, 0.5 } };
		final double[] typeProbabilities = { 0.5, 0.5 }; // not actually used here

		final int numSides = 6;

		Vector<String> data = new Vector<String>();
		Scanner dataSource;
		if (args.length > 0) {
			// reads data from file
			dataSource = new Scanner(new File(args[0]));
		} else {
			// reads data from console
			dataSource = new Scanner(System.in);
		}

		// read each line
		while (dataSource.hasNextLine()) {
			data.add(dataSource.nextLine());
		}

		// calculate counts
		int[][] counts = new int[data.size()][numSides]; // data.size = no of lines = no of trials
		for (int j = 0; j < data.size(); j++) {
			String experiment = data.get(j);
			for (int i = 0; i < experiment.length(); i++) {
				String roll = experiment.substring(i, i + 1);
				int value = Integer.parseInt(roll) - 1;
				counts[j][value]++;
			}
		}

//		for (int i = 0; i< counts.length; i++){
//			for( int j = 0 ; j< counts[i].length; j++){
//				System.out.println("counts [" + i + "],[" + j + "] = " + counts[i][j] );
//			}
//		}
		
		EM em = new EM(2, counts, dieProbabilities, typeProbabilities);
		double[][] posterior = em.calculatePosteriors();
		String[] names = { "  FAIR  ", "WEIGHTED" };

		for (int i = 0; i < data.size(); i++) {
			System.out.println("Trial #" + (i + 1) + ": " + data.get(i));
			System.out.print("  ");
			for (int j = 0; j < counts[i].length; j++) {
				System.out.print((j + 1) + ":" + counts[i][j] + " ");
			}
			System.out.println();
			for (int j = 0; j < posterior[i].length; j++) {
				boolean best = posterior[i][j] > posterior[i][1 - j];
				if(best)
					System.out.print("  ==>");
				else
					System.out.print("     ");
				System.out.print("P(" + names[j] + ") = " + posterior[i][j]); // given an experiment ( or a trial ) containing a set of dice outcomes, 
				//posterior[i][j] is the probability that the experiment i is from fair dice [j = 0] or from weighted dice[j = 1] 
				System.out.println();
				
			}
		}

//		double [] abc =  {0.003, 0.004, 0.0007, 0.006 };
//		double [] logAbc = new double [abc.length];
//		for (int i = 0; i < abc.length; i++){
//			logAbc[i] = Math.log(abc[i]); 
//			System.out.println(logAbc[i]);
//		}
//		System.out.println(EM.logsum(logAbc));
//		
	}

}
