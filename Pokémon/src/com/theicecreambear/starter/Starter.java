package com.theicecreambear.starter;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.theicecreambear.screen.Screen;

public class Starter {
	private JFrame frame;
	private JTextField field;
	
	public Starter() {
		frame = new JFrame("Pokemon Remastered (PC indev)");
		frame.setBounds(0,0, Screen.width, Screen.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		frame.setVisible(true);
		this.runStartUpLoop();
	}
	
	private void runStartUpLoop() {
		
	}
}