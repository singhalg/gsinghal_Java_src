import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Sequence {

	private final static int WIDTH = 80;

	private String header;

	private String body;

	/*
	 * Reads the first sequence from the specified file
	 */
	public Sequence(String filename) throws IOException {
		Scanner in = new Scanner(new File(filename));
		header = in.nextLine();
		StringBuffer buffer = new StringBuffer();
		while (in.hasNextLine()) {
			String line = in.nextLine().trim();
			if (line.charAt(0) == '>')
				break;
			buffer.append(line);
		}
		body = buffer.toString();
	}

	/*
	 * Constructor from data
	 */
	public Sequence(String header, String body) {
		this.header = header;
		this.body = body;
	}

	/*
	 * Returns the length of the sequence
	 */
	public int length() {
		return body.length();
	}

	/*
	 * Returns a String representation of the file
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer(header);
		sb.append('\n');
		int i = 0;
		for (i = 0; i < body.length() - WIDTH; i += WIDTH) {
			sb.append(body.substring(i, i + WIDTH));
			sb.append('\n');
		}
		sb.append(body.substring(i));
		sb.append('\n');
		return sb.toString();
	}

	public String getHeader() {
		return header;
	}

	public String getBody() {
		return body;
	}
	
	public int[] getCounts() {
		int[] counts = new int[5];
		for (int i = 0; i < body.length(); i++) {
			counts[getIndex(body.charAt(i))]++;
		}
		return counts;
	}
	
	private static int getIndex(char c) {
		switch (c) {
		case 'A':
		case 'a':
			return 0;
		case 'C':
		case 'c':
			return 1;
		case 'G':
		case 'g':
			return 2;
		case 'T':
		case 't':
			return 3;
		default:
			return 4;
		}
	}

	/*
	 * Returns all sequences from a FASTA file
	 */
	public static Vector<Sequence> getSequencesFromFile(String filename)
			throws IOException {
		Vector<Sequence> sequences = new Vector<Sequence>();
		Scanner in = new Scanner(new File(filename));
		String head = null;
		StringBuffer body = new StringBuffer();
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (line.charAt(0) == '>') {
				if (head != null)
					sequences.add(new Sequence(head, body.toString()));
				head = line;
				body = new StringBuffer();
			} else
				body.append(line);
		}
		if (head != null)
			sequences.add(new Sequence(head, body.toString()));
		return sequences;
	}
}
