import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Vector;


public class DiceMagic {
	/*
	 * Usage: java DiceMagic <name of dice data file>
	 * or 
	 * Usage: java DiceMagic
	 * (input data from console, end by hitting control-D)
	 */
	public static void main(String[] args) throws Exception {

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
		int[][] counts = new int[data.size()][numSides];
		for (int j = 0; j < data.size(); j++) {
			String experiment = data.get(j);
			for (int i = 0; i < experiment.length(); i++) {
				String roll = experiment.substring(i, i + 1);
				int value = Integer.parseInt(roll) - 1;
				counts[j][value]++;
			}
		}

		// Run EM
		EM em = new EM(2, counts);
		
		em.solve(0.001);

		// Print out the results
		DecimalFormat df = new DecimalFormat(".00");
		for (int i = 0; i < 2; i++) {
			System.out.println("Die #" + i);
			if (Double.isNaN(em.typeProb[i]))
				System.out.println("p = .00");
			else
				System.out.println("p = " + df.format(em.typeProb[i]));
			for (int j = 0; j < 6; j++) {
				double p = em.resultProb[i][j];
				if (Double.isNaN(p)) {
					System.out.print("N/A");
				} else {
					System.out.print(df.format(p));
				}
				System.out.print(" ");
			}
			System.out.println("\n");
		}

	}
}
