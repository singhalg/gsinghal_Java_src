//author : Gaurav Singhal ; gsinghal@wustl.edu


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HiddenMarkovModel {


	public final static double INF = java.lang.Double.NEGATIVE_INFINITY; 
	private HmmState[] states;

	private char[] alphabet;

	public HiddenMarkovModel(String filename) throws IOException {
		Scanner in = new Scanner(new File(filename));
		states = new HmmState[in.nextInt()];
		alphabet = in.next().toCharArray();

		int statei = -1;

		while (in.hasNext()) {
			String token = in.next();
			if (token.equals("State:")) {

				statei++;
				states[statei] = new HmmState(in.next());
				states[statei].setStartProbability(in.nextDouble());
				for (int i = 0; i < alphabet.length; i++) {
					states[statei].addEmissionProbability(alphabet[i], in
							.nextDouble());
				}

			} else {
				states[statei].addTransitionProbability(token, in.nextDouble());
			}
		}
	}

	public HiddenMarkovModel(HmmState[] states, char[] alphabet) {
		this.states = states;
		this.alphabet = alphabet;
	}

	public HmmState[] decode(String s) {
		int sLength = s.length();
		HmmState[] tracebackArray = new HmmState[sLength];


		int[][] decoded = new int[states.length][sLength];
		double [][] seq = new double[states.length][sLength];

		// getting start probs ; calculating in normal space
//		for(int i = 0; i < states.length; i++){
//			seq[i][0] = states[i].getStartProbability()*states[i].getEmissionProbability(s.charAt(0)) ;
//			decoded[i][0]= 0;
//		}
		
		
		// calculating in log space
		for(int j = 0; j < states.length; j++){
			seq[j][0] = Math.log(states[j].getStartProbability()) +   Math.log(states[j].getEmissionProbability(s.charAt(0))) ;
			decoded[j][0]= 0;
		}
		
		//calculations in normal space
//		for(int i = 1; i < sLength; i++){
//			for(int j = 0; j< states.length; j++){
//				double [] probs = new double[states.length];
//				for(int k = 0 ; k < states.length; k++){
//					// array Probs  = prev prob     *  transition prob from prev state[k] to current state[j]  
//					probs[k] =        seq[k][i-1]   *   states[k].getTransitionProbability(states[j].getName());
//				}
//				int x = findIndexOfMax(probs);
//				seq[j][i] = states[j].getEmissionProbability(s.charAt(i))  *   
//							seq[x][i-1]  *  states[x].getTransitionProbability(states[j].getName());
//				decoded[j][i] = x;
//			}
//		}
		
		//calculating in log space
		//now parsing through the sequence and filling seq[][] as well as decoded[][] 
		for(int i = 1; i < sLength; i++){
			for(int j = 0; j< states.length; j++){
				double [] probs = new double[states.length];
				for(int k = 0 ; k < states.length; k++){
					// array Probs  = prev prob     *  transition prob from prev state[k] to current state[j]  
					probs[k] =        seq[k][i-1]   +    Math.log(states[k].getTransitionProbability(states[j].getName()));
				}
				int x = findIndexOfMax(probs);
				seq[j][i] = Math.log(states[j].getEmissionProbability(s.charAt(i)))  
							+ seq[x][i-1]   
							+ Math.log(states[x].getTransitionProbability(states[j].getName()));
				decoded[j][i] = x;

			}
		}

		double []endProbs = new double[states.length];
		for(int j = 0; j < states.length; j++){
			endProbs[j] = seq[j][sLength-1];
		}
		
		int endState = findIndexOfMax(endProbs);
		tracebackArray[sLength-1] = states[endState];
		
		for(int i = sLength-1; i > 0; i-- ){
			tracebackArray[i-1] = states[decoded[endState][i]];
		}

		return tracebackArray;
	}

	/***
	 * Helper method for decode method
	 * @param : double[] array
	 *@return: the array index of the max number in array[]
	 * The value returned by this helper method is also fed to the decoded array and used for traceback.
	 * 
	 */

	public int findIndexOfMax(double[] probs){
		int x = 0;
		double a = INF;
		for(int i = 0; i< probs.length; i++){
			if ( probs[i]>a){
				a = probs[i];
				x = i;
			}
		}
		return x;
	}


	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(states.length);
		sb.append(' ');
		for (char c : alphabet) {
			sb.append(c);
		}
		sb.append('\n');
		for (HmmState state : states) {
			sb.append("State: ");
			sb.append(state.getName());
			sb.append(' ');
			sb.append(state.getStartProbability());
			sb.append("\n ");
			for (char c : alphabet) {
				sb.append(state.getEmissionProbability(c));
				sb.append(' ');
			}
			sb.append('\n');
			for (HmmState tstate : states) {
				String tname = tstate.getName();
				double p = state.getTransitionProbability(tname);
				if (p == 0)
					continue;
				sb.append(' ');
				sb.append(tname);
				sb.append(' ');
				sb.append(p);
				sb.append('\n');
			}

		}
		return sb.toString();
	}
}
