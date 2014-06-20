/* PokeMon PokeBall application
   instantiates the PokePanel class (JPanel) to display PokeBall graphic
   in a stand alone Java program
 */

import javax.swing.*;

public class PokeTrainer
{

    public static void main (String[] args)
    {
	final int WIDTH = 600;
	final int HEIGHT = 550;

	JFrame display = new JFrame("PokeBall");
	JPanel pokePanel = new PokePanel();

       	display.getContentPane().add(pokePanel);
	display.setSize(WIDTH, HEIGHT);
	display.setVisible(true);
	display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
