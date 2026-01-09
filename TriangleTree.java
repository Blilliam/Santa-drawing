import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;

public class TriangleTree {
	private final Point bottomCenter; // position input
	private final int size; // height input
	public static Color treeLeaves;
	public static Color treeTrunk;

	public TriangleTree(Point bottomCenter, int size) {
		this.bottomCenter = bottomCenter;
		this.size = size;
	}

	public void draw(Graphics2D g) {
		int x = bottomCenter.x;
		int y = bottomCenter.y;
		int w = size / 2; // proportional width
		
		//searched up how to do this
		// green gradient that fades toward white (snow-light)
		GradientPaint grad = new GradientPaint(x, y - size, Color.WHITE, x, y, treeLeaves);
		g.setPaint(grad);

		// three stacked triangles
		int part = size / 5;
		int[] xPts = {x - w/2, x, x + w/2 };
		
		for (int i = 0; i < 5; i++) {
			int topY = (y - (i + 1) * part) + /*overlapping*/(i * part)/3;
			
			int[] yPts = { topY + part, topY, topY + part };
			g.fillPolygon(xPts, yPts, 3);
			//outline
			g.setColor(Color.BLACK);
			g.drawPolygon(xPts, yPts, 3);
			//set gradient back
			g.setPaint(grad);

		}

		// trunk
		g.setColor(treeTrunk);
		int trunkW = w / 8;
		g.fillRect(x - trunkW / 2, y, trunkW, size / 5);
	}
}