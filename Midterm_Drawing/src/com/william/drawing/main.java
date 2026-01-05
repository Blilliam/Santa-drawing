package com.william.drawing;

import javax.swing.*;

public class main {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Happy Holidays");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AppPanel field = new AppPanel();

        String input = JOptionPane.showInputDialog("Enter snow fall speed (double):");
        double fallSpeed = Double.parseDouble(input);
        field.dy = fallSpeed;

        frame.add(field);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while (true) {
            field.dropFlakes();
            field.moveSanta();   // move Santa each frame
            field.repaint();
            Thread.sleep(40);
        }
    }
}
