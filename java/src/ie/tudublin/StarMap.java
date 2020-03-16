package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{
    // Arraylist can grow and shrink
    // Generic
    // Declare a global ArrayList of Star objects 

    public float border = width * 0.05f; //50 pixel border 

    ArrayList<Star> stars = new ArrayList<Star>(); 

    int selected1 = -1;
    int selected2 = -1;

    public void drawStars()
    {
        for(Star s: stars)
        {
            s.render(this);
        }
    }

    /* Write code to draw the purple gridlines with labels. You should leave a 50 pixel border around the 
    outside of the drawing window. These gridlines go from -5 parsecs to 5 parsecs on the x and y axis. 
    */
    public void drawGrid()
    {

        stroke(0, 0, 255);
        textAlign(CENTER, CENTER);
        for(int i = -5 ; i <= 5 ; i ++)
        {
            float x = map(i, -5, 5, border, width - border);
            line(x, border, x, height - border);
            line(border, x, width - border, x);    
            
            fill(255);
            text(i, x, border / 2);
            text(i, border / 2, x);
        }
    }

    public void settings()
    {
        size(500, 500);

    }

    public void setup()
    {
        loadData();
        printStars();
        //Testing the map function 
        //System.out.println(map1(-20, -15, -20, 0, 100));
    }

    /*write a method called loadData that loads the data from the file and populates the 
    ArrayList. Call this from setup */

    public void loadData()
    {
        Table t = loadTable("HabHYG15ly.csv", "header");
        for(TableRow tr:t.rows())
        {
            Star s = new Star(tr);
            stars.add(s);
        }
    }

    /*Write a method called printStars that prints the contents of the ArrayList after it has been loaded. You should 
    make a toString method on the Star class to help you do this. Call this from setup after you load the data 
    to make sure it gets loaded correctly. */
    public void printStars()
    {
        for(Star s:stars)
        {
            println(s);
        }
    }

    public void draw()
    {
        background(0);
        drawGrid();
        drawStars();

        //If i have selected one of the stars 
        if(selected1 != -1 && selected2 == -1) {
            Star star1 = stars.get(selected1);
            stroke(255, 255, 0);

            float x = map(star1.getxG(), -5, 5, border, width - border);
            float y = map(star1.getyG(), -5, 5, border, height - border);

            line(x, y, mouseX, mouseY);

        
        } else if (selected1 != -1 && selected2 != -1) {
            Star star1 = stars.get(selected1);
            float x1 = map(star1.getxG(), -5, 5, border, width - border);
            float y1 = map(star1.getyG(), -5, 5, border, height - border);

            Star star2 = stars.get(selected2);
            float x2 = map(star2.getxG(), -5, 5, border, width - border);
            float y2 = map(star2.getyG(), -5, 5, border, height - border);

            stroke(255, 255, 0);
            line(x1, y1, x2, y2);
            fill(255);
            float dist = dist(star1.getxG(), star1.getyG(), star1.getzG(), star2.getxG(), star2.getyG(), star2.getzG());
            text("Distance from " + star1.getDisplayName() + " to " + star2.getDisplayName() + " is " + dist + " parsecs", border,
                    height - 25);
        }
    }

    private float map1(float a, float b, float c, float d, float e) {
        float range1 = c - b;
        float howFar = a - b;

        float range2 = e - d;

        return d + (howFar / range1) * range2;

    }
    
    /* Write code so that you can:
    Click inside a single star and draw a yellow line to the mouse. 
    You can add a mousePressed method to your sketch.
    This method will get called when the mouse is pressed. 
    Use mouseX and mouseY to determine the mouse coordinates.
    When you click a second star, the yellow line should join the two stars and you should
    print the text: Distance from STAR1 to STAR2 is XX parsecs 
    */
    public void mouseClicked() {

        for (int i = 0; i < stars.size(); i++) {
            Star s = stars.get(i);

            float x = map(s.getxG(), -5, 5, border, width - border);
            float y = map(s.getyG(), -5, 5, border, height - border);

            //dist is calculates the distance between two points. 
            if (dist(mouseX, mouseY, x, y) < s.getAbsMag() / 2) {
                if(selected1 == -1) {
                    selected1 = i;
                } else if(selected2 == -1) {
                    selected2 = i;
                } else {
                    selected1 = i;
                    selected2 = -1;
                }
            }
        }
    }

}
