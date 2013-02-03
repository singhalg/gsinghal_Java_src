package arch;

import java.awt.Color;

import nip.Line;
import lab4.Vector;
import lab4.Point;

/**
 * A Spring attached to two Masses.  One is viewed as fixed,
 * and the other as movable.  Thus all force is exerted on just
 * one of the two Masses.
 *
 */
public class SpringBetweenMasses extends Spring implements ForceProvider {
	
	private MassRect movable, fixed;
	private Arch arch;
	private Line line;

	/**
	 * Spring from m1 to m2, meant to pull on m1.  The Spring needs
	 * access to the Arch so it can add and remove components.
	 * This is only because changing Line objects in NIP does
	 * not seem to work.  So, where a Line object would normally
	 * just change its location, it is instead removed and replaced
	 * by a new Line object with the right location.
	 * 
	 * @param movable The Mass that will be moved by the Spring
	 * @param fixed The Mass that does not move
	 * @param arch Needed so we can add and remove components
	 */
	public SpringBetweenMasses(MassRect movable, MassRect fixed, Arch arch) {
		super(null); // FIXME by supplying the Spring's initial displacement
		// FIXME set up instance variables as required
		
		
		// When all is done, show the current location of this Spring:
		locate();
	}
	
	/**
	 * Have the Spring compute its force based on its current displacement
	 */
	public Vector getForce() {
		// FIXME:  use Spring's getForce(displacement) method to return
		//   the right value here
		return null;
	}

	/**
	 * Position the Spring based on the location of the
	 * Masses it connects.  Normally, this could be done
	 * simply by changing the Line's location.  NIP seems
	 * to have a problem with that, so remove the current
	 * Line and add a new one at the right spot.
	 * You will have to call Arch's removeComponent and
	 * addComponent to achieve that result.
	 */
	public void locate() {
		Point leftP = movable.getPoint();
		Point rightP = fixed.getPoint();
		if (line != null) arch.removeComponent(line);
		line = new Line((int)leftP.getX(), (int)leftP.getY(), (int)rightP.getX(), (int)rightP.getY());
		line.setLineColor(Color.RED);
		arch.addComponent(line);		
	}
	
	public String toString() {
		return "Spring from " + movable + " to " + fixed;
	}

}
