//Anant Chaudhary and Andy Xu
//Class for instructions during game without any buttons so game will not be disturbed

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class inGameInstructions extends JPanel{
	JFrame f;
	
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
		g.drawString("As the zombies approach you, you need to solve math problems ", 14, 180);
		g.drawString("to put them down. You have 3 seconds to solve each math", 14, 200);
		g.drawString("problem", 14, 220);
		g.drawString("Kill 25 zombies to win. Good Luck!", 14, 260);
	}

	
	public void setupWindow (inGameInstructions w) {
		//new frame
		f = new JFrame("First");
		f.setTitle("INSTRUCTIONS!");
		
		//frame stuff
		f.setSize(600, 600);
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


