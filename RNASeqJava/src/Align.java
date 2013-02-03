
/*
 * $Id: Example.java,v 1.3 2005/04/03 19:38:21 ahmed Exp $
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */









import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example of using JAligner API to align P53 human aganist
 * P53 mouse using Smith-Waterman-Gotoh algorithm.
 *
 * @author Ahmed Moustafa (ahmed@users.sf.net)
 */

public class Align {
	
	/**
	 * 
	 */
	private static final String dnaseq1 = "/src/seq1";
	
	/**
	 * 
	 */
	private static final String dnaseq2 = "/src/seq2";
	
	/**
	 * Logger
	 */
	//private static final Logger logger = Logger.getLogger(Align.class.getName());
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
        try {
        	
        	DNASeq seq1 = null;    // reference sequence
        	String seq1title = null;

    		// Read the reference sequence
    		try {
    			seq1title = DNASeq.readFastaTitle(args[0]);

    			System.out.println("Reading seq1 : " + seq1title);

    			seq1 = DNASeq.readFastaSeq(args[0]);

    			System.out.println(seq1title + " is of length " + 
    					seq1.length());

    		} catch (IOException e) {
    			System.out.println("Failed to read seq1 " );
    			//return "FAIL";
    		}
        	
    		
    		DNASeq seq2 = null;    // reference sequence
    		String seq2title = null;

    		// Read the reference sequence
    		try {
    			seq2title = DNASeq.readFastaTitle(args[1]);

    			System.out.println("Reading seq2 :  " + seq2title);

    			seq2 = DNASeq.readFastaSeq(args[1]);

    			System.out.println(seq2title + " is of length " + 
    					seq2.length());

    		} catch (IOException e) {
    			System.out.println("Failed to read seq2");
    			//return "FAIL";
    		}
    		
        	//logger.info("Running example...");
        	
			Sequence s1 = new Sequence (seq1.toString(), seq1title, seq1title, 0) ;  
			Sequence s2 = new Sequence (seq2.toString(), seq2title, seq2title, 0) ; 
	        
	        Alignment alignment = SmithWatermanGotoh.align(s1, s2, MatrixLoader.load("EDNAFULL"), 8f, 1f);
	        System.out.println("Sequence #1 :" + seq1title);
	        System.out.println("Sequence #2 :" + seq2title);
	        System.out.println ( alignment.getSummary() );
	        System.out.println(alignment.shortSummary());
	        //System.out.println ( new Pair().format(alignment) );
	        
	        //logger.info("Finished running example");
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	//logger.log(Level.SEVERE, "Failed running alignment: " + e.getMessage(), e);
//        	return "FAIL";
        }
		
    }
	
	/**
	 * 
	 * @param path location of the sequence
	 * @return sequence string
	 * @throws IOException
	 */
	
	
	
	
	
	
	
	
	private static String loadSampleSequence(String path) throws IOException {
		InputStream inputStream = Example.class.getClassLoader().getResourceAsStream(path);
        StringBuffer buffer = new StringBuffer();
		
        int ch;
        while ((ch = inputStream.read()) != -1) {
            ((Appendable) buffer).append((char)ch);
        }
        return buffer.toString();
	}
	
	/**
	 * 
	 * @return sequence string
	 * @throws IOException
	 */
	public static String loadDnaSeq1( ) throws IOException {
		return loadSampleSequence(dnaseq1);
	}

	/**
	 * 
	 * @return sequence string
	 * @throws IOException
	 */
	public static String loadDnaSeq2( ) throws IOException {
		return loadSampleSequence(dnaseq2);
	}
}