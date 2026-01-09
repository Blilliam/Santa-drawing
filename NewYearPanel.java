
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class NewYearPanel extends JPanel {
	// used chatGPT to figure out how to get screen size
	static Toolkit tk = Toolkit.getDefaultToolkit();

	public static final int WIDTH = ((int) tk.getScreenSize().getWidth());
	public static final int HEIGHT = ((int) tk.getScreenSize().getHeight()) - 38;
	public Dimension d = new Dimension(WIDTH, HEIGHT);

	private Point[] flakes;
	private int[] dy;

	// Santa position and speed
	private int santaX;
	private int santaY;
	private int santaDy;

	private int santaYUpperLimit;
	private int santaYLowerLimit;

	private int santaSpeed;
	// present
	// Present state
	private boolean presentDropped;
	private double presentX, presentY;
	private int presentSpeed; 

	Present p;

	// trees
	int tSize;
	Point tPoint;
	int numOfTrees;
	TriangleTree[] trees;

	// cabin
	double hutSize;
	Cabin hut;

	public NewYearPanel() {
		// Color c = new Color(150, 158, 255);
		this.setBackground(new Color(15, 15, 60));
		this.setPreferredSize(d);

		// Santa position and speed
		santaX = -230;
		santaY = 100;
		santaDy = 2;

		santaYUpperLimit = 130;
		santaYLowerLimit = 70;

		santaSpeed = 15;
		// present
		// Present state
		presentDropped = false;
		presentSpeed = 7; // falling speed

		p = new Present(10000, 400, 50, Color.RED, Color.GREEN);

		// trees
		numOfTrees = 10;
		trees = new TriangleTree[numOfTrees];

		// cabin
		//hutSize = 1.5;
		hutSize = 3;
		hut = new Cabin(WIDTH / 2, HEIGHT - 220, (int) (180 * hutSize), (int) (120 * hutSize), (int) (50 * hutSize));

		//starting flake positions
		flakes = new Point[500];
		dy = new int[500];
		for (int i = 0; i < flakes.length; i++) {
			int flakeX = (int) (Math.random() * WIDTH);
			int flakeY = (int) (Math.random() * HEIGHT);
			flakes[i] = new Point(flakeX, flakeY);
			dy[i] = (int) (Math.random() * 4) + 1;
		}
		
		//random trees
		for (int i = 0; i < numOfTrees; i++) {
			tSize = (int) (Math.random() * 150) + 250;
			tPoint = new Point(i * ((int) (Math.random() * 50) + 175), HEIGHT - 150);
			trees[i] = new TriangleTree(tPoint, tSize);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();

		// ground
		g2.setColor(Color.WHITE);
		g2.fillRect(0, HEIGHT - 130, WIDTH, 130);

		// moon
		g2.setColor(new Color(200, 200, 200));
		g2.fillOval(100, 100, 200, 200);

		// Draw Santa
		Santa.drawSanta(g2, new Point((int) santaX, santaY), 2.0);

		// draw present
		if (presentDropped) {
			p.draw(g2, (int) presentX, (int) presentY);
		}

		// draw tree
		for (TriangleTree tree : trees) {
			tree.draw(g2);
		}

		// draw hut
		hut.draw(g2);

		// Draw snowflakes
		g2.setColor(Color.white);
		for (Point flake : flakes) {
			g2.fillOval(flake.x, flake.y, 7, 7);
		}

		g2.dispose();
	}

	public void dropFlakes() {
		//update flakes so they fall
		int i = 0;
		for (Point flake : flakes) {
			flake.setLocation((int) (flake.x + (Math.random() * 2) - 1), flake.y + dy[i++]);

			// Reset if out of bounds
			if (flake.y >= HEIGHT)
				flake.y = 0;
			if (flake.x >= WIDTH)
				flake.x = 0;
			if (flake.x <= 0)
				flake.x = WIDTH;
		}
	}

	public void moveSanta() {
		//make santa fly 
		santaX += santaSpeed;
		if (santaX > WIDTH) {
			santaX = -400; // reset Santa
			presentDropped = false; // ready for next drop
		}
		//up/down
		santaY += santaDy;
		if (santaY >= santaYUpperLimit || santaY <= santaYLowerLimit) {
			santaDy = -santaDy;
		}

		// Trigger present drop when Santa is above cabin
		if (!presentDropped && santaX + 100 >= hut.x && santaX <= hut.x + hut.w) {
			presentDropped = true;
			presentX = santaX + 220; // at santa's x (below the cart)
			presentY = santaY + 50; // slightly below him
		}
	}

	public void dropPresent() {
		if (presentDropped) {
			presentY += presentSpeed;

			// Stop at the hut (same as snow height)
			if (presentY >= HEIGHT - 50 - p.size) {
				presentY = HEIGHT - 50 - p.size;
				presentDropped = false; // can reset for next drop if desired
			}
		}
	}
}