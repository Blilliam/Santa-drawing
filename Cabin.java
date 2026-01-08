package com.william.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Cabin {

	public int x, y; // front top-left
	public int w, h; // front width and height
	public int d; // depth

	public Cabin(int x, int y, int width, int height, int depth) {
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
		this.d = depth;
	}

	public void draw(Graphics2D g2) {

		// FRONT WALL
		g2.setColor(new Color(170, 105, 50));
		g2.fillRect(x, y, w, h);

		// RIGHT WALL
		Polygon rightWall = new Polygon();
		rightWall.addPoint(x + w, y);
		rightWall.addPoint(x + w + d, y - d);
		rightWall.addPoint(x + w + d, y + h - d);
		rightWall.addPoint(x + w, y + h);

		g2.setColor(new Color(140, 85, 40));
		g2.fillPolygon(rightWall);

		// FRONT ROOF
		int roofPeakX = x + w / 2;
		int roofPeakY = y - h / 2;

		Polygon roofFront = new Polygon();
		roofFront.addPoint(x - 10, y); // left eave
		roofFront.addPoint(roofPeakX, roofPeakY); // peak
		roofFront.addPoint(x + w + 10, y); // right eave

		g2.setColor(new Color(125, 65, 30));
		g2.fillPolygon(roofFront);

		// LEFT ROOF
		Polygon roofLeft = new Polygon();
		roofLeft.addPoint(x - 10, y); // front left
		roofLeft.addPoint(x - 10 + d, y - d); // back left offset
		roofLeft.addPoint(roofPeakX + d, roofPeakY - d); // back peak offset
		roofLeft.addPoint(roofPeakX, roofPeakY); // front peak

		g2.setColor(new Color(150, 80, 40)); // slightly brighter
		g2.fillPolygon(roofLeft);

		// CHIMNEY

		drawChimney(g2, roofPeakX, roofPeakY);

		// RIGHT ROOF
		Polygon roofRight = new Polygon();
		roofRight.addPoint(x + w + 10, y); // front right
		roofRight.addPoint(x + w + 10 + d, y - d); // back right
		roofRight.addPoint(roofPeakX + d, roofPeakY - d);// back peak
		roofRight.addPoint(roofPeakX, roofPeakY); // front peak

		g2.setColor(new Color(100, 50, 25));
		g2.fillPolygon(roofRight);

		// DOOR
		int doorW = w / 5;
		int doorH = h / 2;
		int doorX = x + w / 2 - doorW / 2;
		int doorY = y + h - doorH;

		g2.setColor(new Color(90, 50, 25));
		g2.fillRect(doorX, doorY, doorW, doorH);

		// WINDOWS
		g2.setColor(Color.YELLOW);
		int win = w / 6;

		// front left window
		int wx1 = x + w / 6;
		int wy1 = y + h / 4;
		g2.fillRect(wx1, wy1, win, win);

		// front right window
		int wx2 = x + w - w / 6 - win;
		g2.fillRect(wx2, wy1, win, win);

		//		 OUTLINES
		g2.setColor(Color.BLACK);

		// front wall outline
		g2.drawRect(x, y, w, h);

		// right wall outline
		g2.drawLine(x + w, y, x + w + d, y - d);
		g2.drawLine(x + w + d, y - d, x + w + d, y + h - d);
		g2.drawLine(x + w + d, y + h - d, x + w, y + h);
		g2.drawLine(x + w, y + h, x + w + d, y + h - d);

		// roof outlines
		g2.drawLine(x - 10, y, roofPeakX, roofPeakY); // front
		g2.drawLine(x + w + 10, y, roofPeakX, roofPeakY); // front
		g2.drawLine(x + w + 10, y, x + w + 10 + d, y - d); // right
		g2.drawLine(x + w + 10 + d, y - d, roofPeakX + d, roofPeakY - d); // right
		g2.drawLine(roofPeakX + d, roofPeakY - d, roofPeakX, roofPeakY); // right peak

		// door outline
		g2.drawRect(doorX, doorY, doorW, doorH);

		// window outlines
		g2.drawRect(wx1, wy1, win, win);
		g2.drawRect(wx2, wy1, win, win);

	}

	public void drawChimney(Graphics2D g2, int roofPeakX, int roofPeakY) {
		// ---------- CHIMNEY
		// down here to draw over previous roof stuff
		int chimneyW = w / 8;
		int chimneyH = h / 3;

		// Base point on the left side of the roof slope
		int chimneyBaseX = roofPeakX - chimneyW / 2;
		int chimneyBaseY = roofPeakY;

		// Match roof slope
		int slopeOffset = d / 4;

		// --- FRONT CHIMNEY
		Polygon chimneyFront = new Polygon();
		chimneyFront.addPoint(chimneyBaseX, chimneyBaseY);
		chimneyFront.addPoint(chimneyBaseX + chimneyW, chimneyBaseY - 18);
		chimneyFront.addPoint(chimneyBaseX + chimneyW, chimneyBaseY - chimneyH);
		chimneyFront.addPoint(chimneyBaseX, chimneyBaseY - chimneyH);

		g2.setColor(Color.DARK_GRAY);
		g2.fillPolygon(chimneyFront);
		g2.setColor(Color.BLACK);
		g2.drawPolygon(chimneyFront); // outline front

		// --- RIGHT CHIMNEY
		Polygon chimneySide = new Polygon();
		chimneySide.addPoint(chimneyBaseX + chimneyW, chimneyBaseY);
		chimneySide.addPoint(chimneyBaseX + chimneyW + slopeOffset, chimneyBaseY - slopeOffset);
		chimneySide.addPoint(chimneyBaseX + chimneyW + slopeOffset, chimneyBaseY - slopeOffset - chimneyH);
		chimneySide.addPoint(chimneyBaseX + chimneyW, chimneyBaseY - chimneyH);

		g2.setColor(Color.GRAY);
		g2.fillPolygon(chimneySide);
		g2.setColor(Color.BLACK);
		g2.drawPolygon(chimneySide); // outline right

		// --- TOP CHIMNEY
		Polygon chimneyTop = new Polygon();
		chimneyTop.addPoint(chimneyBaseX, chimneyBaseY - chimneyH);
		chimneyTop.addPoint(chimneyBaseX + chimneyW, chimneyBaseY - chimneyH);
		chimneyTop.addPoint(chimneyBaseX + chimneyW + slopeOffset, chimneyBaseY - slopeOffset - chimneyH);
		chimneyTop.addPoint(chimneyBaseX + slopeOffset, chimneyBaseY - slopeOffset - chimneyH);

		g2.setColor(new Color(100, 100, 100));
		g2.fillPolygon(chimneyTop);
		g2.setColor(Color.BLACK);
		g2.drawPolygon(chimneyTop); // outline top
	}
}