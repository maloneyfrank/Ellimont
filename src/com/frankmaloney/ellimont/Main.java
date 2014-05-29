package com.frankmaloney.ellimont;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Ellimont");
		frame.setSize(300, 300);
        frame.setMaximumSize(new Dimension(600, 600));
        frame.setMinimumSize(new Dimension(200, 200));
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MainPanel());
		frame.pack();
	}

}
