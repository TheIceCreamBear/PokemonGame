package com.theicecreambear.starter;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.theicecreambear.commands.Commands;
import com.theicecreambear.engine.GameEngine;
import com.theicecreambear.refrence.Refrence;
import com.theicecreambear.screen.Screen;

public class Starter {
	private JFrame frame;
	
	public static JTextField commandLine;
	public static JTextField runKeyInField;
	
	private JButton startBtn;
	private JButton executeCmd;
	
	public Starter(String[] args) throws IOException {
		frame = new JFrame("Pokemon Remastered (PC indev)");
		frame.setBounds(0,0, Screen.width, Screen.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Icon icon = new ImageIcon(ImageIO.read(new File(Refrence.GUI_IMAGES + "startbutton.png")));
		startBtn = new JButton(icon);
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				frame.dispose();
				GameEngine.startGameEngine(args);
				System.exit(-1);
			}
		});
		startBtn.setToolTipText("Start the game");
		startBtn.setBounds(800 - 45, 800, 90, 30);
		frame.add(startBtn);
		
		commandLine = new JTextField();
		commandLine.setBounds(10, 10, 200, 30);
		commandLine.setEditable(true);
		commandLine.setBorder(null);
		commandLine.setToolTipText("For Developers Only");
		frame.add(commandLine);
		
		icon = new ImageIcon(ImageIO.read(new File(Refrence.GUI_IMAGES + "exeBtn.png")));
		executeCmd = new JButton(icon);
		executeCmd.setToolTipText("For Developers Only");
		executeCmd.setBounds(220, 10, 90, 30);
		executeCmd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Commands.executeCommand(commandLine.getText());
				commandLine.setText(null);
			}
		});
		frame.add(executeCmd);
		
		icon = new ImageIcon(ImageIO.read(new File(Refrence.GUI_IMAGES + "background.png")));
		JLabel label = new JLabel("Temporay picture", icon, 0);
		label.setBounds(0, 0, Screen.width, Screen.height);
		frame.add(label);
		
		frame.setVisible(true);
		this.runStartUpLoop();
	}
	
	private void runStartUpLoop() {
		while (true) {
			// TODO
		}
	}
}