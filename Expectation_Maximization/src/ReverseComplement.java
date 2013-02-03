
import java.io.IOException;

import compbio.Sequence;

public class ReverseComplement {

	public static void main (String args[]) throws IOException{
		Sequence example = new Sequence(args[0]);
		System.out.println(example.getBody());
		System.out.println(example.reverseComplement());
		//System.out.println(example.getSubsequence());
		System.out.println("hello");
		System.out.println(example.getBody().matches(args[1]));
	}

}
