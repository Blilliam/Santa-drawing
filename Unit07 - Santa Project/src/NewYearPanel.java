import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class NewYearPanel extends JPanel {
	static Toolkit tk = Toolkit.getDefaultToolkit();

	public static final int WIDTH = ((int) tk.getScreenSize().getWidth());
	public static final int HEIGHT = ((int) tk.getScreenSize().getHeight()) - 38;
	public Dimension d = new Dimension(WIDTH, HEIGHT);

	private Point[] flakes;
	public double dy = 1;

	// Santa position and speed
	private double santaX = -230;
	private double santaY = 100;
	private double santaSpeed = 15.0;

	// trees
	int tSize;
	Point tPoint;
	int numOfTrees = 10;
	TriangleTree[] trees = new TriangleTree[numOfTrees];

	public NewYearPanel() {
		Color c = new Color(150, 158, 255);
		this.setBackground(c);
		this.setPreferredSize(d);

		flakes = new Point[500];
		for (int i = 0; i < flakes.length; i++) {
			int flakeX = (int) (Math.random() * WIDTH);
			int flakeY = (int) (Math.random() * HEIGHT);
			flakes[i] = new Point(flakeX, flakeY);
		}
		
		for (int i = 0; i < numOfTrees; i++) {
			tSize = (int)(Math.random() * 200) + 200;
			tPoint = new Point(i * 200, HEIGHT - 100);
			trees[i] = new TriangleTree(tPoint, tSize);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		g2.fill(new Rectangle2D.Double(0, 100, 100, 100));

		// Draw Santa at dynamic position
		Santa.drawSanta(g2, new Point((int) santaX, (int) santaY), 2.0);

		// draw tree
		for (TriangleTree tree:trees) {
			tree.draw(g2);
		}
		
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
		if (santaX > WIDTH) { // 400 = approximate Santa width
			santaX = -400;
		}
	}
}