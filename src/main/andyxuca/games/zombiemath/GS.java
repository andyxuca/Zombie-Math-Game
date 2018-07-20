/**
 * Class for instructions and buttons to navigate
 * 
 * @author Andy Xu 
 * @author Anant Chaudhary
 */
package andyxuca.games.zombiemath;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GS extends JPanel implements ActionListener{
	JFrame f;
	
	// the two buttons on the instructions to go back to the greeting screen or to play game
	JButton back, playGame;
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//backgound will be set to black
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 10000, 10000);
		//font on the black background will be white
		g.setColor(Color.WHITE);
		//All the instructions are printed using g.drawstring
		g.setFont(new Font("Helvitica", Font.PLAIN, 18));
		g.drawString("Instructions:", 15, 100);
		g.drawString("You are a gravedigger trapped in a graveyard at midnight.", 14, 120);
		g.drawString("Zombies are constantly spawning and want to eat your brains.", 14, 140);
		g.drawString("All you have with you is your shovel and knowledge.", 14, 160);
		g.drawString("As the zombies approach you, you need to solve math problems to put them down.", 14, 180);
		g.drawString("You have 3 seconds to solve each math problem.", 14, 200);
		g.drawString("Kill 25 zombies to win. Good Luck!", 14, 240);
	}

	
	public void actionPerformed(ActionEvent e) {
		// if the first button is pressed then the user will be taken back to the greeting screen
		if(e.getSource() == back) {
			GreetingScreen greeting = new GreetingScreen();
			greeting.setupWindow(greeting);
			f.setVisible(false);
		}
		//if the user picks button two then they will be taken back to the game
		else if(e.getSource() == playGame) {
			Graveyard gameplay = new Graveyard();
			gameplay.setupWindow(gameplay);
			f.setVisible(false);
		}
	}

	public void setupWindow (GS w) {
		//new frame
		f = new JFrame("First");
		f.setTitle("INSTRUCTIONS!");
		
		//button to go back to home screen
		back = new JButton("GO BACK");
		back.addActionListener(this);
		w.add(back);
		
		//button to start game
		playGame = new JButton("PLAY GAME");
		playGame.addActionListener(this);
		w.add(playGame);
		
		//frame stuff
		f.setSize(800, 800);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.add(w);
		
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		GS w = new GS();
		w.setupWindow(w);
	}

}

