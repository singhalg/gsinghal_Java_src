package lab2;
/**
 * Name:
 * Lab Section: 
 * Date:
 * Email:
 * PatternTool.java
 * CSE 131 Lab 2
 */


import java.awt.Color;
import java.util.Random;

import nip.*;

public class PatternTool extends Tool{
	static Random random = new Random();  // A random number generator, used to create colors.
	
	//Recursive flower main  method.
	public void makeFlower() {
		GraphicsPanel panel = nip.getTargetPanel();
		//The above 'panel' instance variable is what you'll add your ellipses to.
		//Add any additional code you need in this method here.
		//TODO
	}
	
	//Add any helper methods for the recursive flower here.
	//TODO
	
	//Persian recursion main method.
	public void makePersian() {
		Image img = nip.getTargetImage();
		//The above Image instance variable is what you'll set the pixels of.
		//Add any additional code you need in this method here.
		//TODO
	}
	
	//Add any helper methods for the persian recursion here.
	//TODO
	
	// For the "flower" pattern, you can call the following method to generate random
	// colors.  However, for the "Persian recursion" pattern, you should use
	// an instance of the provided ColorPalette class to create a set of colors for your rug.
	static Color randomColor() {
		return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256), 175);
	}
	
	// This method will appear in the NIP menu, in case you want to clear the display during testing.
	public void clear() {
		nip.getTargetPanel().clear();
		Image img = nip.getTargetImage();
		img.fillRegion(0, 0, img.getWidth(), img.getHeight(), Color.WHITE);
	}
	
	@Override
	public void actionNameCalled(String name) {
		if (name == "Flower") makeFlower();
		else if (name == "Persian") makePersian();
		else if (name == "Clear") clear();
		else return;
	}

	@Override
	public String[] getEventNames() {
		String[] s = {"Flower", "Persian", "Clear"};
		return s;
	}
	
	public String toString() {
		return "patterns";
	}
	
	public static void main(String args[]) {
		new NIP(new PatternTool(), 512, 512, 1);
	}

}
