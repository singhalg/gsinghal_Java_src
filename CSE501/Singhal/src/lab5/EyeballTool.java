package lab5;
/**
 * Name: Gaurav Singhal
 * Lab Section: 501N A
 * Date:24 Feb 2010
 * EyeballTool.java
 * CSE 131 Lab 5
 */

import java.awt.event.MouseEvent;

import nip.*;
//import lab4.*;

public class EyeballTool extends Tool {
	
	private GraphicsPanel p;
	private Eyeball demoEye;
/**
 * Constructor for EyeballTool
 * @param panel
 */
	public EyeballTool(GraphicsPanel panel) {
		this.p = panel;
		
	}
	
	
	
	@Override
	/**
	 * a new instance of Eyeball is created on clicking mouse, on panel p and at mouse location
	 */
	
	public void mousePressed(MouseEvent me) {
	
		demoEye = new Eyeball(p, me.getX(), me.getY());
		
	}
	/**
	 * moves the demoEye to mouse's current position
	 */
	public void mouseDragged(MouseEvent e) {
		demoEye.moveTo(e.getX(), e.getY());
	}
	
	
	
	public String toString() {
		return "Eyeballs";
	}

	
	
	/**
	 * We won't be using the menu to do anything for this lab
	 */
	public void actionNameCalled(String name) {		
	}

	/**
	 * No menu items needed
	 */
	public String[] getEventNames() {
		return new String[0];
	}
}
