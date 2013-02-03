package org.debugger.tutorial;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Mark Dexter
 * @version 1.0
 * The MyLibrary class contains a list of books
 * that can be checked out and a list of people
 * who can check them out.
 *
 */
public class MyLibrary implements Serializable {

	private static final long serialVersionUID = 169520141385923186L;
	String name;
	ArrayList<Book> books;
	ArrayList<Person> people;

	public MyLibrary(String name) {
		this.name = name;
		books = new ArrayList<Book>();
		people = new ArrayList<Person>();
		
	}

	public String getName() {
		return name;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	public void addBook(Book b1) {
		this.books.add(b1);
		
	}

	public void removeBook(Book b1) {
		this.books.remove(b1);
		
	}
	
	public void addPerson(Person p1) {
		this.people.add(p1);
	}
	
	public void removePerson(Person p1) {
		this.people.remove(p1);
	}

	/**
	 * This method checks out a book to a person.
	 * The check out is successful only if (a) the 
	 * person field is null (meaning that the book
	 * is not currently checked out) and (b) this person
	 * has not already checked out their maximum number
	 * of books allowed.
	 * 
	 * @param b1 Book object to check out.
	 * @param p1 Person object to check book out to.
	 * @return true if check out succeeds, false otherwise.
	 */
	public boolean checkOut(Book b1, Person p1) {
		int booksOut = this.getBooksForPerson(p1).size();
		if ((b1.getPerson() == null) &&
				(booksOut <= p1.getMaximumBooks()))
		{
			b1.setPerson(p1);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * This method checks in book b1. It succeeds only iff
	 * the book is currently checked out to a person (i.e., 
	 * that the getPerson() is not null).
	 * @param b1 Book to check in.
	 * @return true if method succeeds, false otherwise.
	 */
	public boolean checkIn(Book b1) {
		if (b1.getPerson() != null) {
			b1.setPerson(null);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * This method returns a list of books currently checked
	 * out to a person.
	 * If the person has no books checked out, an empty list
	 * is returned.
	 * @param p1 Person to whom books are checked out.
	 * @return List of books checked out to this person. 
	 */
	public ArrayList<Book> getBooksForPerson(Person p1) {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book aBook : this.getBooks()) {
			if (aBook.getPerson().getName().
							equals(p1.getName())) {
				result.add(aBook);
			}
		}
		return result;
	}

	/**
	 * This method returns a list of books not currently checked out. If all
	 * books are checked out, an empty list is returned.
	 * 
	 * @return List of books not checked out.
	 */
	public ArrayList<Book> getAvailableBooks() {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book book : this.getBooks()) {
			if (book.getPerson() == null) {
				result.add(book);
			}
		}
		return result;
	}

	/**
	 * This method returns a list of books that are currently 
	 * checked out. If no books are checked out, an empty
	 * list is returned.
	 * @return List of books checked out.
	 */
	public ArrayList<Book> getUnavailableBooks() {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book book : this.getBooks()) {
			if (book.getPerson() != null) {
				result.add(book);
			}
		}
		
		return result;
	}

	/**
	 * This method runs a simulated user session where 
	 * a MyLibrary object is created, added to, saved to a
	 * serial file, etc.
	 * @param args No command line arguments are used at this time.
	 */
	public static void main(String[] args) {
		// create a new mylibrary
		MyLibrary testLibrary = new MyLibrary("Test Drive Library");
		Book b1 = new Book("War and Peace");
		b1.setAuthor("Tolstoy");
		Book b2 = new Book("Great Expectations");
		b2.setAuthor("Charles Dickens");
		
		Person jim = new Person();
		jim.setName("Jim");
		Person sue = new Person();
		sue.setName("Sue");
		
		testLibrary.addBook(b1);
		testLibrary.addBook(b2);
		testLibrary.addPerson(jim);
		testLibrary.addPerson(sue);
		
		System.out.println("Just Created New Library");
		testLibrary.printStatus();
		
		testLibrary.checkOut(b1, sue);
		System.out.println("Checked out War and Peace to Sue");
		testLibrary.printStatus();
		
		testLibrary.checkIn(b1);
		testLibrary.checkOut(b2, jim);
		
		testLibrary.printStatus();
	}

	/**
	 * This method prints information about this
	 * MyLibrary object to the system console.
	 */
	private void printStatus() {
		System.out.println("Status Report of MyLibrary: \n" + 
				this.toString());

		for (Book thisBook : this.getBooks()) {
			System.out.println(thisBook);
		}
		
		for (Person thisPerson : this.getPeople()) {
			int count = this.getBooksForPerson(thisPerson).size();
			System.out.println(thisPerson + " (has " + count + 
					" of my books)");
		}
		
		System.out.println("Books Available: "
				+ this.getAvailableBooks().size());
		System.out.println("--- End of Status Report---\n");		
	}
	
	@Override
	public String toString() {
		return this.getName() + 
		": " + this.getBooks().size() + " books; " 
		+ this.getPeople().size() + " people.";
	}
}
