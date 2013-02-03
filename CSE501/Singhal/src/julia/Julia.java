package julia;

import java.awt.Color;
import java.awt.Point;

import nip.Image;

public class Julia {
	private int maxIters = 100;
	private int iters;
//	private int x;
	//private int y;
	private int size; 
	final  Complex ul = new Complex(-2, 2);
	final  Complex lr = new Complex(2, -2);
	ComplexRaster cr = new ComplexRaster(ul, lr, size, size);
	Image image;


	/**
	 * Constructor:  prepare to be able to draw on the supplied image.
	 *   Retains the size of the image as the min of its width and height.
	 *   Initially, the displayed pixels correspond to:
	 *      -2 + 2i for the upper-left-hand corner
	 *       2 - 2i for the lower-right-hand corner
	 * @param image -- pixels to be set to show the Julia set
	 */
	public Julia(Image image) {


		this.image = image;
		this.size = Math.min(image.getHeight(), image.getWidth());

	


	}

	/**
	 * Reset the raster to show the original complex space and
	 * reset the iterations to 100.
	 */
	public void reset() {
		maxIters = 100;
		// how to reset the raster to show original complex space
	}

	/**
	 * Sets the complex coordinates based on the specified pixel location
	 * @param ul Upper-left corner in pixel coordinates of the zoom-to box
	 * @param widthAndHeight length and width of the zoom-to box, in pixels
	 */
	public void zoomTo(Point ul, int widthAndHeight) {


	}

	/**
	 * Zooms in, which counteracts a zoom out
	 */
	public void zoomIn() {
	}

	/**
	 * Zooms out, which counteracts a zoom in
	 */
	public void zoomOut() {
	}

	/**
	 * Increase the maximum number of iterations by some amount (say, 50)
	 */
	public void bump() {


	}

	/**
	 * Decrease the maximum number of iterations by some amount (say, 50)
	 */
	public void unbump() {
	}

	private int rigor(Complex c) {


		/**
		 *    		Pseudocode for rigor(c):
		 *
		 *    		z = -0.7795 + 0.134 i
		 *    		iters = 0
		 *    		while abs(c) < 2 and iters < maxIters
		 *         	c = c*c + z
		 *         	iters = iters + 1
		 *    		end while
		 *
		 *   		return iters
		 *
		 *
		 */
		iters = 0;
		Complex z = new Complex (-0.7795, 0.134);
		while ((c.abs()<2) && (iters < maxIters)){
			c = z.plus(c.times(c));
			iters++;
		}

		return iters;
	}

	/**
	 * Call this method to draw or redraw the Julia set
	 */
	public void draw() {
		

		for(int i=0; i<size; i++){
			for(int j = 0; j<size; j++){
				Color color = Color.black;
				if (rigor(cr.getPoint(i,j)) < maxIters){
					color = Color.getHSBColor((rigor(cr.getPoint(i,j)) % 256)/255.0f, 1.0f, 1.0f);
					image.setPixel(i,j, color);
				}
			}

		}

	}
	
}

