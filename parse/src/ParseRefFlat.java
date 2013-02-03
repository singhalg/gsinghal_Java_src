import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;
import org.biojava.*;
import org.biojava.bio.*;


public class ParseRefFlat {
	public static void main(String[] args) { 
		// Create an instance of File for data.txt file. 
		File file = new File("cpgIslandShrt.txt"); 

		try { 
			// Create a new Scanner object which will read the data from the  
			// file passed in. To check if there are more line to read from it 
			// we check by calling the scanner.hasNextLine() method. We then 
			// read line one by one till all line is read. 
			// 

			Scanner scanner = new Scanner(file); 
			String []arOfLines = new String [100];
			int i = 0;
			while (i<100 && scanner.hasNextLine()) {
				Scanner newLine = new Scanner(scanner.nextLine());
				if(newLine.hasNext("chrY")){
					arOfLines[i] = scanner.nextLine();
					System.out.println("Line" + i + "is : "+ arOfLines[i]);

				}
				i++;
			}
			//			for(int j = 0; j<arOfLines.length; j++){
			//				System.out.println(arOfLines[j]);
			//			}
		}
		catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} 
	} 
}
