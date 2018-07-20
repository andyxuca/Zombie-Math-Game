/**
 * Panel that displays that you lost
 * 
 * @author Andy Xu 
 * @author Anant Chaudhary
 */
package andyxuca.games.zombiemath;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoseScreen extends JPanel implements ActionListener{
	JFrame f;
	JButton back, playGame;
	String score;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//LOSE TEXT
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 10000, 10000);
		g.setColor(Color.RED);
		g.setFont(new Font("Helvitica", Font.PLAIN, 100));
		g.drawString(" YOU LOSE!!!", 15, 140);
		g.setFont(new Font("Helvitica", Font.PLAIN, 70));
		g.drawString("Score: " + score, 50, 250);

	}

	public void setupWindow (LoseScreen w) {
		f = new JFrame("First");
		f.setTitle("lose");
		
		//BUTTONS TO NAVIGATE
		back = new JButton("HOME SCREEN");
		back.addActionListener(this);
		w.add(back);

		playGame = new JButton("PLAY AGAIN");
		playGame.addActionListener(this);
		w.add(playGame);
		
		f.setSize(800, 800);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.add(w);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		LoseScreen w = new LoseScreen();
		w.setupWindow(w);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//BUTTONS
		if(e.getSource() == back) {
			GreetingScreen greeting = new GreetingScreen();
			greeting.setupWindow(greeting);
			f.setVisible(false);
		}
		else if(e.getSource() == playGame) {
			Graveyard gameplay = new Graveyard();
			gameplay.setupWindow(gameplay);
			f.setVisible(false);
		}

	}

}