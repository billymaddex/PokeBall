/* PokeMon class defines PokeMon that can go in a PokeBall
   Should mostly be a graphic, and a Point
*/

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;

public class PokeMon
{

    final static private int WIDTH = 150;
    final static private int HEIGHT = 150;
    final static private int SIZE = 100;

    private Point location;
    private BufferedImage pokepic;
    private String name;
    private boolean selected;

    //public PokeMon() {}

    public void setName(String name)
    {
	this.name = name;
    }

    public void setImage(String filename)
    {
	try
	    {
		URL imgfile = this.getClass().getResource(filename);
		pokepic = ImageIO.read(imgfile);
	    }
	catch (IOException e) {	}
    }

    public boolean clicked(Point click)
    {
	return false;
    }

    public void setSelected(boolean selected)
    {
	this.selected = selected;
    }

    public boolean isSelected()
    {
	return selected;
    }

    public void setLocation(Point moveTo)
    {
	location = moveTo;
    }

    public void draw(Graphics page)
    {
	// page.setColor(color);
	// page.fillRoundRect(location.x - SIZE / 2, location.y - SIZE / 2, SIZE, SIZE, 10, 10);
	
	// page.setColor(Color.black);
	// page.drawRoundRect(location.x - SIZE / 2, location.y - SIZE / 2, SIZE, SIZE, 10, 10);

	// betterness, use a raster image
	if (pokepic != null) page.drawImage(pokepic, location.x - WIDTH / 2,location.y - HEIGHT / 2, null);
	else
	    {
		page.setColor(Color.yellow);
		page.fillRoundRect(location.x - SIZE / 2, location.y - SIZE / 2, SIZE, SIZE, 10, 10);
	    }

	// also draw name...
	//page.setColor(Color.black);
	//page.drawString(name, location.x - WIDTH / 2, location.y + HEIGHT / 2);
    }

}
