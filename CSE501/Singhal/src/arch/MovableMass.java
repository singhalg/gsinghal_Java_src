package arch;

import java.awt.Color;

import nip.GraphicsPanel;

import lab4.Point;
import lab4.Vector;

/**
 * A MassRect that is affected by force.
 *
 */
public class MovableMass extends MassRect {

	/**
	 * A MovableMass is a MassRect that is of gray color.
	 * @param x horizontal coordinate of the Rectangle for this Mass
	 * @param size length and width of the Rectangle
	 * @param mass value of the mass (say in grams)
	 * @param panel GraphicsPanel that will host the Rectangle
	 */
	public MovableMass(int x, int size, double mass, GraphicsPanel panel) {
		super(x, size, Color.GRAY, mass, panel);
	}

	/**
	 * Change the location of this MassRect based on applying the
	 * specified force for the specified amount of time.
	 * Using F = ma, the acceleration experienced by this
	 *   Mass can be computed.  Applying said acceleration for
	 *   the specified amount of time produces a displacement,
	 *   which is applied to the MassRect's current position to
	 *   obtain its new position. 
	 */
	@Override
	public void applyForce(Vector force, double time) {
		// FIXME: apply the force as described above
	}

}
