// SEQREADER.JAVA
// Read a sequence from a file.  The file is assumed to contain a single
// sequence, possibly split across multiple lines.  Case is not preserved.
//
import java.io.*;

public class SeqReader {
	public static String readSeq(String fileName)
	{
		BufferedReader r;

		// Create a reader for the file
		//
		try {
			InputStream is = new FileInputStream(fileName);
			r = new BufferedReader(new InputStreamReader(is));
		}
		catch (IOException e) {
			System.out.println("IOException while opening " +
					fileName + "\n" + e);
			return null;
		}

		StringWriter buffer = new StringWriter();

		// Accumulate each line of the file (minus surrounding whitespace)
		// sequentially in a string buffer.  Convert to lower case as we read.
		//
		try {
			boolean stop = false;

			while (!stop)
			{
				String nextline = r.readLine();
				if (nextline == null) // end of file
					stop = true;
				else
				{
					String seq = nextline.trim();
					buffer.write(seq.toLowerCase());
				}
			}
		}
		catch (IOException e) {
			System.out.println("IOException while reading sequence from " +
					fileName + "\n" + e);
			return null;
		}

		return buffer.toString();
	}
}
