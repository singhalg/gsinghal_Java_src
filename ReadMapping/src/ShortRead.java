//
// SHORTREAD.JAVA
// Representation of short reads with per-base error probabilities
// 
// Reads may be read en masse from a FASTQ-formatted file.
//

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class ShortRead {

	//
	// Construct a ShortRead from its data elements:
	//  (1) name (an arbitrary identifier string)
	//  (2) DNA sequence
	//  (3) an array of integer quality values per base
	//
	public ShortRead(String name, String seqstr, int[] qualities) 
	{
		init(name, seqstr, qualities);
	}

	//
	// Construct a ShortRead from three unparsed lines describing the
	// sequence in a FASTQ file:
	//  (1) FASTA header
	//  (2) DNA sequence
	//  (3) encoded quality values per base
	//
	public ShortRead(String descline, String seqline, String qline) 
	{
		int nameEnd = descline.indexOf(' ');
		init(descline.substring(1, nameEnd), seqline, toQualities(qline));
	}

	// Return a read's name
	public String name() 
	{
		return _name;
	}

	// return a read's DNA sequence
	public DNASeq sequence() 
	{
		return _sequence;
	}

	// return a read's per-base error *probabilities*
	// (note that these are actual probabilities, not quality values)
	public float [] errProbs() 
	{
		return _errProbs;
	}

	// print a read along with its per-base quality values
	public String toString() 
	{
		return "@" + 
		_name + "\n" + 
		_sequence.toString() + "\n" + 
		toQList(_errProbs);
	}

	//
	// Compute the reverse complement of a read, reversing
	// the quality value array and updating the name to disambiguate
	// it from the original read.
	//
	public ShortRead getReverseComplement()
	{
		ShortRead rread = new ShortRead();

		rread._name = _name + ".rc";
		rread._sequence = _sequence.getReverseComplement();

		int nProbs = _errProbs.length;
		rread._errProbs = new float [nProbs];

		for (int i = 0; i < nProbs; i++)
			rread._errProbs[i] = _errProbs[nProbs - i - 1];

		return rread;
	}

	/////////////////////////////////////////////////////////////

	//
	// Read a collection of short reads in FASTQ format from a file.
	//
	public static ShortRead[] readAllFastqReads(String filename)
	throws IOException 
	{
		Scanner in = openFastqFile(filename);

		return readFastqReads(in, 2000000000); // MAX_VALUE doesn't work?
	}


	//
	// Open a FASTQ file, returning a handle to a Scanner object
	// that can be used to get the reads.
	//
	public static Scanner openFastqFile(String filename)
	throws IOException 
	{
		Scanner in = new Scanner(new File(filename));

		return in;
	}

	//
	// Read the next batch of up to nreads short reads in FASTQ format from
	// an open file, represented by a Scanner.
	//
	public static ShortRead[] readFastqReads(Scanner in, int nreads) 
	throws IOException 
	{
		ArrayList<ShortRead> v = new ArrayList<ShortRead>();

		int count = 1;
		while (count <= nreads && in.hasNextLine()) 
		{
			String desc = in.nextLine();
			String seq  = in.nextLine();
			in.nextLine(); // eat second copy of identifier
			String quals = in.nextLine(); 
			v.add(new ShortRead(desc, seq, quals.trim()));

			count++;
		}

		ShortRead[] reads = new ShortRead [v.size()];
		v.toArray(reads);

		return reads;
	}

	/////////////////////////////////////////////////////////////
	// NO PUBLIC INTERFACE BELOW THIS LINE
	/////////////////////////////////////////////////////////////

	private String _name;         // read identifier
	private DNASeq _sequence;     // DNA sequence of read
	private float[] _errProbs;    // per-base error probabilities

	private static float[] q2p;   // table for quality->error prob conversion

	// initialize quality to error probability conversion table
	static
	{
		q2p = new float [93];

		double incr = Math.pow(10.0, -0.1);

		double prob = 1.0;
		for (int i = 1; i <= 93; i++)
		{
			q2p[i-1] = (float) prob;
			prob *= incr;
		}
	}

	// null constructor to create objects internally
	private ShortRead()
	{
		// do nothing
	}

	// helper for constructor
	private void init(String name, String seqstr, int[] qualities) 
	{
		_name     = name;
		_sequence = new DNASeq(seqstr);
		_errProbs = toProbs(qualities);
	}

	//
	// convert a string of qualities stored as characters into
	// integers, following the FASTQ format.  Assume that
	// quality values are in Sanger form.
	//
	private static int[] toQualities(String s) 
	{
		int[] qs = new int [s.length()];

		for (int i = 0; i < s.length(); i++) 
		{
			qs[i] = s.charAt(i) - 33;
		}

		return qs;
	}

	//
	// Convert an array of quality values into an array of
	// error probabilities, using table lookup to avoid
	// transcendental math.
	//
	private static float[] toProbs(int[] qs)
	{
		float[] ps = new float [qs.length];
		for (int i = 0; i < qs.length; i++)
		{
			ps[i] = q2p[qs[i]];
		}
		return ps;
	}

	//
	// Convert an array of error probabilities into a
	// printable list of Sanger quality values.
	//
	private static String toQList(float[] errProbs)
	{
		StringBuffer sb = new StringBuffer();

		sb.append("{");

		for (float p : errProbs)
		{
			sb.append(String.format("%.0f", -10 * Math.log10(p)));
			sb.append(", ");
		}

		sb.append("}");

		return sb.toString();
	}
}
