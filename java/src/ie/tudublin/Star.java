package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;
/* Create a class called Star to encapsulate the columns of interest from a single row from the file. The columns 
of interest are given in the table above. Feel free to add additional fields if you need them later 
*/
public class Star
{   
    /* Habitability flag 1 = star has a high probability of hosting a human habitable planet
    */
    private boolean hab;
    private String displayName; //The name of the star 
    private float distance; //Distance from the sun in parsecs
    /* xyz galactic cartesian co-ordinates in parsecs (used to draw the star map)
    */
    private float xG;
    private float yG;
    private float zG;
    private float absMag; //star's size 
    
    public void setHab(boolean hab)
    {
        this.hab = hab;
    }

    public boolean getHab()
    {
        return hab;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getxG() {
        return xG;
    }

    public void setxG(float xG) {
        this.xG = xG;
    }

    public float getyG() {
        return yG;
    }

    public void setyG(float yG) {
        this.yG = yG;
    }

    public float getzG() {
        return zG;
    }

    public void setzG(float zG) {
        this.zG = zG;
    }

    public float getAbsMag() {
        return absMag;
    }

    public void setAbsMag(float absMag) {
        this.absMag = absMag;
    }

    // Converts the object to a string for logging etc
    public String toString()
    {
        return displayName + "\t" + hab + "\t" 
            + xG + "\t" + yG + "\t" + zG + "\t" + absMag; 
    }

    public Star(String displayName
        , boolean hab, float xG, float yG, float zG, float distance
        , float absMag
        )
    {
        this.displayName = displayName;
        this.xG = xG;
        this.yG = yG;
        this.zG = xG;
        this.distance = distance;
        this.absMag = absMag;        
    }

    // Default constructor
    // Chain to the other constructor
    // If no default constructor, Java will make one for u
    // Once you add a constructor, you dont get the 
    // default one for free
    public Star()
    {
        this("", false, 0, 0, 0, 0, 0);
    }
    /* Write a constructor that takes a TableRow as a parameter and assigns the fields 
    in the class from the appropriate columns in the TableRow. 
    A tableRow object represents a single row of data values, stored in columns from a table
    */
    public Star(TableRow tr)
    {
        this(
            tr.getString("Display Name")
            , tr.getInt("Hab?") == 1
            , tr.getFloat("Xg")
            , tr.getFloat("Yg")
            , tr.getFloat("Zg")
            , tr.getFloat("Distance")
            , tr.getFloat("AbsMag")
        );
    }

    /* Write code to plot the stars onto the grid. For each star you should:
    - Use the star's Xg and Yg values. Ignore the Zg value when drawing the star
    - Draw a yellow cross at the star position on the grid 
    - Draw a red circle with a dimeter of the star's size 
    - Print the star name beside the star. The text should be left aligned horizontally and centred vertically 
    */
    public void render(PApplet pa)
    {
        float border = pa.width * 0.05f;
        float x = PApplet.map(xG, -5, 5, border, pa.width - border);
        float y = PApplet.map(yG, -5, 5, border, pa.height - border);
        pa.noFill();
        pa.stroke(255, 255, 0);
        pa.line(x, y -5, x, y + 5);
        pa.line(x - 5, y, x + 5, y);
        pa.stroke(255, 0, 0);
        pa.ellipse(x, y, absMag, absMag);
        pa.textAlign(PApplet.LEFT, PApplet.CENTER);
        pa.fill(255);
        pa.text(displayName, x + 50, y);
    }
}