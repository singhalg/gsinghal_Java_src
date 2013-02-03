package julia;


import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Point;

import nip.*;

public class Main extends Tool {
	private Point start;
	private Rectangle zoomRect;
	private Julia julia;

	public Main() {
		start = new Point(0,0);
		zoomRect = new Rectangle(0,0,1,1);
	}


	/**
	 * Pressed to initiate forming a square for zooming into the picture.
	 */
	public void mousePressed(MouseEvent e) {
		GraphicsPanel panel = (GraphicsPanel)(e.getSource());
		// Remember the start so we can set corners later, as the
		//    box gets dragged
		start.x = e.getX();
		start.y = e.getY();
		zoomRect = new Rectangle(start.x, start.y, 1, 1);
		zoomRect.setLineColor(Color.white);
		panel.add(zoomRect);
	}

	/**
	 * As the mouse is dragged, enlarge or shrink the white square that
	 *    shows the zoom-To area.
	 */
	public void mouseDragged(MouseEvent e) {
		GraphicsPanel panel = (GraphicsPanel)(e.getSource());
		int x = e.getX();
		int y = e.getY();
		if (panel.getBounds().contains(x,y)) {
			zoomRect.setCorners(start.x, start.y, x, y);
			int width = zoomRect.getWidth();
			int height = zoomRect.getHeight();
			//
			//  Keep this a square, so take the min of the width and height
			//
			int least = Math.min(width, height);
			zoomRect.setCorners(start.x, start.y, start.x+least, start.y+least);
		}
	}

	/**
	 * Done with making the square, time to zoom
	 */
	public void mouseReleased(MouseEvent e) {
		check();
		GraphicsPanel panel = (GraphicsPanel)e.getSource();
		panel.remove(zoomRect);
		Point ul = new Point(zoomRect.getX(), zoomRect.getY());
		int size = Math.min(zoomRect.getWidth(), zoomRect.getHeight());
		julia.zoomTo(ul, size);
	}

	public String toString() {
		return "julia";
	}

	public static void main(String args[]) {
		new NIP(new Main(), 512, 512, 1);
	}

	private void check() {
		if (julia == null) {
			System.out.println("You have to pick Julia from the methods menu first");
			System.exit(-1);
		}
	}

	public void actionNameCalled(String name) {
		if (name.equals("Julia")) {
			julia = new Julia(nip.getTargetImage());
			julia.reset();
			julia.draw();
		}
		check();
		if (name.equals("Bump")) {
			julia.bump();
		}
		if (name.equals("UnBump")) {
			julia.unbump();
		}
		if (name.equals("ZoomIn")) {
			julia.zoomIn();
		}
		if (name.equals("ZoomOut")) {
			julia.zoomOut();
		}
	}

	public String[] getEventNames() {
		return new String[] { "Julia", "Bump", "UnBump", "ZoomIn", "ZoomOut" };
	}

}
