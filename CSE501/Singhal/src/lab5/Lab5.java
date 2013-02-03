package lab5;
/**
 * Name: Gaurav	 Singhal
 * Lab Section: 501N A
 * Date: Feb 28, 2010
 * Lab5.java
 * CSE 131 Lab 5
 */

import nip.NIP;

public class Lab5 {

	public static void main(String[] args) {
		NIP nip = new NIP(300, 500, 2, "", "ken.jpg");
		nip.setTool(new EyeballTool(nip.getTargetPanel()));
		
		nip.addTool(new CloningTool());
	}
}
