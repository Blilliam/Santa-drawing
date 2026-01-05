package com.william.drawing;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;

public class TriangleTree {
    private final Point bottomCenter;   // position input
    private final int size;             // height input

    public TriangleTree(Point bottomCenter, int size) {
        this.bottomCenter = bottomCenter;
        this.size = size;
    }

    public void draw(Graphics2D g) {
        int x = bottomCenter.x;
        int y = bottomCenter.y;
        int w = size / 2;               // proportional width

        // green gradient that fades toward white (snow-light)
        GradientPaint grad = new GradientPaint(
                x, y - size, new Color(60, 100, 60),
                x, y, new Color(230, 235, 230));
        g.setPaint(grad);

        // three stacked triangles
        int third = size / 3;
        int[] xPts = {x - w/2, x, x + w/2};
        for (int i = 0; i < 3; i++) {
            int topY = y - (i + 1) * third;
            int[] yPts = {topY + third, topY, topY + third};
            g.fillPolygon(xPts, yPts, 3);
        }

        // thin trunk
        g.setColor(new Color(60, 50, 40));
        int trunkW = w / 8;
        g.fillRect(x - trunkW/2, y, trunkW, size/5);
    }
}