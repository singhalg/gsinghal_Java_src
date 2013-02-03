package lab8;

/**
 * Holds a number in a linked list structure.
 * Gaurav Singhal ; CSE 501N Lab A; gsinghal@wustl.edu
 * @author
 * @version 1.0
 * CSE131 Lab 8
 * Date: 28 March 2010
 */

public class ListItem {
	int number;
	ListItem next;

	/**
	 * Creates a single list item.
	 * @param number the value to be held in the item
	 * @param next a reference to the next item in the list
	 */
	ListItem(int number, ListItem next) {
		this.number = number;
		this.next   = next;
	}

	/**
	 * Return a copy of this list using recursion.  No
	 * credit if you use any iteration!  All existing lists should remain
	 * intact -- this method must not mutate anything.
	 * @return
	 */

	/**
	 * duplicate return a new ListItem, beginning with head, and uses copy(ListItem) to make the rest of the 
	 * ListItem
	 * I could have done this w/o the help of copy, if duplicate could take some argument. 
	 */

	public ListItem duplicate() {

		//		int head = this.number;
		//		ListItem tail = this.next;
		//		ListItem duplist = new ListItem(head, copy(tail));
		//		return duplist;
		return copy(this);
	}


	/**
	 * Helper method for duplicate()
	 * does same as duplicate, but for the supplied argument.
	 * @param aList 
	 * @returns 
	 */
	private static ListItem copy(ListItem aList){
		if(aList == null){
			return null;
		}
		else{
			int head = aList.number;
			ListItem tail = aList.next;
			ListItem abc = new ListItem(head, copy(tail));
			return abc;
		}
	}



	/**
	 * Recursively compute the number of elements in the list. No
	 * credit if you use any iteration!  All existing lists should remain
	 * intact.
	 * @return
	 */

	public int length(){

		return this.lengthHelp(1);

	}

	/**
	 * helper method for length()
	 * just to keep a tally of the count till the whole list is traversed
	 * @param count number of items discovered in the list so far
	 * @return 
	 */
	private int lengthHelp(int count) {
		if (this.next == null){
			return count;
		}
		else{ 
			return this.next.lengthHelp(count+1);

		}

	}


	/**
	 * Return a new list, like duplicate(), but every element
	 * appears n times instead of once.  See the web page for details.
	 * You must do this method iteratively.  No credit
	 * if you use any recursion!
	 * @param n a positive (never 0) number specifying how many times to copy each element
	 * @return
	 */

	public ListItem stretch(int n) {
		ListItem curr = this;

		ListItem ans = new ListItem (this.number, null); 

		ListItem p = ans; // p is pointing towards the first Item of the list
		// so that I can return this p in the end

		while(curr != null){

			for(int i = 0; i< n ; i++){
				ListItem temp = new ListItem (curr.number, null); 
				ans.next = temp;
				ans = ans.next; // now ans points at the last item of the list
			}
			curr = curr.next;
		}


		return p.next; // since p contains one extra copy of the first element in the beginning of the list, 
		//so I return p.next


	}



	/**
	 * Return the first ListItem, looking from "this" forward,
	 * that contains the specified number.  No lists should be
	 * modified as a result of this call.  You may do this recursively
	 * or iteratively, as you like.
	 * @param n
	 * @return
	 */
	/**
	 * as always, we split the List into this.number and the rest of the list, to make head and tail respectively
	 * traverses till the end of the list by splitting every list in such a manner and checking for the presence of n.
	 * 
	 */
	public ListItem find(int n) {

		ListItem head = this;
		ListItem tail = this.next;

		while(head.next!= null){

			if(head.number == n){
				return head;
			}
			else{
				head = tail;
				tail = tail.next;
			}

		}
		return null;
	}

	/**
	 * Return the maximum number contained in the list
	 * from this point forward.  No lists should be modified
	 * as a result of this call.  You may do this method recursively
	 * or iteratively,as you like.
	 * @return
	 */
	/***
	 * same implementation as with find. 
	 * just sets the first number as highest, and then compares highest to the next number in the list.
	 * resets highest to the higher number in each comparison
	 */
	public int max() {
		int highest = this.number; // currently this is the highest number so far
		ListItem  head = this;
		ListItem tail = this.next;
		while(head.next !=null){
			if(tail.number>highest){
				highest = tail.number;
			}
			head = tail;
			tail = tail.next;
		}

		return highest; 
	}

	/**
	 * Returns a copy of the list beginning at ls, but containing
	 * only elements that are even.
	 * @param ls
	 * @return
	 */


	// basically its similar to the copy method described before, but also includes a test for even number 
	public static ListItem evenElements(ListItem ls){
		if (ls == null){
			return null;
		}
		else
		{
			if(((ls.number%2)==0) || (ls.number==0)){
				return new ListItem (ls.number, evenElements(ls.next));
			}
			else{
				return evenElements(ls.next);
			}
		}
	}





	/**
	 * Returns a string representation of the values reachable from
	 * this list item.  Values appear in the same order as the occur in
	 * the linked structure.  Leave this method alone so our testing will work
	 * properly.
	 */
	public String toString() {
		if (next == null)
			return ("" + number);
		else
			return (number + " " + next); // calls next.toString() implicitly
	}

}
