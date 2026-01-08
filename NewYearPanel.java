package com.william.drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class NewYearPanel extends JPanel {
	//used chatGPT to figure out how to get screen size
	static Toolkit tk = Toolkit.getDefaultToolkit();

	public static final int WIDTH = ((int) tk.getScreenSize().getWidth());
	public static final int HEIGHT = ((int) tk.getScreenSize().getHeight()) - 38;
	public Dimension d = new Dimension(WIDTH, HEIGHT);

	private Point[] flakes;
	public double dy = 1;

	// Santa position and speed
	private double santaX = -230;
	private int santaY = 100;
	private int santaDy = 2;
	
	private int santaYUpperLimit = 130;
	private int santaYLowerLimit = 70;
	
	private double santaSpeed = 15.0;
	//present
	// Present state
	private boolean presentDropped = false;
	private double presentX, presentY;
	private double presentSpeed = 7.0; // falling speed

	
	Present p = new Present(10000, 400, 50, Color.RED, Color.GREEN);

	// trees
	int tSize;
	Point tPoint;
	int numOfTrees = 10;
	TriangleTree[] trees = new TriangleTree[numOfTrees];
	
	//cabin
	double hutSize = 1.5;
	Cabin hut = new Cabin(WIDTH/2, HEIGHT - 220, (int)(180 * hutSize), (int)(120 * hutSize), (int)(50 * hutSize));

	public NewYearPanel() {
		//Color c = new Color(150, 158, 255);
		this.setBackground(new Color(15, 15, 60));
		this.setPreferredSize(d);
		
		flakes = new Point[500];
		for (int i = 0; i < flakes.length; i++) {
			int flakeX = (int) (Math.random() * WIDTH);
			int flakeY = (int) (Math.random() * HEIGHT);
			flakes[i] = new Point(flakeX, flakeY);
		}
		
		for (int i = 0; i < numOfTrees; i++) {
			tSize = (int)(Math.random() * 200) + 200;
			tPoint = new Point(i * ((int)(Math.random() * 100) + 150), HEIGHT - 150);
			trees[i] = new TriangleTree(tPoint, tSize);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, HEIGHT - 130, WIDTH, 130);

		// Draw Santa 
		Santa.drawSanta(g2, new Point((int) santaX, santaY), 2.0);
		
		//draw present
		if (presentDropped) {
		    p.draw(g2, (int)presentX, (int)presentY);
		}

		// draw tree
		for (TriangleTree tree:trees) {
			tree.draw(g2);
		}
		
		//draw hut
		hut.draw(g2);
		
		// Draw snowflakes
		g2.setColor(Color.white);
		for (Point flake : flakes) {
			g2.fillOval(flake.x, flake.y, 7, 7);
		}

		g2.dispose();
	}

	public void dropFlakes() {
		for (Point flake : flakes) {
			flake.setLocation((int) (flake.x + (Math.random() * 2) - 1), flake.y + dy);

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
	    santaX += santaSpeed;
	    if (santaX > WIDTH) {
	        santaX = -400; // reset Santa
	        presentDropped = false; // ready for next drop
	    }

	    santaY += santaDy;
	    if (santaY >= santaYUpperLimit || santaY <= santaYLowerLimit) {
	        santaDy = -santaDy;
	    }

	    // Trigger present drop when Santa is above cabin
	    if (!presentDropped && santaX + 100 >= hut.x && santaX <= hut.x + hut.w) {
	        presentDropped = true;
	        presentX = santaX + 220;       // at santa's x (below the cart)
	        presentY = santaY + 50;       // slightly below him
	    }
	}
	
	public void dropPresent() {
	    if (presentDropped) {
	        presentY += presentSpeed;

	        // Stop at the ground (same as snow height)
	        if (presentY >= HEIGHT - 50 - p.size) { 
	            presentY = HEIGHT - 50 - p.size;
	            presentDropped = false; // can reset for next drop if desired
	        }
	    }
	}
}