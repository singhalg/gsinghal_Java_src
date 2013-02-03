

	/*
	 * FastaParser.java
	 *
	 * Created on July 13, 2005, 10:15 AM
	 *
	 * 
	 */
	 
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.util.ArrayList;
	import java.util.Iterator;
	import java.util.List;
	import org.biojava.bio.Annotation;
	import org.biojava.bio.program.sax.FastaSearchSAXParser;
	import org.biojava.bio.program.ssbind.BlastLikeSearchBuilder;
	import org.biojava.bio.program.ssbind.SeqSimilarityAdapter;
	import org.biojava.bio.search.SearchContentHandler;
	import org.biojava.bio.search.SeqSimilaritySearchHit;
	import org.biojava.bio.search.SeqSimilaritySearchResult;
	import org.biojava.bio.seq.db.DummySequenceDB;
	import org.biojava.bio.seq.db.DummySequenceDBInstallation;
	import org.xml.sax.InputSource;
	import org.xml.sax.SAXException;
	 
	 
	 
	public class FastaParser {
	  /**
	   * args[0] is assumed to be the name of a Fasta output file
	   */
	  public static void main(String[] args) {
	    try {
	      //get the Fasta input as a Stream
	      InputStream is = new FileInputStream(args[0]);
	 
	      //make a FastaSearchSAXParser
	      FastaSearchSAXParser parser = new FastaSearchSAXParser();
	 
	      //make the SAX event adapter that will pass events to a Handler.
	      SeqSimilarityAdapter adapter = new SeqSimilarityAdapter();
	 
	      //set the parsers SAX event adapter
	      parser.setContentHandler(adapter);
	 
	      //The list to hold the SeqSimilaritySearchResults
	      List results = new ArrayList();
	 
	      //create the SearchContentHandler that will build SeqSimilaritySearchResults
	      //in the results List
	      SearchContentHandler builder = new BlastLikeSearchBuilder(results,
	          new DummySequenceDB("ATTAT"), new DummySequenceDBInstallation());
	 
	      //register builder with adapter
	      adapter.setSearchContentHandler(builder);
	 
	      //parse the file, after this the result List will be populated with
	      //SeqSimilaritySearchResults
	      parser.parse(new InputSource(is));
	 
	      //output some blast details
	      for (Iterator i = results.iterator(); i.hasNext(); ) {
	        SeqSimilaritySearchResult result =
	            (SeqSimilaritySearchResult)i.next();
	 
	        Annotation anno = result.getAnnotation();
	 
	        for (Iterator j = anno.keys().iterator(); j.hasNext(); ) {
	          Object key = j.next();
	          Object property = anno.getProperty(key);
	          System.out.println(key+" : "+property);
	        }
	        System.out.println("Hits: ");
	 
	        //list the hits
	        for (Iterator k = result.getHits().iterator(); k.hasNext(); ) {
	          SeqSimilaritySearchHit hit =
	              (SeqSimilaritySearchHit)k.next();
	          System.out.print("\tmatch: "+hit.getSubjectID());
	          System.out.println("\te score: "+hit.getEValue());
	        }
	 
	        System.out.println("\n");
	      }
	 
	    }
	    catch (SAXException ex) {
	      //XML problem
	      ex.printStackTrace();
	    }catch (IOException ex) {
	      //IO problem, possibly file not found
	      ex.printStackTrace();
	    }
	  }
	}

