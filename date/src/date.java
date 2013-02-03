// author : Stephanie Wolf


import java.io.*; 

public class date {
	// main method, execution begins here
	public static void main (String[] args) throws IOException {

		BufferedReader stdin = new BufferedReader
		(new InputStreamReader(System.in));
		int month, date, year; // declares three integers

		// this message is displayed
		System.out.print ("Enter the Month (mm): ");
		System.out.flush(); 

		// read a line, and then converts it to an integer, and stored as month
		month = Integer.parseInt( stdin.readLine());

		// continue only if input is valid
		if((month<13)&&(month>0)){
			System.out.print ("Enter the Date (dd): ");
			System.out.flush(); 
			// read a line, and then converts it to an integer, and stored as date
			date = Integer.parseInt( stdin.readLine());
			// continue only if input is valid
			if ((date>0)&&(date<32)){
				System.out.print ("Enter the Year (yyyy): ");
				System.out.flush(); 
				year = Integer.parseInt( stdin.readLine());


				// this is an array of Strings
				// such that months[month-1] = "Month name"  
				String [] months = new String[13];

				months[0] = "January";
				months[1] = "February";
				months[2] = "March";
				months[3] = "April";
				months[4] = "May";
				months[5] = "June";
				months[6] = "July";
				months[7] = "August";
				months[8] = "September";
				months[9] = "October";
				months[10] = "November";
				months[11] = "December";


				// this is an array of strings
				// such that for any date, dates[date] = its cardinal suffix
				String [] dates = new String[32];
				for(int i = 1; i <= 31; i++){
					if((i%10)==1){
						dates[i] = "st";
					}
					else if ((i%10)==2){
						dates[i] = "nd";
					}

					else if ((i%10)==3){
						dates[i] = "rd";
					}

					else{
						dates[i] = "th";
					}
				}

				// special case for 11th, 12th and 13th

				dates[11] = "th";
				dates[12] = "th";
				dates[13] = "th";

				String ans = months[month-1] + " " + date + dates[date]+ " " +year;

				// displaying final output
				System.out.println(ans );
				System.out.println("PROGRAM TERMINATES");
				System.out.println();
			}
			else {System.out.println("Invalid Input : Program Terminates");
			System.out.println();}
		}
		else {System.out.println("Invalid Input : Program Terminates");
		System.out.println();}
	} // method main terminates




} 

