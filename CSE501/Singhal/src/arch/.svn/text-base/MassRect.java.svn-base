package arch;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import lab4.Point;
import lab4.Vector;

import nip.GraphicsPanel;
import nip.Rectangle;

/**
 * A MassRect is-a Mass and has-a Rectangle that represents
 * itself in the simulation.
 * 
 * But it was not always this way....
 * 
 * My initial thinking was to extend Rectangle, but in
 *    retrospect that was not such a good idea:
 *    1) I ran into methods I wanted for my own purpose, such
 *       as getLocation, but that was already a method defined
 *       on a Rectangle.
 *    2) I need Mass coordinates to be double, not int
 *    3) Outside this class, a Mass object is not really
 *       treated as-a Rectangle.
 *
 */
abstract public class MassRect extends Mass {
	
	private List<ForceProvider> forces; 
	private static int seq = 0;
	final private int num;
	private Point at;
	private Rectangle rect;
	
	/**
	 * This is-a Mass of the specified mass value.
	 * Instantiates a Rectangle at (x,0) of the specified size,
	 *   and adds that Rectangle to the panel.
	 * @param x horizontal coordinate for the Rectangle representing this Mass
	 * @param size length and width of the Rectangle
	 * @param color color of the Rectangle
	 * @param mass value for the Mass
	 * @param panel panel to hold the Rectangle
	 */
	public MassRect(int x, int size, Color color, double mass, GraphicsPanel panel) {
		super(mass);
		rect = new Rectangle(x, 0, size, size, color, true);
		this.num = seq++;
		rect.setFillColor(color);
		panel.add(rect);
		this.forces = new LinkedList<ForceProvider>();
		this.at = new Point(rect.getCenterX(), rect.getCenterY());
	}
	
	/**
	 * Causes the specified ForceProvider to be 
	 * included in future force calculations for this Mass.
	 * @param f Provides force
	 */
	public void affectedByForce(ForceProvider f) {
		forces.add(f);
	}
	
	/**
	 * Where is this Mass currently?
	 * @return location of the Mass
	 */
	public Point getPoint() {
		return at;
	}
	
	/**
	 * Change this Mass's location to the specified Point
	 * @param location
	 */
	protected void setLocation(Point location) {
		rect.setCenter((int)location.getX(), (int)location.getY());
		at = location;
	}
		
	
	/**
	 * Computes the Vector sum of all forces that currently affect
	 * this Mass, but does not actually apply the
	 * force to the Mass.
	 * @return the sum of all forces currently affecting this Mass
	 */
	public Vector sumForces() {
		return null;  // FIXME
	}
	
	public String toString() {
		return "Mass " + num + " at " + getPoint();
	}
	
	/**
	 * Apply a particular force to this Mass for the specified time.
	 * @param force The force to be applied to this mass.
	 * @param time The time duration of the force application.
	 */
	abstract public void applyForce(Vector force, double time);
}
