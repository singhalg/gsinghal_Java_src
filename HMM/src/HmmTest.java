import java.text.DecimalFormat;

public class HmmTest {
	public static void main(String[] args) throws Exception {
		HiddenMarkovModel hmm = new HiddenMarkovModel(args[0]);
		Sequence s = new Sequence(args[1]);

		// decode
		HmmState[] decoded = hmm.decode(s.getBody());
		
		// check accuracy
		Sequence key = new Sequence(args[2]);
		String keyS = key.getBody();
		int correct = 0;
		for (int i = 0; i < keyS.length(); i++) {
			if (decoded[i] == null)
				continue;
			boolean isHuman = decoded[i].getName().charAt(0)=='H';
			boolean predictedHuman = keyS.charAt(i) == 'h';

			if ((predictedHuman && isHuman) || (!predictedHuman && !isHuman)) {
				correct++;
			}
		}
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("Accuracy: " + df.format(correct * 100. / keyS.length()));
	}
}
