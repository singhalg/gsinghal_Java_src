import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 


public class ParseCpG {
	int number;
	public ParseCpg(int number){
		this.number = number;
	}
	public void main(String[] args) { 
		// Create an instance of File for data.txt file. 
		File file = new File("cpgIslandShrt.txt"); 

		try { 
			// Create a new Scanner object which will read the data from the  
			// file passed in. To check if there are more line to read from it 
			// we check by calling the scanner.hasNextLine() method. We then 
			// read line one by one till all line is read. 
			// 
			String[][]cpg = new String [100][8];
			Scanner scanner = new Scanner(file); 
			int i = 0;
			while(i<100)	{
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					Scanner oneLine = new Scanner(line);
					while(oneLine.hasNext("chrY")){
						System.out.println(oneLine.next());
						//for(int j = 0; j < 8; j++){
						//	cpg[i][j] = oneLine.next();
						//	System.out.println(cpg[i][j]);
						//}
						i++;
						// oneIsland[0] = bin
						// oneIsland[1] = chrom
						// oneIsland[2] = chromStart
						// oneIsland[3] = chromEnd
						// oneIsland[4] = name
						// oneIsland[5] = length
						// oneIsland[6] = CpG number
						// oneIsland[7] = gc num
					}
				}
//				for(int k = 0; k<cpg[i].length; k++){
//					System.out.println(cpg[2][k]);
//				}
			}
			
			
			
		}
		
	
		catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} 
		
		
	}
	
	private String printLine(String [][] twoD ,int i){
		String ans = "";
		for(int j = 0; j<twoD[i].length; j++){
			ans = ans + twoD[i][j];
		}
		return ans;
		
	}
	
	
} 



