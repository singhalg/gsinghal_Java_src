
public class Species {
	private String name;
	private int gnome;
	private int numchr;

	public Species(String speciesname, int genome, int chromosomes){
		name = speciesname;
		gnome = genome;
		numchr = chromosomes;

	}

	public double avglen(int genome, int chromosomes){
		return ((genome*1.0)/chromosomes);
	}

	public int getgenome(){
		return gnome;
	}

	public int getchrnum(){
		return numchr;
	}

	public String getname(){
		return name;
	}
	public static void main (String args[]){
		Species human = new Species("human", 3000000, 30);
		int genome = human.getgenome();
		int chr = human.getchrnum();
		System.out.println(human.avglen(genome, chr));
	}
}

