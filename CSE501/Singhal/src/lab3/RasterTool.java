//TA Grade: 100/100
//Bridge completed
package lab3;


import java.awt.Color;

import nip.*;

/**
 * Name: Gaurav Singhal	
 * Lab Section: 501N A
 * Date: 13 Feb 2010
 * Email: gsinghal@wustl.edu
 * RasterTool.java CSE 131 Lab 3
 */

public class RasterTool extends Tool {

	// Your methods go here.

	public void flipHoriz(Image source, Image target) {
		for (int x = 0; x < source.getWidth(); x++) {
			for (int y = 0; y < source.getHeight(); y++) {
				target.setPixel(x, y, source.getPixel(source.getWidth() - 1 - x, y));


			}
		}
	} 



	// same implementation, as for flipHoriz, except for the changed coordinates
	public void flipVertical(Image source, Image target){
		for (int y = 0; y < source.getHeight(); y++){
			for (int x = 0; x < source.getWidth(); x++) {
				target.setPixel(x, y, source.getPixel(x, source.getHeight() - 1 - y));
			}
		}
	}


	//TA This can be done with just one set of for loops
	public void flipLeftHalf(Image source, Image target){
		for (int y = 0; y < source.getHeight(); y++){
			for (int x = 0; x < (source.getWidth()/2); x++ ){  // half of the image (from width = 0 to width = imageWidth /2) gets copied as it is
				target.setPixel(x, y, source.getPixel(x, y));
			}
			int a = 0;
			for (int x = (source.getWidth()/2) ; x < source.getWidth(); x++)
			{ 
				target.setPixel(x, y, source.getPixel((source.getWidth()/2)-1-a, y)); // rest half of the image (from width = imageWidth /2  to  width = imageWidth) is set a mirror of the left half of source
				a++;
			}
		}
	}

	// same implementation as of flipLeftHalf, but with different coordinates
	public void flipBottomHalf(Image source, Image target){
		for (int x = 0; x<source.getWidth(); x++){
			for (int y = source.getHeight()-1; y > source.getHeight()/2; y--){
				target.setPixel(x, y, source.getPixel(x,y));
			}
			int a=0;
			for (int y = (source.getHeight()/2); y >=0; y--){
				target.setPixel(x, y, source.getPixel(x,(source.getHeight()/2)-1 +a));
				a++;

			}
		}
	}

	//the red and green pixels are set as a function of position in the image.
	// uses equation of the line; y = m*x + c    {y = color intensity; x = current coordinate m = slope; c=0;} 

	public void colorGradient(Image source, Image target){

		int r = 0;
		int g = 0;
		int b = 128;

		for ( int x=0; x<source.getWidth();  x++){
			for (int y=0; y<source.getHeight(); y ++){

				g = (255*y)/source.getHeight();
				r = (255*x)/(source.getWidth());
				b = 128;
				target.setPixel(x, y, new Color(r,g,b));
			}
		}
	}



	//uses the isColorDiff method defines previously to compare color components of each pixel with components of the 4 pixels
	// surrounding it. if the isColorDiff method returns true, then sets the corresponding pixel of the target as Black
	// otherwise sets the corresponding pixel of the target as white
	public void edgeDetect(Image source, Image target){

		for (int y = 1; y < source.getHeight()-1; y++){
			for (int x = 1; x < source.getWidth()-1; x++){
				if ((isColorDiff(source.getPixelColor(x,y), source.getPixelColor(x, y-1), 60)) || (isColorDiff(source.getPixelColor(x,y), source.getPixelColor(x, y+1), 60)) || (isColorDiff(source.getPixelColor(x,y), source.getPixelColor(x-1, y), 60)) || (isColorDiff(source.getPixelColor(x,y), source.getPixelColor(x+1, y), 60))){
					target.setPixel(x, y, Color.BLACK);
				}
				else {
					target.setPixel(x, y, Color.WHITE);
				}
			}

		}
	}



	// helper method for edgeDetect
	//does the comparison between individual color components of two colors, 
	// and return a true value if difference between any of the color components is greater than threshold. 
	public boolean isColorDiff(Color a, Color b, int threshold){
		int red = Math.abs(a.getRed()-b.getRed());
		int green = Math.abs(a.getGreen() - b.getGreen());
		int blue = Math.abs(a.getBlue()- b.getBlue());

		if ((red>threshold) || (green>threshold) || (blue>threshold))
		{
			return true;
		}
		else

			return false;
	}
	/**	Bridge for Lab 3
	 * Image Filter : traverses the whole image and calls the calcColor for every pair of x,y coordinates
	 * param: source Image and target Image 
	 * 
	 * takes in a source image and applies the provided 3X3 array of filter coefficients to every pixel of the source image 
	 * and applies this to the target image with every pixel of the target image as a weighted average of the corresponding pixel in the source image and the filter matrix.
	 */
	public void imgFilter(Image source, Image target){

		for ( int x=1; x<source.getWidth()-1;  x++){
			for (int y=1; y<source.getHeight()-1; y ++){


				target.setPixel(x, y, new Color(calcColor(source,x,y,0), // Red
						calcColor(source,x,y,1), //Green
						calcColor(source,x,y,2))); // BLue



			}
		}
	}


	/**
	 * @return returns an int value for a particular color(R/G/B) which is the weighted sum of neighboring pixel components as indicated in the instructions
	 * @param source = source Image
	 * @param x = x coordinate at the center of 3x3 array
	 * @param y = y coordinate at the center of 3x3 array
	 * @param clrVal = 0 = Red; 1 = Green; 2 =  Blue
	 */
	private int calcColor(Image source, int x, int y, int clrVal){


		double[][] coefs = { // 3x3 array of filter coefficients
				{ .0625, .125, .0625 }, 
				{ .125,  .25,  .125  }, 
				{ .0625, .125, .0625 } 
		};

		// 3x3 array of pixel components of a particular color, with the array centered at x and y 
		double [][]pixelComp = new double [3][3];

		
		for( int i = 0; i < 3; i++)	{
			for (int j = 0; j< 3; j++){
				if(clrVal == 0)
					pixelComp[i][j] = source.getPixelColor(x-1+j, y-1+i).getRed(); // since y increases as u go down
				else if(clrVal == 1)
					pixelComp[i][j] = source.getPixelColor(x-1+j, y-1+i).getGreen();
				else if(clrVal == 2)
					pixelComp[i][j] = source.getPixelColor(x-1+j, y-1+i).getBlue();
				// x increases as u go right
			}
		}
	
		double weightedColor[][] = new double[3][3]; // 3x3 array with each element being the product of corresponding pixel component 
		//and coef
		for (int i = 0; i<coefs.length; i++){
			for (int j = 0; j< coefs[0].length; j++){
				weightedColor[i][j] = coefs[i][j]*pixelComp[i][j]; 	
			}
		}
		double total =0;
		for (int i = 0; i<weightedColor.length; i++){
			for (int j = 0; j< weightedColor[0].length; j++){
				total = total + weightedColor[i][j];
			}
		}

		if(total>=255){
			return  255;
		}
		else return (int)total;
	}




	public String[] getEventNames() {
		String[] s = { 
				"Flip Horizontally",
				"Flip Vertically",
				"Flip Left Half Horizontally",
				"Flip Bottom Half Vertically",
				"Color Gradient",
				"Edge Detection",
				"Image Filter"
		};
		return s;
	}

	// Don't forget to tell your tool here how it should respond when a menu
	// item is clicked!
	public void actionNameCalled(String name) {
		if (name.equals("Flip Horizontally")){
			flipHoriz(nip.getPrimarySourceImage(), nip.getTargetImage());
		}
		else if (name.equals("Flip Vertically")){
			flipVertical(nip.getPrimarySourceImage(), nip.getTargetImage());
		}
		else if (name.equals("Flip Left Half Horizontally")){
			flipLeftHalf(nip.getPrimarySourceImage(), nip.getTargetImage());
		}
		else if (name.equals("Flip Bottom Half Vertically")){
			flipBottomHalf(nip.getPrimarySourceImage(), nip.getTargetImage());
		}
		else if (name.equals("Color Gradient")){

			colorGradient(nip.getPrimarySourceImage(), nip.getTargetImage());
		}
		else if (name.equals("Edge Detection")){
			edgeDetect(nip.getPrimarySourceImage(), nip.getTargetImage());
		}

		else if (name.equals("Image Filter")){
			imgFilter(nip.getPrimarySourceImage(), nip.getTargetImage());
		}



	}

	public String toString() {
		return "raster";
	}

	public static void main(String args[]) {
		new NIP(new RasterTool(), 200, 300, 3, "", "brookings.jpg", "two-bears.jpg");
	}

}
