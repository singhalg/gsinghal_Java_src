import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;



public class SequenceEM {
	public static void main(String[] args) throws Exception {
		final String nucleotides = "ACGT";
		final int numNucleotides = nucleotides.length();

		Vector<Sequence> sequences = Sequence.getSequencesFromFile(args[0]);

		int[][] counts = new int[sequences.size()][];

		for (int i = 0; i < sequences.size(); i++) {
			Sequence seq = sequences.get(i);
			counts[i] = seq.getCounts();
		}

		// Run EM
		EM em = new EM(2, counts);
		em.solve(2E-10);

		boolean firstIsHuman;
		if (em.resultProb[0][0] < em.resultProb[1][0])
			firstIsHuman = true;
		else
			firstIsHuman = false;

		// Print out the results
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < 2; i++) {
			if ((firstIsHuman && i == 0) || (!firstIsHuman && i == 1))
				System.out.println("Human: ");
			else
				System.out.println("Malaria: ");

			if (Double.isNaN(em.typeProb[i]))
				System.out.println("p = .00");
			else
				System.out.println("p = " + df.format(em.typeProb[i]));
			for (int j = 0; j < numNucleotides; j++) {
				double p = em.resultProb[i][j];
				System.out.print(nucleotides.charAt(j) + ":");
				if (Double.isNaN(p)) {
					System.out.print("N/A");
				} else {
					System.out.print(df.format(p));
				}
				System.out.print(" ");
			}
			System.out.println("\n");
		}

		if (args.length > 1) {
			printAccuracy(args[1], em.calculatePosteriors(), firstIsHuman);
		}

	}

	private static void printAccuracy(String filename, double[][] posteriors,
			boolean firstIsHuman) throws IOException {
		Scanner in = new Scanner(new File(filename));
		StringBuffer sb = new StringBuffer();
		while (in.hasNextLine()) {
			sb.append(in.nextLine());
		}
		int correct = 0;
		for (int i = 0; i < posteriors.length; i++) {
			boolean correctIsHuman = sb.charAt(i) == 'H';
			boolean predictedIsFirst = posteriors[i][0] > posteriors[i][1];
			boolean predictedIsHuman = (predictedIsFirst && firstIsHuman)
					|| (!predictedIsFirst && !firstIsHuman);
			if(correctIsHuman==predictedIsHuman)
				correct++;
		}
		System.out.println("# Correct: " + correct + "/" + posteriors.length);
		System.out.println("% Correct: " + (correct*100.0/posteriors.length));
	}
}
