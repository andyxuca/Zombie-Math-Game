/**
 * Class for drawing background, zombies, and math equation
 * Includes timer that moves and generate zombies
 * Includes timer that sets time limit every time math equation generates
 * 
 * @author Andy Xu 
 * @author Anant Chaudhary
 * @since 3/20/18 
 */
package andyxuca.games.zombiemath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Graveyard extends JPanel implements ActionListener
{	
	JFrame frame;

	//Math Equation Label, numbers, and answers
	private JLabel math = new JLabel ();
	private int num1, num2, answer, wrongAnswer;
	private String equation;

	//buttons for possible math answers
	JButton answer1, answer2; 
	int pick, pickSub;

	//boolean to see if any zombies hit human
	boolean hit = false;
	int q;

	//score
	int score = 0;

	//arrays for zombie coordinates
	private final int numSpawn = 100000;
	private int [] x = new int[numSpawn];
	private int[] y = new int[numSpawn];

	//number of zombies
	private int numZ = 0;

	//menubar items
	JMenuItem instruction, restart, play, pause, scoreDisplay;

	//Image of human in graveyard
	private Image man;
	private int man_x = 350;
	private int man_y = 350;

	//trees in graveyard
	private Image tree;

	//zombies in graveyard, their speeds and directions
	private Image[] zombies = new Image[10000];
	private Image zombie;
	private int speed = 50;
	private int space = 0;
	private int[] xdirection = new int[10000];
	private int[] ydirection = new int[10000];
	private int[] NewXdirection = new int[10000];
	private int[] NewYdirection = new int[10000];

	//timer for spawning and changing zombies
	private Timer tm;

	//timer for time limit per math problem
	private Timer timeCheck;
	int timeLimit = 4;
	private JLabel secondsCount = new JLabel ();

	public Graveyard(){
		tm = new Timer(speed, this);
		tm.start();

		timeCheck = new Timer(1000, this);

		ImageIcon i = new ImageIcon("src/resources/digger.jpg");
		man = i.getImage();

		ImageIcon j = new ImageIcon("src/resources/zombie.gif");
		zombie = j.getImage();

		ImageIcon l = new ImageIcon("src/resources/tree.gif");
		tree = l.getImage();

		answer1 = new JButton();
		answer2 = new JButton();

		frame = new JFrame();
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		//Green Background
		Color z = new Color(51, 51, 0);
		g.setColor(z);
		g.fillRect(0,0, 800, 800);

		//Gravestones
		Color i = new Color(153, 153, 153);
		g.setColor(i);
		for(int a = 0; a<=200; a+=50) {
			g.fillRect(550+a,400, 20, 30);
			g.fillOval(549+a, 390, 20, 20);
		}

		for(int b = 0; b<=200; b+=50) {
			g.fillRect(50+b,400, 20, 30);
			g.fillOval(49+b, 390, 20, 20);
		}

		for(int c = 0; c<=700; c+=50) {
			g.fillRect(50+c,300, 20, 30);
			g.fillOval(49+c, 290, 20, 20);
		}

		for(int d = 0; d<=700; d+=50) {
			g.fillRect(50+d,500, 20, 30);
			g.fillOval(49+d, 490, 20, 20);
		}

		for(int e = 0; e<=700; e+=50) {
			g.fillRect(50+e,600, 20, 30);
			g.fillOval(49+e, 590, 20, 20);
		}

		for(int f = 0; f<=700; f+=50) {
			g.fillRect(50+f,700, 20, 30);
			g.fillOval(49+f, 690, 20, 20);
		}

		for(int h = 0; h<=700; h+=50) {
			g.fillRect(50+h, 200, 20, 30);
			g.fillOval(49+h, 190, 20, 20);
		}


		//black borderlines
		Color b = new Color(0, 0, 0);
		g.setColor(b);
		g.drawRect(10,10,775, 775);

		//bushes
		Color l = new Color(51, 153, 51);
		g.setColor(l);
		g.fillRect(10,20, 300, 50);
		g.fillRect(475,20, 300, 50);

		//trees
		Graphics2D trees = (Graphics2D) g;
		trees.drawImage(tree, 0, 0, null);
		trees.drawImage(tree, 0, 200, null);
		trees.drawImage(tree, 0, 400, null);
		trees.drawImage(tree, 0, 590, null);
		trees.drawImage(tree, 650, 0, null);
		trees.drawImage(tree, 650, 200, null);
		trees.drawImage(tree, 650, 400, null);
		trees.drawImage(tree, 650, 590, null);

		//human digger
		Graphics2D digger = (Graphics2D) g;
		digger.drawImage(man, man_x, man_y, null);

		//zombies
		if(numZ != 0){
			for(int q=0; q<numZ; q++){					
				Graphics2D monster = (Graphics2D) g;
				monster.drawImage(zombies[q], x[q]+NewXdirection[q], y[q]+NewYdirection[q], null);
			}
		}

		//Math and time limit box
		g.setColor(Color.WHITE);
		g.fillRect(150, 0, 500, 75);
	}

	public void actionPerformed(ActionEvent e){
		
		//button for instructions, pauses game
		if (e.getSource() == instruction) {
			InGameInstructions instructionPage = new InGameInstructions();
			instructionPage.setupWindow(instructionPage);
			tm.stop();
			timeCheck.stop();
		}
		
		//restarts game, new window, deletes old one
		else if (e.getSource() == restart) {
			tm.stop();
			timeCheck.stop();
			frame.setVisible(false);
			frame.dispose();
			Graveyard newGame = new Graveyard();
			newGame.setupWindow(newGame);
		}

		//plays game and starts timers
		else if (e.getSource() == play) {
			tm.start();
			pick = pickSub;
			if(pick==1||pick==2)
				timeCheck.start();
		}
		
		//pauses game and stops timers
		else if (e.getSource() == pause) {
			tm.stop();
			timeCheck.stop();
			pickSub = pick;
			pick = 0;
		}

		//  ZOMBIE TIMER STUFF
		else if (e.getSource() == tm){
			space++;

			//Generate new zombie every 20 timer runs
			if(space%20==0) {

				int zx = (int)(Math.random()*700+1);
				while(!(zx>=400)&&!(zx<=200)) {
					zx = (int)(Math.random()*700+1);
				}
				int zy = (int)(Math.random()*700+1);
				while(!(zy>=400)&&!(zy<=200)) {
					zy = (int)(Math.random()*700+1);
				}
				x[numZ]=zx;
				y[numZ]=zy;

				NewZombie zPlus = new NewZombie(zx, zy);

				xdirection[numZ] = zPlus.GetXDirection();
				ydirection[numZ] = zPlus.GetYDirection();
				NewXdirection[numZ] = zPlus.GetXDirection();
				NewYdirection[numZ] = zPlus.GetYDirection();
				zombies[numZ] = zombie;

				numZ++;

			}

			//add to each zombies' direction

			for(int i = 0; i<xdirection.length; i++) {
				NewXdirection[i] += xdirection[i];

			}
			for(int i = 0; i<ydirection.length; i++) {
				NewYdirection[i] += 2*ydirection[i];	
			}

			q = 0;

			//checking for collision between zombie and human
			while(!hit && q < numZ){
				q++;
				if((x[q]+NewXdirection[q] >= 200)&&(x[q]+NewXdirection[q] <= 500)&&(y[q]+NewYdirection[q] >= 300)&&(y[q]+NewYdirection[q] <= 400)) {
					hit = true;

					//stops generating more zombies and stops zombies from moving
					tm.stop();

					//generates new math equation
					num1 = (int)(Math.random()*20+1);
					num2 = (int)(Math.random()*20+1);		
					answer = num1*num2;

					num1 = (int)(Math.random()*20+1);
					num2 = (int)(Math.random()*20+1);		
					wrongAnswer =  num1*num2;

					while(wrongAnswer==answer) {
						num1 = (int)(Math.random()*20+1);
						num2 = (int)(Math.random()*20+1);		
						wrongAnswer =  num1*num2;
					}
					equation = num1 + "x" + num2;
					math.setText(equation);
					pick = (int)(Math.random()*2+1);

					//starts time limit for math equation
					timeLimit = 4;
					timeCheck.start();

					//decides which button correct answer will be on
					if(pick==1) {
						answer1.setText(String.valueOf(answer));
						answer2.setText(String.valueOf(wrongAnswer));
					}
					else if(pick==2){
						answer1.setText(String.valueOf(wrongAnswer));
						answer2.setText(String.valueOf(answer));
					}
				}
			}
		}

		//TIMER FOR TIME LIMIT FOR MATH PROBLEM
		else if(e.getSource() == timeCheck) {
			//have three seconds for each problem, otherwise lose
			timeLimit --;
			secondsCount.setText("TIME: " + String.valueOf(timeLimit));
			if(timeLimit == 0) {
				timeCheck.stop();
				timeLimit = 4;
				LoseScreen loseScreen = new LoseScreen();
				loseScreen.score = String.valueOf(score);
				loseScreen.setupWindow(loseScreen);
				frame.setVisible(false);
			}
		}



		// MATH ANSWER BUTTONS
		else if(e.getSource()==answer1||e.getSource()==answer2){
			//checking button answers

			if(pick==1) {
				//wrong answer, lose screen
				if(e.getSource()==answer1){
					pick = 0;
					LoseScreen loseScreen = new LoseScreen();
					loseScreen.score = String.valueOf(score);
					loseScreen.setupWindow(loseScreen);
					frame.setVisible(false);
					timeCheck.stop();
				}
				//correct answer, start timer and add score
				else if(e.getSource()==answer2){
					pick = 0;
					tm.start();
					timeCheck.stop();
					hit = false;
					x[q] = -1000;
					y[q] = -1000;
					score+=10;
					secondsCount.setText("TIME: ");
					math.setText("Math");
					answer1.setText("0");
					answer2.setText("0");
					scoreDisplay.setText("Score: " + score);
				}

			}

			else if(pick==2){
				//wrong answer, lose screen
				if(e.getSource()==answer2){
					pick = 0;
					LoseScreen loseScreen = new LoseScreen();
					loseScreen.setupWindow(loseScreen);
					loseScreen.score = String.valueOf(score);
					frame.setVisible(false);
					timeCheck.stop();
				}

				//correct answer, start timer and add score
				else if(e.getSource()==answer1){
					pick = 0;
					tm.start();
					hit = false;
					x[q] = -100;
					y[q] = 1000;
					score+=10;
					timeCheck.stop();
					secondsCount.setText("TIME: ");
					math.setText("Math");
					answer1.setText("0");
					answer2.setText("0");
					scoreDisplay.setText("Score: " + score);
				}

			}

			//win game, win screen
			if(score>=250){
				Win winScreen = new Win();
				winScreen.score = String.valueOf(score);
				winScreen.setupWindow(winScreen);
				frame.setVisible(false);
				tm.stop();
				timeCheck.stop();
			}

		}

		repaint();
	}

	public void setupWindow(Graveyard p) {
		//new frame
		frame = new JFrame("Zombie Math Game");

		//new menubar
		JMenuBar menubar = new JMenuBar();
		menubar.setForeground(Color.WHITE);

		//menubar items
		instruction = new JMenuItem("Instructions");
		restart = new JMenuItem("Restart");
		play = new JMenuItem("Play");
		pause = new JMenuItem("Pause");
		scoreDisplay = new JMenuItem("Score: " + score);

		instruction.addActionListener(this);		
		restart.addActionListener(this);		
		play.addActionListener(this);
		pause.addActionListener(this);

		menubar.add(instruction);
		menubar.add(restart);
		menubar.add(scoreDisplay);
		menubar.add(play);
		menubar.add(pause);

		instruction.setOpaque(true);
		instruction.setBackground(Color.BLACK);
		restart.setOpaque(true);
		restart.setBackground(Color.BLACK);
		play.setOpaque(true);
		play.setBackground(Color.BLACK);
		pause.setOpaque(true);
		pause.setBackground(Color.BLACK);
		scoreDisplay.setOpaque(true);
		scoreDisplay.setBackground(Color.GRAY);

		frame.setJMenuBar(menubar);

		//new JLabel for math equation
		math = new JLabel(equation);
		math.setFont(new Font("Serif", Font.PLAIN, 50));
		math.setText("Math");
		p.add(math);

		//new JButtons for math equation answers
		answer1 = new JButton(String.valueOf(answer));
		answer1.addActionListener(this);
		p.add(answer1);

		answer2 = new JButton(String.valueOf(wrongAnswer));
		answer2.addActionListener(this);
		p.add(answer2);

		//new JLabel for time limit for math equation
		secondsCount = new JLabel();
		secondsCount.setFont(new Font("Serif", Font.PLAIN, 50));
		secondsCount.setText("TIME: ");
		p.add(secondsCount);

		//generating frame stuff
		frame.setSize(800, 800);
		p.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(p);
		frame.setVisible(true);

		p.requestFocusInWindow();
	}

	public static void main(String[] args) {
		Graveyard p = new Graveyard();
		p.setupWindow(p);
	}
}
