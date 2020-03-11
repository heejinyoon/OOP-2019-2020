package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{
    // Arraylist can grow and shrink
    // Generic
    ArrayList<Star> stars = new ArrayList<Star>(); 

    public void drawStars()
    {
        for(Star s: stars)
        {
            s.render(this);
        }
    }

    public void drawGrid()
    {
        float border = width * 0.05f;

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
    }

    public void loadData()
    {
        Table t = loadTable("HabHYG15ly.csv", "header");
        for(TableRow tr:t.rows())
        {
            Star s = new Star(tr);
            stars.add(s);
        }
    }

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
    }

}
