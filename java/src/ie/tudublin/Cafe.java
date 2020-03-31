package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Cafe extends PApplet
{
    ArrayList<Product> products = new ArrayList<Product>();
    ArrayList<Product> bill = new ArrayList<Product>();

    float border;
    float left;

    float w;
    float h;

    public void loadData()
    {
        Table table = loadTable("cafe.csv", "header");
        for(TableRow row:table.rows())
        {
            Product p = new Product(row);
            products.add(p);
        }
    }

    public void printProducts()
    {
        for(Product p:products)
        {
            System.out.println(p);
        }
    }
    
    public void settings()
    {
        size(800, 600);

        border = width * 0.1f;
        left = width * 0.05f;

        w = width * 0.3f;
        h = height * 0.1f;
    }

    void displayProducts()
    {    
        for(int i = 0 ; i < products.size() ; i ++)
        {
            Product p = products.get(i);

            float y = map(i, 0, products.size(), border, height - border);

            fill(255);
            rect(left, y, w, h);
            fill(0);
            textAlign(LEFT, CENTER);
            text(p.getName(), left + 10, y + (h / 2));
            textAlign(RIGHT, CENTER);
            text(nf(p.getPrice(), 0, 2), left + w - 10, y + (h / 2));
        }
    }

    public void drawBill()
    {
        float billLeft = (width / 2) + 50;
        
        float billHeight = height * 0.8f;

        stroke(0);
        fill(255);
        rect(billLeft, border, w, billHeight);

        float y = border + 50;
        float total = 0;
        for(int i = 0 ; i < bill.size() ; i ++)
        {
            Product p = bill.get(i);
            textAlign(LEFT, CENTER);
            fill(0);
            text(p.getName(), billLeft + 10, y);
            textAlign(RIGHT, CENTER);
            text(nf(p.getPrice(), 0, 2), billLeft + w - 10, y);            
            y += 30;
            total += p.getPrice();
        }
        fill(0);
        textAlign(LEFT, CENTER);
        text("Total:", billLeft + 10, y);
        textAlign(RIGHT, CENTER);
        text(nf(total, 0, 2), billLeft + w - 10, y);            
            

    }

    public void mousePressed()
    {
        for(int i = 0 ; i < products.size() ; i ++)
        {
            float y = map(i, 0, products.size(), border, height - border);
            if (mouseX > left && mouseX < left + w 
                && mouseY > y && mouseY < y + h                 
                )
                {
                    bill.add(products.get(i));
                    break;
                }

        }
    }

    public void draw()
    {
        displayProducts();
        drawBill();
    }

    public void setup()
    {
        loadData();
        printProducts();
    }
}