package com.william.drawing;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Santa {

    /**
     * Draw Santa's sleigh and reindeer
     * @param g Graphics2D context
     * @param location starting position
     * @param size scale factor
     */
    public static void drawSanta(Graphics2D g, Point location, double size) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.translate(location.x, location.y);
        g2.scale(size, size);
        g2.setColor(new Color(47, 93, 115));
        g2.setStroke(new BasicStroke(2));

        //REINDEER
        drawReindeer(g2, 150);
        drawReindeer(g2, 200);
        drawReindeer(g2, 250);

        // HARNES
        g2.drawLine(100, 0, 250, 0);

        //SLEIGH
        g2.translate(70, 10);

        // sleigh body
        g2.fill(new RoundRectangle2D.Double(0, -15, 40, 15, 8, 8));

        // seat
        g2.fill(new Rectangle2D.Double(10, -30, 15, 15));

        // runners
        g2.draw(new Arc2D.Double(-5, -5, 50, 20, 200, 140, Arc2D.OPEN));

        // Santa head
        g2.fill(new Ellipse2D.Double(12, -38, 10, 10));

        g2.dispose();
    }

    private static void drawReindeer(Graphics2D g2, int xOffset) {
        g2.translate(xOffset, 0);

        // body
        g2.fill(new Ellipse2D.Double(-15, -6, 30, 12));

        // head
        g2.fill(new Ellipse2D.Double(15, -10, 10, 8));

        // legs
        g2.fill(new Rectangle2D.Double(-8, 6, 3, 12));
        g2.fill(new Rectangle2D.Double(2, 6, 3, 12));

        // antlers
        Path2D antlers = new Path2D.Double();
        antlers.moveTo(20, -12);
        antlers.lineTo(25, -18);
        antlers.lineTo(23, -14);
        antlers.lineTo(28, -16);
        g2.draw(antlers);

        g2.translate(-xOffset, 0);
    }
}