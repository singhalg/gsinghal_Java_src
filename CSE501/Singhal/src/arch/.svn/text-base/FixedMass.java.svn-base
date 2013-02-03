package arch;

import java.awt.Color;

import nip.GraphicsPanel;
import lab4.Vector;

/**
 * A MassRect that is unaffected by any force.
 *
 */
public class FixedMass extends MassRect {

	/**
	 * Establish this Mass which is-a MassRect.  Fixed masses
	 * are black so you can see distinguish them on the screen.
	 * 
	 * @param x horizontal coordinate of the Rectangle for this Mass
	 * @param size length and width of the Rectangle
	 * @param mass value of the mass (say in grams)
	 * @param panel GraphicsPanel that will host the Rectangle
	 */
	public FixedMass(int x, int size, double mass, GraphicsPanel panel) {
		super(x, size, Color.BLACK, mass, panel);
	}
	
	/**
	 * A fixed mass does not react to force, sorry.
	 */
	public void applyForce(Vector force, double time) {
		// do nothing, this Mass cannot move
	}

}
