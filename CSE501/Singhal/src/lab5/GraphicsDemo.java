package lab5;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import nip.*;

/**
 * This example demonstrates creating some shapes, adding them to a panel,
 * and reacting to mouse events that occur on the panel.  NOTE: It is important to
 * add shapes to the panel after creating them... simply creating them will not
 * make them appear.
 * 
 * RKC refactored and adapted to NIP  September 2009
 * 
 * @author Kenneth J. Goldman<BR>
 * Created Aug 5, 2005
 */
public class GraphicsDemo implements MouseMotionListener {
	
	private Ellipse oval;       // the inner shape
	private Rectangle box;      // the box around the shape
	
	/*
	 * The constructor creates the shapes, sets their colors, and puts them on
	 * the NIP target panel.
	 */
	public GraphicsDemo(GraphicsPanel panel) {
	    // Create an ellipse at (50,75) that is 100 wide and 25 high
		this.oval = new Ellipse(500,7,10,25);
		this.oval.setLineColor(Color.BLUE);     
		this.oval.setFillColor(Color.PINK);
		panel.add(oval);                   // put the ellipse on the panel
		
		// Create a rectangle with the same dimensions
		box = new Rectangle(500,7,10,25); 
		panel.add(box);  // put the rectangle on the panel (behind the oval)
		
		//
		// The line below is important. Without it, this object would not
		//    listen to mouse events and respond in mouseMoved, below
		//
		panel.addMouseMotionListener(this);
	}
	
	/*
	 * Whenever the mouse moves inside the panel, move the shapes to
	 * the mouse location.
	 */
	// @Override
	public void mouseMoved(MouseEvent me) {
		moveTo(me.getX(),me.getY());
	}
	
	/*
	 * Sets the centers of the oval and the box to the given coordinates.
	 */
	public void moveTo(int x, int y) {
		oval.setCenter(x,y);
		box.setCenter(x,y);
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		moveTo(me.getX(),me.getY());
	}
	
	//
	// Do nothing
	


}

