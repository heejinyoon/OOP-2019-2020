package ie.tudublin;

import processing.core.PApplet;


public class Arrays extends PApplet
{	
	//float[] rainFall = new float[12]; //one way to make array 
	float[] rainFall = {45, 37, 55, 27, 38, 50, 79, 48, 104, 31, 100, 58}; //another way to make array 
	//if we change 58 to 58.0 - double 8 bites convert double to float : lose persitions  so we need to 58.0f 
	//not losing any data if int convert to float 
	String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}; 

	public void settings()
	{
		size(500, 500);
	}

	public void setup() 
	{
		for(int i = 0; i < rainFall.length; i++) {

			println(months[i] + "\t" + rainFall[i]);
		}

		for(float f:rainFall) {
			println(f); 
		}// we don't have any index value in this loop 

		for(String s:months)
		{
			println(s);
		}

		int minIndex = 0;

		for (int i = 1; i < rainFall.length; i++) {
			
			if (rainFall[i] < rainFall[minIndex]) {
				minIndex = i;
			}
		}

		println(months[minIndex] + " had the minimum rainFall of  " + rainFall[minIndex]);

		int maxIndex = 0;

		for (int i = 1; i < rainFall.length; i++) {
			
			if (rainFall[i] > rainFall[maxIndex]) {
				maxIndex = i;
			}
		}

		println(months[maxIndex] + " had the minimum rainFall of  " + rainFall[maxIndex]);

		
	}
	
	void drawBarchart() {

		float w = width / (float)rainFall.length;
		float cGap = 255 / (float)rainFall.length;
		noStroke();
		colorMode(HSB);
		//rect(top left x, top left y, width ,height)
		for(int i=0; i < rainFall.length; i++) {
			float x = i * w; 
			fill(i * cGap, 255, 255);
			rect(x, height, w, -rainFall[i]);
		}
	}

	void test() {

	}

	float offset = 0;
	
	
	public void keyPressed()
	{
		if (key == ' ')
		{
			
		}
	}	


	public void draw()
	{	
		background(0);		
		colorMode(HSB);	
		drawBarchart();
	}
}
