package com.joseph.pokemongame.spritetester;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SpriteTest extends JFrame {

	private static final long serialVersionUID = 7876630408112116927L;
	private JPanel contentPane;
	public BufferedImage img;
	public Graphics2D g;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpriteTest frame = new SpriteTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SpriteTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		
		panel.setBounds(10, 11, 21, 59);
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.RED);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel.setForeground(Color.WHITE);
			}
		});
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel orangeOp = new JPanel();
		orangeOp.setBounds(41, 11, 21, 59);
		orangeOp.setBackground(Color.ORANGE);
		contentPane.add(orangeOp);
		
		JPanel yellowOp = new JPanel();
		yellowOp.setBounds(72, 11, 21, 59);
		yellowOp.setBackground(Color.YELLOW);
		contentPane.add(yellowOp);
		
		JPanel greenOp = new JPanel();
		greenOp.setBounds(103, 11, 21, 59);
		greenOp.setBackground(Color.GREEN);
		contentPane.add(greenOp);
		
		JPanel cyanOp = new JPanel();
		cyanOp.setBounds(134, 11, 21, 59);
		cyanOp.setBackground(Color.CYAN);
		contentPane.add(cyanOp);
		
		JPanel blueOp = new JPanel();
		blueOp.setBounds(165, 11, 21, 59);
		blueOp.setBackground(Color.BLUE);
		contentPane.add(blueOp);
		
		JPanel magentaOp = new JPanel();
		magentaOp.setBounds(196, 11, 21, 59);
		magentaOp.setBackground(Color.MAGENTA);
		contentPane.add(magentaOp);
		
		JPanel pinkOp = new JPanel();
		pinkOp.setBounds(227, 11, 21, 59);
		pinkOp.setBackground(Color.PINK);
		contentPane.add(pinkOp);
		
		JPanel lightGrayOp = new JPanel();
		lightGrayOp.setBounds(258, 11, 21, 59);
		lightGrayOp.setBackground(Color.LIGHT_GRAY);
		contentPane.add(lightGrayOp);
		
		JPanel grayOp = new JPanel();
		grayOp.setBounds(289, 11, 21, 59);
		grayOp.setBackground(Color.GRAY);
		contentPane.add(grayOp);
		
		JPanel darkGrayOp = new JPanel();
		darkGrayOp.setBounds(320, 11, 21, 59);
		darkGrayOp.setBackground(Color.DARK_GRAY);
		contentPane.add(darkGrayOp);
		
		JPanel blackOp = new JPanel();
		blackOp.setBounds(351, 11, 21, 59);
		blackOp.setBackground(Color.BLACK);
		contentPane.add(blackOp);
		
		JPanel whiteOp = new JPanel();
		whiteOp.setBounds(382, 11, 21, 59);
		whiteOp.setBackground(Color.WHITE);
		contentPane.add(whiteOp);
		
		JPanel canvas = new JPanel();
		canvas.setBounds(10, 93, 548, 319);
		contentPane.add(canvas);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(413, 11, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnImport = new JButton("Import");
		btnImport.setBounds(413, 45, 89, 23);
		contentPane.add(btnImport);
		
	}
}
