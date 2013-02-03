package org.debugger.tutorial;

import java.io.Serializable;

/**
 * @author Mark Dexter
 * @version 1.0
 * The Person class represents people who
 * check out books from a MyLibrary object.
 *
 */
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -549832220463532971L;
	private String name; // person's name
	
	/*
	 * maximum number of books this person can check out
	 */
	private int maximumBooks; 
	
	public Person() {
		name = "unknown name";
		maximumBooks = 3;
	}
	
	public Person(String name, int maximumBooks) {
		super();
		this.name = name;
		this.maximumBooks = maximumBooks;
	}
	
	public int getMaximumBooks() {
		return maximumBooks;
	}

	public String getName() {
		return name;
	}

	public void setMaximumBooks(int maximumBooks) {
		this.maximumBooks = maximumBooks;
	}

	public void setName(String newName) {
		this.name = newName;
	}
	
	public String toString() {
		return this.getName() + " (" + this.getMaximumBooks()
		+ " books)";
	}
}