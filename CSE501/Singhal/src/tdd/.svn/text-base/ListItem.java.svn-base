package tdd;

// Like Ken Goldman's ListItem 
public class ListItem {

	final int num;  // No change after construction
	ListItem next;  // Could change after construction.
			// access is allowed by any class in the same package
	ListItem prev;

	/**
	 * Constructor remembers the number
	 * and sets its references to null;
	 * presumably they will be set outside this constructor call.
	 */
	public ListItem(int num) {
		this.num  = num;
		this.next = null;
		this.prev = null;
	}
	
	/**
	 * returns a String with every ListItem's num from here to the end of the list
	 */
	public String toString() {
		if (next == null) {	 // "this" is the end of list
			return ""+this.num;
		}
		else { // not the end of the list keep going
			return this.num + ", " + next.toString();
		}
	}


}
