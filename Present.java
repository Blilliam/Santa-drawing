import java.awt.Color;
import java.awt.Graphics2D;

public class Present {

    public int x, y;    // top-left corner of the present
    public int size;    // width and height (square)
    private Color boxColor;
    private Color ribbonColor;

    public Present(int x, int y, int size, Color boxColor, Color ribbonColor) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.boxColor = boxColor;
        this.ribbonColor = ribbonColor;
    }

    public void draw(Graphics2D g2, int x, int y) {
    	this.x = x;
    	this.y = y;
    	
        // 			BOX
        g2.setColor(boxColor);
        g2.fillRect(x, y, size, size);

        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, size, size); // outline

        // 			RIBBON (vertical) 
        int ribbonWidth = size / 5;
        int ribbonX = x + size / 2 - ribbonWidth / 2;
        g2.setColor(ribbonColor);
        g2.fillRect(ribbonX, y, ribbonWidth, size);

        // 			RIBBON (horizontal)
        int ribbonY = y + size / 2 - ribbonWidth / 2;
        g2.fillRect(x, ribbonY, size, ribbonWidth);

        // 			BOW
        int bowSize = size / 4;
        g2.setColor(ribbonColor);
        // left loop
        g2.fillOval(x + size / 2 - bowSize, y - bowSize / 2, bowSize, bowSize);
        // right loop
        g2.fillOval(x + size / 2, y - bowSize / 2, bowSize, bowSize);
        // center knot
        g2.fillOval(x + size / 2 - bowSize / 4, y - bowSize / 4, bowSize / 2, bowSize / 2);
    }
}