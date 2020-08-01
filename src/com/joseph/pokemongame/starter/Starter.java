package com.joseph.pokemongame.starter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.joseph.pokemongame.engine.GameEngine;
import com.joseph.pokemongame.reference.Reference;
import com.joseph.pokemongame.screen.Screen;

public class Starter {
	private JFrame frame;
	
	public static JTextField commandLine;
	public static JTextField runKeyInField;
	
	private JComboBox<String> renderDimensionsBox;
	private JButton startBtn;
	private JButton executeCmd;
	
	public Starter(String[] args) throws IOException {
		frame = new JFrame("Pokemon Remastered (PC indev)");
		frame.setBounds(0,0, Screen.WIDTH, Screen.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Icon icon = new ImageIcon(ImageIO.read(new File(Reference.GUI_IMAGES + "startbutton.png")));
		startBtn = new JButton(icon);
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				frame.dispose();
//				GameEngine.startGameEngine(args);
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
		
		icon = new ImageIcon(ImageIO.read(new File(Reference.GUI_IMAGES + "exeBtn.png")));
		executeCmd = new JButton(icon);
		executeCmd.setToolTipText("For Developers Only");
		executeCmd.setBounds(220, 10, 90, 30);
		executeCmd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				commandLine.setText("");
			}
		});
		frame.add(executeCmd);
		
		String[] resolutions = new String[1];
		renderDimensionsBox =  new JComboBox<String>(resolutions);
		
		icon = new ImageIcon(ImageIO.read(new File(Reference.GUI_IMAGES + "background.png")));
		JLabel label = new JLabel("Temporay picture", icon, 0);
		label.setBounds(0, 0, Screen.WIDTH, Screen.HEIGHT);
		frame.add(label);
		
		frame.setVisible(true);
	}
}