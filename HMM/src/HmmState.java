import java.util.HashMap;

public class HmmState {
	private String name;

	private double probStart;

	private HashMap<String, Double> transition;

	private HashMap<Character, Double> emission;

	public HmmState(String name) {
		this.name = name;
		probStart = 0.0;
		transition = new HashMap<String, Double>();
		emission = new HashMap<Character, Double>();
	}

	public void setStartProbability(double probStart) {
		this.probStart = probStart;
	}

	public double getStartProbability() {
		return probStart;
	}

	public void addEmissionProbability(char c, double probability) {
		emission.put(c, probability);
	}

	public double getEmissionProbability(char c) {
		Double p = emission.get(c);
		if (p == null)
			return 0.0;
		else
			return p;
	}

	public void addTransitionProbability(String name, double probability) {
		transition.put(name, probability);
	}

	public double getTransitionProbability(String name) {
		Double p = transition.get(name);
		if (p == null)
			return 0.0;
		else
			return p;
	}

	public String toString() {
		return name + " (" + probStart + ")" + transition + " " + emission;
	}

	public String getName() {
		return name;
	}

	public boolean equals(HmmState s) {
		return name.equals(s.getName());
	}
}
