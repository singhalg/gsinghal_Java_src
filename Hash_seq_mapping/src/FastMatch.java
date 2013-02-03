import java.util.*;

// FastMatch.java
// Sequence matching driver for CS 241 Lab 2
//
// SYNTAX: java Lab2 <transcript file> <corpus file> <pattern file> 
//                   [ <mask file> ]
//
// This sequence matching program reads two sequences, a CORPUS and a PATTERN,
// from files on disk and finds all strings of a given length M (given
// interactively by the user) that occur in both corpus and pattern.  
// Matching substrings are printed in the form
//      <index 1> <index 2> <substring>
// where the two indices are the offsets of the match within the corpus and
// pattern, respectively, and <substring> is the actual matching string.
// All output is copied into the file <transcript file> for later review.
//
// As an optional fourth argument, the program can take a file containing a
// MASK sequence.  Substrings of the mask sequence are considered
// "uninteresting" and so must not be reported by the matching code.
// To implement this requirement, we delete any occurrences of substrings
// in the mask sequence from our pattern table before performing the search.
//
// ALTERNATE SYNTAX: java fastMatch
//
// This alternate is provided for those who find it difficult to give
// command line arguments.  With this option it will ask if you want
// to use data set 1,2,3 or 4.  All but data set 3 assume there is a
// mask file.  For data set 3, no mask file is used.  The transcript
// will put put into a file with the name test#-output where # is the data
// set (1,2,3 or 4).

public class FastMatch {
	public static void main(String args[]) throws Exception {
		String corpusSeq = null;
		String patternSeq = null;
		String maskSeq = null;
		int testNum = 1;
		if (args.length == 0) {
			Terminal.print("Which test data set (1,2,3 or 4)? ");
			testNum = Terminal.readInt();
			if ((testNum < 1) || (testNum > 4)) {
				System.out.println("You must enter 1,2,3 or 4");
				return;
			}
			Terminal.startTranscript("test" + testNum + "-output");
			corpusSeq = SeqReader.readSeq("case" + testNum + "-corpus");
			patternSeq = SeqReader.readSeq("case" + testNum + "-pattern");
			Terminal.println("CORPUS: " + corpusSeq.length() + " bases");
			Terminal.println("PATTERN: " + patternSeq.length() + " bases");

			if ((testNum == 1) || (testNum == 2) || (testNum == 4)) {
				maskSeq = SeqReader.readSeq("case" + testNum + "-mask");
				Terminal.println("MASK: " + maskSeq.length() + " bases");
			}
		} else if (args.length < 3) {
			System.out.println("Syntax: java FastMatch <transcript file> "
					+ "<corpus file> <pattern file> " + "[<mask file>]");
			return;
		} else {
			Terminal.startTranscript(args[0]);
			corpusSeq = SeqReader.readSeq(args[1]);
			patternSeq = SeqReader.readSeq(args[2]);
			Terminal.println("CORPUS: " + corpusSeq.length() + " bases");
			Terminal.println("PATTERN: " + patternSeq.length() + " bases");

			if (args.length > 3) {
				maskSeq = SeqReader.readSeq(args[3]);
				Terminal.println("MASK: " + maskSeq.length() + " bases");
			}
		}

		// Interactively get the desired match length
		// 
		Terminal.print("Match length? ");
		int matchLength = Terminal.readInt();

		// Insert all sequences of match length into the BucketMapping
		BucketMapping table = new BucketMapping(8192);

		for (int j = 0; j < patternSeq.length() - matchLength + 1; j++) {
			String key = patternSeq.substring(j, j + matchLength);
			//System.out.println(key);
			table.put(key, new Integer(j));
		}
		//System.out.println("table size = " +table.size());


		Terminal.println("\nAfter creating the table, it holds "
				+ table.size() + " sequences of length " + matchLength);

		// Remove all substrings in the mask sequence from a StringDictionary.
		// 
		if (maskSeq != null) {
			for (int j = 0; j < maskSeq.length() - matchLength + 1; j++) {
				String key = maskSeq.substring(j, j + matchLength);
				if (table.contains(key))
					table.remove(key);
			}
		}

		Terminal.println("After removing the mask sequences, the table holds "
				+ table.size() + " sequences of length " + matchLength + "\n");

		// Find and print all matches between the corpus sequence and any
		// string in the BucketMapping
		//
		int numMatches = 0;
		for (int j = 0; j < corpusSeq.length() - matchLength + 1; j++) {
			String key = corpusSeq.substring(j, j + matchLength);

			//For Part 2 of the lab, count the average number of probes made
			//  by the below call to get.


			Iterator<Object> loc = table.get(key);
			if (loc != null){
				while (loc.hasNext()){

					Terminal.println(j + " " + loc.next() + " " + key);
					numMatches++;
				}
			}
		}

		System.out.println("Total number of probes = " + table.probe); // printing total number of probes made in all the calls to get method
		System.out.println("Total number of get() calls = " + (corpusSeq.length() - matchLength + 1));
		System.out.println("Average number of probes per call = " + (1.0)*(table.probe)/(corpusSeq.length() - matchLength + 1) );
		System.out.println("Load Factor = " + (table.size()/16384.00 )); 
		Terminal.println("\nThere were " + numMatches + " matches found.");
		Terminal.stopTranscript();
	}
}
