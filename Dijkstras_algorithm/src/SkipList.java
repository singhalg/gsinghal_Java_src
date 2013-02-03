// author : Gaurav Singhal

import java.util.* ;


public class SkipList {
	// a constant for infinity and negative infinity

	public static final int INF = java.lang.Integer.MAX_VALUE;
	public static final int NEGINF = java.lang.Integer.MIN_VALUE;
	java.util.Random randseq;
	private ListItem header;

	int maxLevel ; // maximum level of the list
	int currentMax = 0; // current maximum level of any item of the list, when height is 1, currentMax is 0
	static HashMap<String, HashSet<Integer>> table = new HashMap<String, HashSet<Integer>>(4000);

	ListItem nilItem;

	public SkipList( int maxHeight){

		randseq = new java.util.Random();
		this.maxLevel = maxHeight-1; // if maxHeight = 100, maxLevle = 99
		header = new ListItem(maxLevel, NEGINF, "" ) ;
		nilItem = new ListItem(maxLevel, INF, "");
		for (int i=0; i<=maxLevel; i++) {
			header.forward[i] = nilItem;
		}
		table = new HashMap<String, HashSet<Integer>>(4000);

	}



	public ListItem getHeader() { 
		return this.header; 
	}
	public int getCurrentMax(){
		return currentMax;
	}




	public boolean containsKey( int searchKey){
		ListItem x = this.getHeader();

		for(int i = getCurrentMax(); i >=0; i--){
			System.out.println("");
			System.out.print("At level " + i + " compared " + searchKey + " to: "); 
			while(x.forward[i].date<searchKey){
				System.out.print(x.forward[i].date+ "   " );
				x = x.forward[i];

			}
			if(x.forward[i].date!=INF){
				System.out.print( x.forward[i].date);}
		}
		x = x.forward[0];  // it checks if x.forward[0] is the element we were looking for
		if(x.date == searchKey){
			return true;
		}
		else {
			return false;
		}


	}


	public String toString(){
		String ans = "";
		for(int i = getCurrentMax(); i >=0; i--){


			ListItem x = header;
			ans += "Level " + i + " : ";
			do{
				ans+= x.forward[i].date+ "   " ;
				x = x.forward[i];
			}while(x.forward[i].date!= INF);
			ans += '\n';
		}
		return ans;
	}


// I allow ListItems with similar dates to be inserted. 
//	If a ListItem has exactly same date and description as a preexisting one, then the pre-existing one is overwritten 
// if a ListItem has a date(key) which already exists in the list, then a new element is created with similar key.
	
	public void put(int date, String description){
		ListItem[] update = new ListItem[maxLevel+1]; // if the MaxLevel is 99, maxHeight is 100 and update.length = 100
		ListItem x = header;

		for(int i = x.getListLevel(); i>= 0; i--){

			while(x.forward[i].date<date){
				x = x.forward[i];

			}
			update[i] = x;
		}

		x = x.forward[0];

		if((x.date==date)&&(x.description == description)){// if the date and event description match an event that is 
			// already present
			x.description = description; // replaces the existing node 
		}
		else{
			int newLevel = randomLevel();
			
			if (newLevel>currentMax){
			
				for( int i = currentMax+1; i <=newLevel; i++){
				
					update[i] = header;
				

				}
				currentMax = newLevel;
			}

			ListItem newX = new ListItem(newLevel, date, description);
			
			for(int i = 0; i<=newLevel; i++){
				
				newX.forward[i] = update[i].forward[i];
				update[i].forward[i]= newX;
			}


		}

// inserting elements in the HashMap
		StringTokenizer st = new StringTokenizer(description.toLowerCase());
		while (st.hasMoreTokens()){
			String word = st.nextToken();
			if(table.containsKey(word)){
				table.get(word).add(date);
			}else{
				HashSet<Integer> newHs = new HashSet<Integer>() ;
				newHs.add(date);
				table.put(word, newHs);
			}

		}
	}

	
	
	/***
	 * 
	 * @param startDate
	 * @param endDate
	 * @returns a String representation of those ListItems whose key is <= endDate
	 * It starts at ListItem whose key is >= start date.
	 */
	
	public String rangeSearch(int startDate, int endDate){
		String ans = "";

		ans += "Start Date : " + startDate + '\n';
		ans += "End Date : " + endDate +'\n';
		ListItem start = search(startDate);
		//ListItem end = search(endDate);

		ListItem x = start;

		while ( ! (x.date > endDate)){
			ans+=  x.date + " : " + x.description + '\n';
			x = x.forward[0];
		}



		return ans;
	}

	
	/**
	 * Helper method for rangeSearch method 
	 * @param searchKey
	 * @returns a List Item whose key is greater than or equal to searchKey
	 */

	public  ListItem search(int searchKey){

		ListItem x = header;

		for(int i = getCurrentMax(); i >=0; i--){

			while(x.forward[i].date<searchKey){

				x = x.forward[i];

			}

		}
		
		x = x.forward[0];  // here, x.date >= searchKey
		return x;
	}





	public String remove(int date){
		String ans = "";

		ListItem[] update = new ListItem[maxLevel+1]; // if the MaxLevel is 99, maxHeight is 100 and update.length = 100
		ListItem x = header;
		for(int i = x.getListLevel(); i>= 0; i--){
			while(x.forward[i].date<date){
				x = x.forward[i];
			}
			update[i] = x;
		}
		ListItem xPrev = x;		
		x = x.forward[0];
		
		// here if there are multiple consecutive events with the same date, then all of them are deleted
		while((xPrev.forward[0].date==date)){// if an event with exact date is found			

			int dateTbd = xPrev.forward[0].date;
			String descTbd = xPrev.forward[0].description;
			removeFromTable(dateTbd, descTbd);

			ans += xPrev.forward[0].date + " : " + xPrev.forward[0].description + '\n';		
			for(int i = 0 ; i <=currentMax; i++){
				if (update[i].forward[i]!= xPrev.forward[0]){
					break;
				}
				update[i].forward[i] = xPrev.forward[0].forward[i];
			}	
			x = null;
			while((currentMax>=1)&&(header.forward[currentMax].date == INF)){
				currentMax--;
			}

		}
		return ans;
	}
	
	/***
	 * Helper method for removing an event from hash table
	 * @param date
	 * @param description
	 */
	public void removeFromTable(int date , String description){
		StringTokenizer st = new StringTokenizer(description.toLowerCase());
		while (st.hasMoreTokens()){
			String word = st.nextToken();
			if(table.containsKey(word)){
				if(table.get(word).contains(date)){
					table.get(word).remove(date);

				}
				if(table.get(word).isEmpty()){
					table.remove(word);
				}
			}
		}

	}
	
	
	public  String findOccurance(String word){
		String ans = "";
		word = word.toLowerCase();
		if(table.containsKey(word)){
			HashSet<Integer> datesHs =  table.get(word);
			ArrayList<Integer>dates = new ArrayList<Integer>(datesHs);
			Collections.sort(dates);
			Integer[] dateArr = new Integer[dates.size()];
			dates.toArray(dateArr);
			for(int i = 0; i < dateArr.length; i++){
				ListItem anItem = 	search(dateArr[i]);
				while(anItem.getListDate() == dateArr[i]){
					if(containsWord(anItem.getListDesc(), word)){
						System.out.println("true");
						ans+= anItem.getListDate() + " : " +  anItem.getListDesc()	+ '\n';
					}
					else{anItem = anItem.forward[0];
					}
				}

			}
			return ans;
		}
		else{
			return "Cound not find " + word; 
		}
	}



/***
 * Helper method for findOccurance method
 * Checks whether a given string contains a word or not
 * @param desc
 * @param search
 * @return
 */
	public boolean containsWord(String desc, String search){
		boolean ans = false;
		StringTokenizer st = new StringTokenizer(desc.toLowerCase());
		while (st.hasMoreTokens()){
			String word = st.nextToken();
			if(search.toLowerCase() == word){
				return true;
			}
		}
		return ans;
	}


	private int randomLevel() {
		int newLevel = 0;
		while ((newLevel< maxLevel)&&(coinFlip() )) {
			newLevel++;
		}
		return newLevel;
	}




	// A method to simulate a coin that is heads with probability 1/4

	boolean coinFlip() {
		return (randseq.nextInt(4) == 0);
	} 



}