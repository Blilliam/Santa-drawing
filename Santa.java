import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;

public class Santa {

	/**
	 * Draw Santa's sleig2h and reindeer
	 * 
	 * @param g2, location, size
	 */
	public static void drawSanta(Graphics2D g, Point location, double size) {

		Graphics2D g2 = (Graphics2D) g.create();

		g2.translate(location.x, location.y);
		g2.scale(size, size);
		g2.setColor(new Color(47, 93, 115));
		g2.setStroke(new BasicStroke(2));

		// REINDEER
		drawReindeer(g2, 150);
		drawReindeer(g2, 200);
		drawReindeer(g2, 250);

		// HARNES
		g2.drawLine(100, 0, 250, 0);

		// SLEIg2H
		g2.translate(70, 10);

		// sleigh body
		g2.fillRect(0, -15, 40, 15);

		// seat
		g2.fillRect(10, -30, 15, 15);

		// Santa head
		g2.fillOval(12, -38, 10, 10);

		g2.dispose();
	}

	private static void drawReindeer(Graphics2D g2, int xOffset) {
		g2.translate(xOffset, 0);

		// body
		g2.fillOval(-15, -6, 30, 12);

		// head
		g2.fillOval(15, -10, 10, 8);

		// leg2s
		g2.fillRect(-8, 6, 3, 12);
		g2.fillRect(2, 6, 3, 12);

		// antlers
		// used GPT to do this
		Path2D antlers = new Path2D.Double();
		antlers.moveTo(20, -12);
		antlers.lineTo(25, -18);
		antlers.lineTo(23, -14);
		antlers.lineTo(28, -16);
		g2.draw(antlers);

		g2.translate(-xOffset, 0);
	}
}