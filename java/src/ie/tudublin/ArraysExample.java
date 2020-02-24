package ie.tudublin;

import java.util.Arrays; 
import java.util.Collections; 

import processing.core.PApplet;

public class ArraysExample extends PApplet
{	

	//float[] rainFall = new float[12]; 
	float[] rainFall = {45, 37, 55, 27, 38, 50, 79, 48, 104, 31, 100, 58};
	String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

	public void settings()
	{
		size(500, 500);
	}

	public void setup() 
	{
		for(int i = 0 ; i < rainFall.length; i ++)
		{
			println(months[i] + "\t" + rainFall[i]);
		}

		for(float f:rainFall)
		{
			println(f);
		}

		for(String s:months)
		{
			println(s);
		}

		int minIndex = 0;
		for(int i = 1 ; i < rainFall.length ; i ++)
		{
			if (rainFall[i] < rainFall[minIndex])
			{
				minIndex = i;
			}
		}
		println(months[minIndex] + " had the minimum rainfall of " + rainFall[minIndex]);
		
		int maxIndex = 0;
		for(int i = 1 ; i < rainFall.length ; i ++)
		{
			if (rainFall[i] > rainFall[maxIndex])
			{
				maxIndex = i;
			}
		}
		println(months[maxIndex] + " had the maximum rainfall of " + rainFall[maxIndex]);

		// You can also calculate the minimum and max of an arry this way:
		// Note the array is of type Float not float
		// Float are objects float is a primitive type
		Float[] floatArray = {10.0f, 5.0f, 20.0f};
		float min = Collections.min(Arrays.asList(floatArray)); 
        float max = Collections.max(Arrays.asList(floatArray));
	}

	void drawBarChart()
	{
		float w = width / (float) rainFall.length;
		float cGap = 255 / (float) rainFall.length;
		noStroke();
		colorMode(HSB);
		for(int i = 0 ; i < rainFall.length ; i ++)
		{
			float x = i * w;
			fill(i * cGap, 255, 255);
			rect(x, height, w, -rainFall[i]);
		}
	}

	void drawPieChart()
    {
        float cx = width / 2;
        float cy = height / 2;

        float w = width * 0.8f;
        //arc(cx, cy, w, w, 0, TWO_PI, ARC);

        float total = 0;
        colorMode(HSB);
        for (int i = 0 ; i < rainFall.length ; i ++)
        {
            total += rainFall[i];
        }

        float runningSum = 0;
        for (int i = 0 ; i < rainFall.length ; i ++)
        {
            float next = runningSum + rainFall[i];
            float start = map(runningSum, 0, total, 0, TWO_PI);
            float end = map(next, 0, total, 0, TWO_PI);
            fill(map(i, 0, rainFall.length, 0, 255), 255, 255);
            arc(cx, cy, w, w, start, end, ARC);
            runningSum = next;
        }

    }

	public void keyPressed()
	{
		if (key == ' ')
		{
			
		}
	}	

	void drawLineGraph()
	{
		float border = width * 0.1f;

		textAlign(CENTER, CENTER);
		fill(255);
		line(border, border, border, height - border);
		for(int ya = 0 ; ya <= 150 ; ya += 10)
		{
			float y = map(ya, 0, 150, height - border, border);
			line(border, y, border - 5, y);
			text(ya, border / 2, y);
		}

		line(border, height - border, width - border, height - border);
		for(int i = 0 ; i < months.length ; i ++)
		{
			float x = map(i, 0, months.length - 1, border, width - border);
			line(x, height - border, x, height - (border + 5));
			text(months[i], x, height - (border / 2));
		}

		for(int i = 0 ; i < rainFall.length - 1 ; i ++)
		{
			float x1 = map(i, 0, months.length - 1, border, width - border);
			float x2 = map(i + 1, 0, months.length - 1, border, width - border);
			
			float y1 = map(rainFall[i], 0, 150, height - border, border);
			float y2 = map(rainFall[i + 1], 0, 150, height - border, border);

			line(x1, y1, x2, y2);
		}
	}
	

	public void draw()
	{	
		background(0);		
		colorMode(HSB);	

		//drawBarChart();
		//drawLineGraph();
		drawPieChart();
		stroke(255);
	}
}
