//TA Grade 90/100
//TA Extension 1
//TA Extension 2
//TA PH

package lab5;
/**
 * Name: Gaurav Singhal	
 * Lab Section: 501N A
 * Date: Feb 24, 2010
 * Eyeball.java
 * CSE 131 Lab 5
 */

import java.awt.Color;

import lab4.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;



import nip.*;

public class Eyeball implements MouseMotionListener, ActionListener {

	private Ellipse pupil;
	private Ellipse outline;
	
	public static final int PUPIL_SIZE = 6;
	public static final int OUTLINE_SIZE = 24;
	public static final int PUPIL_DISTANCE = OUTLINE_SIZE/2 - PUPIL_SIZE/2;

/**
 * Constructor for Eyeball
 * @param p, a Graphics Panel
 * @param x, coordinate of the position where eyeball is moved to
 * @param y, coordinate of the position where eyeball is moved to
 */
	public Eyeball(GraphicsPanel p, int x, int y ) {
		//TA: 59, 59 should be x, y    -5
		this.pupil = new Ellipse(59,59,PUPIL_SIZE,PUPIL_SIZE);
		this.pupil.setLineColor(Color.BLACK);     
		this.pupil.setFillColor(Color.BLACK);
		p.add(pupil); // pupil is added to the panel
		


		this.outline = new Ellipse(50,50,OUTLINE_SIZE, OUTLINE_SIZE);
		this.outline.setLineColor(Color.BLACK);     
		this.outline.setFillColor(Color.WHITE);
		p.add(outline); // outline is added to the panel
		
		
		moveTo(x,y); // outline and pupil are moved to x,y


		p.addMouseMotionListener(this);  

	}

	
	/*
	 * moveTo() method moves pupil and outline so that they are centered at this location
	 */
	public void moveTo(int x, int y) {
		movePupilTo(x,y);
		moveOutTo(x,y);
	}

	
	/**
	 * calls the lookAt method to make the pupil look at a point e, which is mouse's current position
	 */
	public void mouseMoved(MouseEvent e) {
		lookAt(new Point(e.getX(), e.getY()));

	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	/**moves outline's center to x,y
	 * @param x
	 * @param y
	 */
	private void moveOutTo(int x, int y) {
		outline.setCenter(x,y);
	}
	
	
	/**moves pupil's center to x,y
	 * @param x
	 * @param y
	 */
	private void movePupilTo(int x, int y) {
		pupil.setCenter(x,y);
	}

	
	
	/**
	 * moves pupil look at point p, pupil moves towards the point p, but only within the outline
	 * 
	 * @param p
	 */
	
	public void lookAt(Point p) {
		//TA doesnt track inside eye -5
		Point mousePosition = p;  
		Point eyeBallCenter = new Point(outline.getCenterX(), outline.getCenterY());
		Vector distance = mousePosition.minus(eyeBallCenter);
		Vector rescaled = distance.rescale(PUPIL_DISTANCE);
		Point at = eyeBallCenter.plus(rescaled);
		movePupilTo((int)at.getX(),(int)at.getY());
	}













	// You will modify the following method only if you
	// complete the animation Extension of this project.
	public void actionPerformed(ActionEvent arg0) {
	}

	//  Do nothing here



}
