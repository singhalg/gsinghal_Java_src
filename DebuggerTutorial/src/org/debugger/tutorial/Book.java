package org.debugger.tutorial;

import java.io.Serializable;

/**
 * @author Mark Dexter
 * @version 1.0
 * The Book class represents books that can be 
 * checked out from a MyLibrary object.
 *
 */
public class Book implements Serializable{

	private static final long serialVersionUID = -4946021242876591956L;
	public String title;
	public String author;
	private Person person;

	public Book(String string) {
		this.title = string;
		this.author = "unknown author";
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setPerson(Person p2) {
		this.person = p2;
	}

	public Person getPerson() {
		return this.person;
	}
	
	@Override
	public String toString() {
		String available;
		if (this.getPerson() == null) {
			available = "Available";
		}
		else {
			available = "Checked out to " + 
			this.getPerson().getName();
		}
		return this.getTitle() + 
		" by " + this.getAuthor() +
		"; " + available;
	}
}