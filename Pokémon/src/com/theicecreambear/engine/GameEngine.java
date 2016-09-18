package com.theicecreambear.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.theicecreambear.gameobject.GameObject;
import com.theicecreambear.interfaces.Drawable;
import com.theicecreambear.interfaces.Updateable;
import com.theicecreambear.item.Item;
import com.theicecreambear.player.OverworldPosition;
import com.theicecreambear.player.Player;
import com.theicecreambear.player.WorldPosition;
import com.theicecreambear.refrence.Refrence;
import com.theicecreambear.screen.Screen;

/**
 * 
 * @author Joseph Terribile
 * @author David Santamaria
 *
 */
public class GameEngine {
	
	public RenderState state;
	
	public static boolean running = true;
	public static GameEngine instance;
	public static String stats = "";
	public JFrame frame;
	public Graphics g;
	public Graphics g2;
	public BufferedImage i;
	private Player p1;
	
	private JTextArea consoleReadout;
	private JTextField consoleIn;
	private boolean consoleShowing;
	
	/* The three types of Game Objects */
	// 8/29/16 TODO possibly make these maps, idk
	static ArrayList<GameObject> updateableAndDrawable = new ArrayList<GameObject>();
	static ArrayList<Updateable> updateable = new ArrayList<Updateable>();
	static ArrayList<Drawable> drawable = new ArrayList<Drawable>();
	
	/**
	 * @deprecated - This method may get to be removed in the final export of the game so that 
	 * {@link com.theicecreambear.Main#main(String[]) Main.main()} will be invoked when executing 
	 * the exported .jar file.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().maxMemory());
		System.err.println("x: " + Screen.width + "y: " + Screen.height);
		new GameEngine();
	}
	
	public static void startGameEngine(String[] args) {
		new GameEngine();
	}
	
	public GameEngine() {
		initialize();
		run();
	}

	public void initialize() {
		this.state = RenderState.NORMAL_MAP;
		
		frame = new JFrame("Pokemon Remastered (PC indev)");
		frame.setBounds(0,0, Screen.width, Screen.height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		consoleReadout = new JTextArea(); 
		consoleReadout.setEditable(false);
		consoleReadout.setBounds(0, 0, 600, 400);
		consoleReadout.setBorder(null);
		consoleReadout.setRows(10);
		
		consoleIn = new JTextField();
		consoleIn.setBounds(0, 401, 600, 30);
		consoleIn.setBorder(null);
		consoleIn.setEditable(true);
		
		
		// TODO
		p1 = new Player(new OverworldPosition(0,0), new WorldPosition(0,0), new ArrayList<Item>(), true, frame);
		updateableAndDrawable.add(p1);
		
		
		i = new BufferedImage(Screen.width, Screen.height, BufferedImage.TYPE_INT_RGB);
		g2 = i.createGraphics();
		g = frame.getGraphics();
		
		instance = this;
	}

	public void update(double deltaTime) {
		for(GameObject gameObject: updateableAndDrawable) {
			gameObject.update(deltaTime);
		}
		
		for(Updateable upject : updateable) {
			upject.update(deltaTime);
		}
	}

	public void render(Graphics g, ImageObserver observer) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, Screen.width, Screen.height);
		g2.drawImage(Refrence.Maps.tileMap, 0, 0, frame);
		
		for(GameObject gameObject : updateableAndDrawable) {
			gameObject.draw(g2, observer);
		}
		for(Drawable staject : drawable) {
			staject.draw(g, observer);
		}
		g2.setColor(Color.GREEN);
		g2.setFont(new Font("Arial", 1, 20));
		g2.drawString(stats, 25, 60);
		
		g.drawImage(i, 0, 0, frame);
	}

	/**
	 * THIS IS BAD CODE PLACEMENT AND ORGANIZATION AND IM SORRY, BUT IM TRYING THINGS
	 * 
	 * THIS WORKS BUT I HAVENT MOVED IT BACK YET
	 */
	public int ticks;
	
	public void run() {
		long time = System.nanoTime();
		final double tick = 60.0;
		double ms = 1000000000 / tick;
		double deltaTime = 0;
		ticks = 0;
		int fps = 0;
		long timer = System.currentTimeMillis();
		long frameLimit = 80;
		long currentTime;
		int seconds = 0;
		int minutes = 0;
		int hours = 0;

		while (running) {
			currentTime = System.nanoTime();
			deltaTime += (currentTime - time) / ms;
			time = currentTime;

			if (deltaTime >= 1) {
				ticks++;
				update(deltaTime);
				deltaTime--;
			}
			
			render(g, frame);
			fps++;
			
			while(deltaTime < frameLimit) {
				currentTime = System.nanoTime();
				deltaTime += (currentTime - time) / ms;
				time = currentTime;
			}
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				seconds++;
				if(seconds > 60) {
					seconds %= 60;
					minutes++;
					
					if(minutes > 60) {
						minutes %= 60;
						hours++;
					}
				}
				
				// GT stands for GameTime. P.C stands for Player coordinates
				stats = "Ticks: " + ticks + " FPS: " + fps + " GT: " + ((hours < 10) ? "0" + hours : hours) + ":" + ((minutes < 10) ? "0" + minutes : minutes) + ":" + ((seconds < 10) ? "0" + seconds : seconds);
				System.out.println(stats);
				ticks = 0;
				fps = 0;
				p1.isInMovingPlus = false;
				p1.isInMovingMinus = false;
				System.out.println(Runtime.getRuntime().freeMemory());
				System.gc();
				System.out.println(Runtime.getRuntime().freeMemory());
			}
		}
	}

	public boolean isConsoleShowing() {
		return consoleShowing;
	}

	public void showConsole() {
		consoleShowing = true;
		frame.add(consoleReadout);
		frame.add(consoleIn);
		// TODO Possibly stop pause engine if console is showing?
		
	}
	
	public void hideConsole() {
		consoleShowing = false;
		frame.remove(consoleReadout);
		frame.remove(consoleIn);
	}
	
	public enum RenderState {
		NORMAL_MAP,
		BATTLE,
		BAG;
		
		
	}
}