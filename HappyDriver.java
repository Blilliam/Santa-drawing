package com.william.drawing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class HappyDriver {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Happy Holidays");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        NewYearPanel field = new NewYearPanel();

        TriangleTree.treeLeaves = JColorChooser.showDialog(null, "Choose a color for the tree leaves", Color.WHITE);
        TriangleTree.treeTrunk = JColorChooser.showDialog(null, "Choose a color for the tree trunks", Color.WHITE);
        
        frame.add(field);
        JSlider slider  = new JSlider(SwingConstants.VERTICAL, 0, 100, 50);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		frame.add(slider, BorderLayout.EAST);
		
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        

        while (true) {
            field.dropFlakes();
            int speed = slider.getValue();
            field.moveSanta();
            field.dropPresent();
            field.repaint();
            Thread.sleep(speed);
        }
    }
}