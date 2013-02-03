import java.text.DecimalFormat;

public class BaumWelchTest {
	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.err
					.println("Usage: java BaumWelchTest sequence.fa key.fa convergencefactor");
			System.exit(1);
		}

		String sequenceFile = args[0], keyFile = args[1];
		double converge = Double.parseDouble(args[2]);
		int numStates = 2;
		Sequence sequence = new Sequence(sequenceFile), key = new Sequence(
				keyFile);

		HiddenMarkovModel hmm = BaumWelch.infer(sequence.getBody(), numStates,
				converge);

		System.out.println(hmm);

		// decode
		HmmState[] decoded = hmm.decode(sequence.getBody());

		// check accuracy
		String keyS = key.getBody();
		int correct0 = 0, correct1 = 0;
		char stateA = keyS.charAt(0);

		for (int i = 0; i < keyS.length(); i++) {
			if (decoded[i] == null)
				continue;

			boolean isHuman = decoded[i].getName().equals("0");
			boolean predictedHuman = keyS.charAt(i) == stateA;

			if ((predictedHuman && isHuman) || (!predictedHuman && !isHuman)) {
				correct0++;
			} else {
				correct1++;
			}

		}
		DecimalFormat df = new DecimalFormat("0.00");
		int correct = Math.max(correct0, correct1);
		System.out.println("Accuracy: "
				+ df.format(correct * 100. / keyS.length()));
	}
}
