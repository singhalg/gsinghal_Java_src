package arch;

import lab4.Vector;

/**
 * Gravity, the equal opportunity accelerator.
 *
 */
public class Gravity implements ForceProvider {

	private Mass mass;
	private static Vector accel = new Vector(0,2); // 2 pixels / second / second
	
	/**
	 * The force Gravity exerts is related to the Mass on which
	 * Gravity acts.
	 * @param m the Mass subject to Gravity
	 */
	
	public Gravity(Mass m) {
		this.mass = m;
	}
	
	/**
	 * Gravity's force is linear in the mass it is affecting
	 */
	public Vector getForce() {
		// F = ma
		return accel.scale(mass.getMass());
	}
	
	/**
	 * Get the acceleration due to gravity. This is
	 *   constant over all Masses.
	 * @return acceleration due to Gravity in pixels / sec / sec
	 */
	public static Vector getAcceleration() {
		return accel;
	}
	
	/**
	 * Set the acceleration due to Gravity.  This will
	 * change the value that is used to compute the Force
	 * exerted by Gravity for all Masses.
	 * @param v new acceleration due to Gravity in pixels / sec / sec
	 */
	public static void setAcceleration(Vector v) {
		accel = v;
	}
	
	public String toString() {
		return "Gravity a=" + accel + " applied to mass " + mass + " has force " + getForce();
	}

}
