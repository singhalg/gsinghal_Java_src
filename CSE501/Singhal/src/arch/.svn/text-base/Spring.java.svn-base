package arch;

import lab4.Vector;

/**
 * An unattached Spring with an initial displacement.
 * It can compute its force at any time using
 * Hooks Law, if provided a hypothetical
 * current displacement.
 *
 */
public class Spring {

	private static double k = 1.0;
	public static boolean genLog = false;

	/**
	 * A Spring that behaves according to Hook's law
	 * @param displacement The Vector representing the
	 *    Spring's initial (untensioned) state
	 */
	public Spring(Vector displacement) {
	}

	/**
	 * All Springs have this value as their spring constant
	 * for Hook's law
	 * @return spring constant value
	 */
	public static double getSpringConstant() {
		return k;
	}
	
	/**
	 * Set the spring constant value for all Springs.
	 * @param k new spring constant value
	 */
	public static void setSpringConstant(double k) {
		Spring.k = k;
	}
	
	/**
	 * Apply Hook's law based on the initial and current displacements.
	 * The magnitude of the resulting force is proportional to the
	 *   differences in current vs. initial displacement.
	 *   Fmagnitude = -k * x
	 *   where k is the Spring constant and
	 *   x is the difference of the
	 *   current displacement's magnitude and the
	 *   original displacement's magnitude.
	 *   
	 *   The force is returned as the current displacement,
	 *      rescaled to the force magnitude from Hook's law.
	 * @param currentDisplacement The current displacement of the Spring
	 */
	public Vector getForce(Vector currentDisplacement) {
		return null;  // FIXME
	}

}
