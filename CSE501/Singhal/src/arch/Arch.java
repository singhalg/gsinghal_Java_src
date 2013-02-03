package arch;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;

import lab4.Vector;

import nip.*;

/**
 * Models an arch using masses and springs.
 *
 */
public class Arch implements ActionListener {

	final public double ROUNDDURATION = 1.0; // in seconds
	final private GraphicsPanel panel;
	final public static double TOTALMASS=50;
	private Timer timer;
	
	/**
	 * Set up the simulation by depositing the specified
	 * number of masses (MassRect) onto the panel, with two
	 * SpringBetweenMasses between each adjacent pair of masses.
	 * The two SpringBetweenMasses are needed because one end of
	 * each Spring is viewed as fixed.
	 * @param panel The panel on which to place things
	 * @param numMasses The number of masses for the Arch
	 */

	public Arch(GraphicsPanel panel, int numMasses) {
		panel.getMainImage().fillRegion(0, 0, panel.getWidth(), panel.getHeight(), Color.WHITE);
		this.panel = panel;
		timer = new Timer(20, this);
		//
		// FIXME:  create the masses and springs as described above
	}

	/**
	 * Allows a graphics component to be added to the arch panel.
	 * The SpringBetweenMasses class needs this so it can update the Spring
	 * visualization (Line) that describes the Spring.
	 * @param g
	 */
	public void addComponent(Graphic g) {
		panel.add(g);
	}

	/**
	 * Allows a graphics component to be removed from the arch panel
	 * The SpringBetweenMasses class needs this so it can update the Spring
	 * visualization (Line) that describes the Spring.
	 * @param g
	 */
	public void removeComponent(Graphic g) {
		panel.remove(g);
	}


	/**
	 * Start the swing timer
	 */
	public void run() {
		timer.start();
	}

	/**
	 * Stop the swing timer
	 */
	public void stop() {
		timer.stop();
	}

	/**
	 * Run one round of the simulation.
	 * For each mass, compute (but don't apply)
	 * the force that the mass
	 * by calling mass.sumForces().
	 * Then, for each mass, apply the force that it
	 * should experience.
	 * Then, for each Spring, have it relocate itself
	 * based on its masses' positions.
	 */
	public void round() {
		// FIXME: perform one round as described above
	}

	/**
	 * Each clock tick, perform one round of the
	 * simulation.
	 */
	public void actionPerformed(ActionEvent e) {
		round();
	}

}
