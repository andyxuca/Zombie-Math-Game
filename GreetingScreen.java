//Anant Chaudhary and Andy Xu
//Class for Home Page with Buttons to navigate to game

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GreetingScreen extends JPanel implements ActionListener{
	JFrame frame;
	// Play Game Button
	JButton b1;
	//Instuctions Button
	JButton b2;
	//background picture on greeting screen
	private Image bg;
	//Sign number 1 on the greeting screen
	private Image signZombie;
	//sign number 2 on the greeting screen 
	private Image signZombie1;
	//width of background image
	private int bg_width;
	//height of the background image
	private int bg_height;

	public GreetingScreen () {
        // the background image is being called 'bg'
		ImageIcon i = new ImageIcon("src/Zombie.jpg");
		bg = i.getImage();
		bg_width = bg.getWidth(null);
		bg_height = bg.getHeight(null);
		
		//signZombie is turning into the first sign
		ImageIcon sign = new ImageIcon("src/Sign2.jpg");
		signZombie = sign.getImage();
		//signZombie1 is becoming the second sign
		ImageIcon Sign = new ImageIcon("src/hello.jpg");
		signZombie1 = Sign.getImage();
	}

	public static void main(String[] args) {
		//sets up the greeting screen window
		GreetingScreen p = new GreetingScreen();
		p.setupWindow(p);
	}


	public int getWidth () {
		//gets my width
		
		return bg_width;
	}

		//gets height
	public int getHeight () {
		return bg_height;
	}


	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//displays background image on the greeting screen
		g2d.drawImage(bg, -700, 0, null);
		//displays both of the signs on the greeting screen
		Graphics2D signZ = (Graphics2D) g;
		g2d.drawImage(signZombie, 25, 525, null);
		Graphics2D signZ1 = (Graphics2D) g;
		g2d.drawImage(signZombie1, 25, 220, null);
		
		//setting the color for the text on the greeting screen
		Color z = new Color(204, 51,153);
		g.setColor(z);

		//text on cover
		g.setFont(new Font("Helvitica", Font.PLAIN, 40));
		g.drawString("THE GRAVEYARD GAME", 15, 50);
		g.setFont(new Font("Helvitica", Font.PLAIN, 20));
		g.drawString("By : Andy Xu and Anant Chaudhary", 15, 100);

	}


	public void actionPerformed(ActionEvent e){
		//if putton 1 is pressed then the game will show up
		if(e.getSource() == b1) {
			Graveyard gameplay = new Graveyard();
			gameplay.setupWindow(gameplay);
			frame.setVisible(false);
		}
		//if button 2 is selected then the instructions will show up
		else if(e.getSource() == b2) {
			GS instructionPage = new GS();
			instructionPage.setupWindow(instructionPage);
			frame.setVisible(false);
		}
	}

	public void setupWindow(GreetingScreen p) {

		frame = new JFrame("GRAVEYARD GAME 101");

		frame.setLayout(new GridLayout(10, 5));
		p.setLayout( new GridLayout(1, 10));
		
		// These e buttons are false buttons so that it could take up space for the grid layout
		JButton e1 = new JButton("");
		e1.setVisible(false);
		p.add(e1);
		JButton e2 = new JButton("");
		e2.setVisible(false);
		p.add(e2);
		JButton e3 = new JButton("");
		e3.setVisible(false);
		p.add(e3);
		JButton e4 = new JButton("");
		e4.setVisible(false);
		p.add(e4);
		
		//The play game button on the greeting screen
		b1 = new JButton("PLAY GAME");
		b1.addActionListener(this);
		p.add(b1);
		//the instructions button on the greeeting screen
		b2 = new JButton("INSTRUCTIONS");
		b2.addActionListener(this);
		p.add(b2);
		//declares the size ogf the greeting screen window
		frame.setSize(800,800);
		frame.setLocationRelativeTo(null);
		//closes the individual window if exit button is selected, not all of them
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(p);
		//window is visble
		frame.setVisible(true);
	}
}

