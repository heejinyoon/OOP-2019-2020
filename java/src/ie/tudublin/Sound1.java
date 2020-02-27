package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class Sound1 extends PApplet
{	
	Minim minim;
	AudioInput ai;
	AudioSample as;

	public void settings()
	{
		size(1024, 500);
	}

	public void setup() 
	{
		minim = new Minim(this);
		ai = minim.getLineIn(Minim.MONO, width, 44100, 16);

		as = minim.loadSample("heroplanet.mp3");
		//as.trigger();
		colorMode(HSB);
		circy = height / 2;
		lerpedcircley = circy;
	}

	float offs = 0;

	float circy;
	float lerpedcircley;
	float lerpedw = 0;
	
	public void draw()
	{	
		background(0);		
		stroke(255);
		float cy = height / 2;
		float sum = 0;
		for(int i = 0 ; i < ai.bufferSize() ; i ++)
		{
			stroke(
				map(i, 0, ai.bufferSize(), 0, 255)
				, 255
				, 255
			);
			//line(i, cy, i, cy + ai.left.get(i) * cy);
			sum += abs(ai.left.get(i));
		}
		float average = sum / ai.bufferSize();

		float w  = average * 1000;
		lerpedw = lerp(lerpedw, w, 0.1f);
		noStroke();
		fill(
			map(average, 0, 1, 0, 255)
			, 255
			, 255
		);
		ellipse(400 , cy,w, w);
		ellipse(600 , cy,lerpedw, lerpedw);


		/*
		for(int i = 0 ; i < as.bufferSize() ; i ++)
		{
			stroke(
				map(i, 0, as.bufferSize(), 0, 255)
				, 255
				, 255
			);
			line(i, cy, i, cy + as.left.get(i) * cy);
		}
		*/

		circy += random(-20, 20);
		lerpedcircley = lerp(lerpedcircley, circy, 0.1f);
		ellipse(100, circy, 50, 50);
		ellipse(200, lerpedcircley, 50, 50);
		
	}
}
