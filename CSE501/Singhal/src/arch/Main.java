package arch;

import java.awt.event.MouseEvent;

import lab4.Vector;
import nip.*;

/**
 * This class is written for you.  It just presents
 * the standard NIP menu and keeps track of the number
 * of rounds last used, so you can click in the panel
 * and repeat that number of rounds.
 * @author cytron
 *
 */
public class Main extends Tool {

	private Arch arch;
	private int numMasses;
	private int numRounds = 1;

	public Main() {
		numMasses = 23;
	}


	public String toString() {
		return "arch";
	}

	public static void main(String args[]) {
		new NIP(new Main(), 512, 512, 1);
	}
	
	public void mousePressed(MouseEvent me) {
		run(this.numRounds);
	}
	
	private void run(int numRounds) {
		System.out.print("Running.....");
		this.numRounds = numRounds;
		for (int i=0; i < numRounds; ++i) {
			arch.round();
		}
		System.out.println("....done");
	}

	@Override
	public void actionNameCalled(String name) {
		if (name.equals("Arch")) {
			arch = new Arch(nip.getTargetPanel(), numMasses);
		}
		if (name.equals("1 round")) {
			run(1);
		}
		if (name.equals("10 rounds")) {
			run(10);
		}
		if (name.equals("100 rounds")) {
			run(100);
		}
		if (name.equals("run")) {
			arch.run();
		}
		if (name.equals("stop")) {
			arch.stop();
		}
	}

	@Override
	public String[] getEventNames() {
		return new String[] { "Arch", "1 round", "10 rounds", "100 rounds", "run", "stop" };
	}

}
