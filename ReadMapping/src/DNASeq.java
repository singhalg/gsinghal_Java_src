//
// DNASEQ.JAVA
// A DNA sequence, stored in 4 bits per base
// Note that a DNASeq is also a reasonable representation for a
// hash value computed by extracting a subset of bases.
//
// The sequence may be read from a FASTA file.
//

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

class DNASeq {

	// Enumeration of DNA bases, including "X" for unknown.
	public final static byte BASE_A = 0;
	public final static byte BASE_C = 1;
	public final static byte BASE_G = 2;
	public final static byte BASE_T = 3;
	public final static byte BASE_X = 4;

	// conversion string for printing -- must match ordering above
	private final static String baseMap = "ACGTX";

	////////////////////////////////////////////////////////////

	// constructor from String representation of sequence
	public DNASeq(String iseqstr)
	{
		String seqstr = iseqstr.toUpperCase();
		_length = seqstr.length();

		bases = new byte [(_length + 1)/2];
		int bidx = 0;

		for (int i = 0; i < _length; i++)
		{
			byte nextBase;

			switch(seqstr.charAt(i))
			{
			case 'A':
				nextBase = BASE_A;
				break;

			case 'C':
				nextBase = BASE_C;
				break;

			case 'G':
				nextBase = BASE_G;
				break;

			case 'T':
				nextBase = BASE_T;
				break;

			default: // N, X, etc
				nextBase = BASE_X;
			break;
			}

			if ((i & 0x01) == 1) // odd numbered base
			{
				bases[bidx] |= (nextBase << 4);
				bidx++;
			}
			else
			{
				bases[bidx]  = nextBase;
			}
		}
	}

	// DNASeq constructor from an arbitrary byte array; useful for
	// forming hashable values from partial DNA sequences.
	//
	public DNASeq(byte [] bytes)
	{
		_length = bytes.length;

		bases = new byte [(_length + 1)/2];
		int bidx = 0;

		for (int i = 0; i < _length; i++)
		{
			byte nextBase = bytes[i];

			if ((i & 0x01) == 1) // odd numbered base
			{
				bases[bidx] |= (nextBase << 4);
				bidx++;
			}
			else
			{
				bases[bidx]  = nextBase;
			}
		}
	}

	// length of sequence
	public int length()
	{
		return _length;
	}

	// get ith base of sequence
	public byte baseAt(int i)
	{
		int offset = (i & 0x01);
		return (byte) ((bases[i/2] >>> (4 * offset)) & 0x0F);
	}

	// convert a DNA sequence to a printable string
	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < _length; i++)
		{
			sb.append(baseToChar(baseAt(i)));
		}

		return sb.toString();
	}

	// create a new sequence that is the reverse complement of this one
	public DNASeq getReverseComplement()
	{
		DNASeq cseq = new DNASeq();

		cseq._length = _length;
		cseq.bases   = new byte [(cseq._length + 1)/2];

		int bidx = 0;
		for (int i = 0; i < _length; i++)
		{
			byte rbase = this.baseAt(_length - i - 1);

			byte cbase;
			switch(rbase)
			{
			case BASE_A:
				cbase = BASE_T;
				break;
			case BASE_T:
				cbase = BASE_A;
				break;
			case BASE_C:
				cbase = BASE_G;
				break;
			case BASE_G:
				cbase = BASE_C;
				break;
			default:
				cbase = rbase;
			break;
			}

			if ((i & 0x01) == 1) // odd numbered base
			{
				cseq.bases[bidx] |= (cbase << 4);
				bidx++;
			}
			else
			{
				cseq.bases[bidx] = cbase;
			}
		}

		return cseq;
	}


	//
	// Create a new sequence containing the substring of this one
	// running from start to start + length - 1, inclusive.
	//
	public DNASeq getSubseq(int start, int length)
	{
		DNASeq sseq = new DNASeq();

		sseq._length = length;
		sseq.bases   = new byte [(sseq._length + 1)/2];

		int bidx = 0;
		for (int i = 0; i < length; i++)
		{
			byte sbase = this.baseAt(start + i);
			if ((i & 0x01) == 1) // odd numbered base
			{
				sseq.bases[bidx] |= (sbase << 4);
				bidx++;
			}
			else
			{
				sseq.bases[bidx] = sbase;
			}
		}

		return sseq;
	}

	// Test sequences for equality of their bases.
	public boolean equals(Object iother)
	{
		if (iother == null || iother.getClass() != this.getClass())
			return false;

		DNASeq other = (DNASeq) iother;

		if (_length != other._length)
			return false;

		for (int i = 0; i < (_length + 1)/2; i++)
		{
			if (bases[i] != other.bases[i])
				return false;
		}

		return true;
	}

	// Hash a sequence by a simple computation on its bases
	public int hashCode()
	{
		final int A = 1511506517;
		int h = _length;

		for (int i = 0; i < (_length + 1)/2; i++)
		{
			h = h * A + bases[i];
		}

		return h;
	}

	///////////////////////////////////////////////////////////////////

	// Read and return just the FASTA title of a FASTA-formatted sequence
	public static String readFastaTitle(String filename) throws IOException 
	{
		Scanner in = new Scanner(new File(filename));

		String firstline = in.nextLine();
		return ((firstline.substring(1)).trim());
	}

	// Read and return just the bases of a FASTA-formatted sequence
	public static DNASeq readFastaSeq(String filename) throws IOException 
	{	
		File infile = new File(filename);

		Scanner in = new Scanner(new File(filename));

		String title = in.nextLine();
		long fileLength = infile.length() - title.length(); // upper bound

		byte [] seqbytes = new byte [(int) (fileLength + 1)/2];

		//
		// Read all the sequence lines and track their total length
		int totalLength = 0;
		int nlines = 1;
		while (in.hasNextLine()) 
		{
			DNASeq seqline = new DNASeq((in.nextLine()).trim());

			appendBases(seqline.bases, seqline.length(),
					seqbytes, totalLength);
			totalLength += seqline.length();

			if (nlines % 10000 == 0)
				System.out.println("Read sequence line " + nlines);
			nlines++;
		}

		return new DNASeq(seqbytes, totalLength);
	}

	/////////////////////////////////////////////////////////////
	// NO PUBLIC INTERFACE BELOW THIS LINE
	/////////////////////////////////////////////////////////////

	private byte[] bases; // base content, 4 bits/base
	private int _length;  // length of sequence

	// null constructor for internal creation of sequence objects
	private DNASeq()
	{
		// do nothing
	}

	// constructor given raw byte array for sequence
	private DNASeq(byte [] bases, int length)
	{
		this.bases = bases;
		_length = length;
	}

	// convert a base to a printable character
	private char baseToChar(byte b)
	{
		return baseMap.charAt(b);
	}


	// Append a packed DNA sequence "sourceBases" to the end of
	// the packed sequence "targetBases".  We assume that the target
	// has enough room to append the source.
	//
	private static void appendBases(byte [] sourceBases, int sourceLength, 
			byte [] targetBases, int targetLength)
	{
		int targetStart = targetLength/2;

		if ((targetLength & 0x01) == 0)
		{
			// Easy case: target starts at a new byte of source
			System.arraycopy(sourceBases, 0, 
					targetBases, targetStart,
					(sourceLength + 1)/2);
		}
		else
		{
			// Hard case: need to copy byte-by-byte so as to
			// fill last half-byte of target's last byte first.
			int offset = 0;
			while (offset < sourceLength/2)
			{
				byte b = sourceBases[offset];

				targetBases[targetStart + offset] |= 
					(b & 0x0F) << 4;

				offset++;

				targetBases[targetStart + offset] = 
					(byte) (b >>> 4);
			}

			// if source has odd length, need to copy last
			// half byte into last byte of target.
			if ((sourceLength & 0x01) == 1)
			{
				byte b = sourceBases[offset];

				targetBases[targetStart + offset] |= 
					(b & 0x0F) << 4;
			}
		}
	}

}
