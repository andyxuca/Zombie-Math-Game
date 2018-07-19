//Anant Chaudhary and Andy Xu
//Class that displays that you win
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Win extends JPanel implements ActionListener{
	JFrame f;
	
	JButton back, playGame;
	String score;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//WIN TEXT
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 10000, 10000);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Helvitica", Font.PLAIN, 100));
		g.drawString(" YOU WON!", 15, 140);
		g.setFont(new Font("Helvitica", Font.PLAIN, 70));
		g.drawString("Score: " + score, 30, 250);
	}

	public void setupWindow (Win w) {
		f = new JFrame("First");
		f.setTitle("win");
		
		//BUTTONS TO NAVIGATE
		back = new JButton("HOME PAGE");
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
		Win w = new Win();
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