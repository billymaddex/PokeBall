/* PokeBall class
   instantiate to create a PokeBall graphic object
 */

import java.awt.*;
import javax.swing.*;

public class PokeBall
{

    private PokeMon pokemon;
    private Point center, latch;
    private int radius;
    private int thickness, outter, inner;
    private int arcDistance, tilt;

    public PokeBall(Point center, int radius)
    {
	this.center = center;
	this.radius = radius;

	latch = new Point(center.x, center.y);

	thickness = (int) (radius / 40);
	outter = (int) (radius / 5);
	inner = (int) (radius / 10);
    }

    // returns true if the mouse click is within the latch area
    public boolean latchClicked(Point click)
    {
	boolean clicked = false;

	if (click.x > latch.x - inner && click.x < latch.x + inner)
	    if (click.y > latch.y - inner && click.y < latch.y + inner)
		clicked = true;

	return clicked;
    }

    // move the latch vertically +/- distance from current position
    // do not allow for movement beyond PokeBall physical limits
    public void moveLatch(int yPos)
    {
	if ( yPos <= center.y && yPos > center.y - radius + outter)
	    latch.y = yPos;
    }

    public void setPokeMon(PokeMon pokemon)
    {
	this.pokemon = pokemon;
	pokemon.setLocation(center);
    }

    public void draw(Graphics page)
    {
	Graphics2D page2d = (Graphics2D) page;
	page2d.setStroke(new BasicStroke(thickness));
	arcDistance = radius * 2  - (center.y - latch.y);
	tilt = (int) (0.1 * (center.y - latch.y));

	// draw pokeball top red oval
	page2d.setColor(Color.red);
	page2d.fillOval(center.x - radius, latch.y, radius * 2, center.y - latch.y);

	// draw pokeball bottom white oval
	page2d.setColor(Color.white);
	page2d.fillOval(center.x - radius, center.y, radius * 2, center.y - latch.y);

	// draw layer 1 black arcs
	page2d.setColor(Color.black);
	page2d.drawArc(center.x - radius, latch.y, radius * 2, center.y - latch.y, 0, -180);
	page2d.drawArc(center.x - radius, center.y, radius * 2, center.y - latch.y, 0, 180);

	// draw pokemon in the pokeball;
	//page2d.setColor(Color.yellow);
	//page2d.fillRect(center.x - 30, center.y - 40, 60, 60);
	pokemon.draw(page2d);

	// draw pokeball top red arc
	page2d.setColor(Color.red);
	page2d.fillArc(center.x - radius, center.y - radius, radius * 2, arcDistance, 0, 180);

	// draw pokeball botton white arc
	page2d.setColor(Color.white);
	page2d.fillArc(center.x - radius, center.y - radius + (center.y - latch.y), radius * 2, arcDistance, 0, -180);

	// draw layer 2 black arcs
	page2d.setColor(Color.black);
	//smaller arcs
	page2d.drawArc(center.x - radius, latch.y, radius * 2, center.y - latch.y, 0, 180);
	page2d.drawArc(center.x - radius, center.y, radius * 2, center.y - latch.y, 0, -180);
	//larger arcs
	page2d.drawArc(center.x - radius, center.y - radius, radius * 2, arcDistance, 0, 180);
	page2d.drawArc(center.x - radius, center.y - radius + (center.y - latch.y), radius * 2, arcDistance, 0, -180);

	// draw pokeball latch
	// draw latch background filled oval
	page2d.setColor(Color.white);
	page2d.fillOval(latch.x - outter, latch.y - outter + tilt, outter * 2, outter * 2 - tilt);
	// draw latch inner and outter outline
	page2d.setColor(Color.black);
	page2d.drawOval(latch.x - outter, latch.y - outter + tilt, outter * 2, outter * 2 - tilt);
	page2d.drawOval(latch.x - inner, latch.y - inner + tilt, inner * 2, inner * 2 - tilt);
    }

}
