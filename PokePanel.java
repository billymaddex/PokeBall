/* PokePanel class
   provides Graphics page and event handlers to draw and manipulate PokeBall
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PokePanel extends JPanel
{  

    //instance variables
    private PokeBall pokeball;
    private CanvasPanel canvas;
    private JPanel buttonPanel;
    private JButton[] pokeButtons;
    private Point click;
    private boolean latchClicked;
    private PokeMon[] pokemons;

    //Constructor to instantiate components
    public PokePanel ()
    {
	Point center = new Point(300, 225);
	int radius = 200;
	latchClicked = false;

	// setup the six pokeButtons
	pokeButtons = new JButton[6];

	// setup the six Pokemons
	pokemons = new PokeMon[6];
	String pokename;

	pokename = "Treecko";
	pokemons[0] = new PokeMon();
	pokemons[0].setName(pokename);
	pokemons[0].setImage(pokename + ".png");
	pokeButtons[0] = new JButton(pokename);

	pokename = "Snorlax";
	pokemons[1] = new PokeMon();
	pokemons[1].setName(pokename);
	pokemons[1].setImage(pokename + ".png");
	pokeButtons[1] = new JButton(pokename);
	
	pokename = "Squirtle";
	pokemons[2] = new PokeMon();
	pokemons[2].setName(pokename);
	pokemons[2].setImage(pokename + ".png");
	pokeButtons[2] = new JButton(pokename);
	
	pokename = "Cyndaquil";
	pokemons[3] = new PokeMon();
	pokemons[3].setName(pokename);
	pokemons[3].setImage(pokename + ".png");
	pokeButtons[3] = new JButton(pokename);

	pokename = "Dratini";
	pokemons[4] = new PokeMon();
	pokemons[4].setName(pokename);
	pokemons[4].setImage(pokename + ".png");
	pokeButtons[4] = new JButton(pokename);

	pokename = "Bedlum";
	pokemons[5] = new PokeMon();
	pokemons[5].setName(pokename);
	pokemons[5].setImage(pokename + ".png");
	pokeButtons[5] = new JButton(pokename);

	for (int index = 0; index < pokeButtons.length; index++)
	    pokeButtons[index].addActionListener(new ButtonListener());
	
	pokeball = new PokeBall(center, radius);
	pokeball.setPokeMon(pokemons[0]);

	//canvas panel is where pokeball will be drawn
	//it will be listening to a mouse to open and close
	canvas = new CanvasPanel();
	canvas.setBackground(Color.white);
	canvas.addMouseListener(new LatchListener());
	canvas.addMouseMotionListener(new LatchListener());

	buttonPanel = new JPanel();
	for (int index = 0; index < pokeButtons.length; index++)
	    buttonPanel.add(pokeButtons[index]);

	setLayout(new BorderLayout());
	add(buttonPanel, BorderLayout.NORTH);
	add(canvas, BorderLayout.CENTER);
    } // end of PokePanel constructor

    // CanvasPanel is the panel where rectangles will be drawn
    private class CanvasPanel extends JPanel
    {

	//this method draws all rectangles specified by a user
	public void paintComponent (Graphics page)
	{
	    super.paintComponent(page);
	    
	    //draw the pokeball
	    pokeball.draw(page);
	}

    } //end of CanvasPanel class


    // listener class for the buttons
    public class ButtonListener implements ActionListener
    {
	public void actionPerformed(ActionEvent event)
	{
	    for (int index = 0; index < pokeButtons.length; index++)
		if (event.getSource() == pokeButtons[index])
		    pokeball.setPokeMon(pokemons[index]);
	    canvas.repaint();
	}
    }

    // listener class that listens to the mouse
    public class LatchListener implements MouseListener, MouseMotionListener
    {
	public void mousePressed (MouseEvent event)
	{
	    click = event.getPoint();
	    if (pokeball.latchClicked(click)) latchClicked = true;
	}

	public void mouseReleased (MouseEvent event)
	{
	    latchClicked = false;
	}

	public void mouseDragged (MouseEvent event)
	{
	    if (latchClicked)
		{
		    pokeball.moveLatch(event.getY());
		    canvas.repaint();
		}
	}

	public void mouseClicked (MouseEvent event) {}
	public void mouseEntered (MouseEvent event) {}
	public void mouseExited (MouseEvent event) {}
	public void mouseMoved (MouseEvent event) {}

    } // end of LatchListener

} // end of PokePanel class
