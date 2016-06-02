package com.theicecreambear.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.theicecreambear.gameobject.GameObject;
import com.theicecreambear.gameobject.StaticObject;
import com.theicecreambear.gameobject.UpdateableObject;
import com.theicecreambear.item.Item;
import com.theicecreambear.player.OverworldPosition;
import com.theicecreambear.player.Player;
import com.theicecreambear.player.WorldPosition;
import com.theicecreambear.refrence.Refrence;
import com.theicecreambear.screen.Screen;

public class GameEngine {
	
	public static boolean running = true;
	public JFrame frame;
	public static GameEngine engine;
	public Graphics g;
	public Graphics g2;
	public BufferedImage i;
	public static String stats = "";
	private Player p1;
	
	/* The three types of Game Objects */
	static ArrayList<GameObject> updateableAndDrawable = new ArrayList<GameObject>();
	static ArrayList<UpdateableObject> updateable = new ArrayList<UpdateableObject>();
	static ArrayList<StaticObject> statics = new ArrayList<StaticObject>();
	
	/**
	 * @deprecated - This method is going to be removed in the final export of the game so that 
	 * <code> Main.main()</code> will be invoked when executing the exported .jar
	 * @param args
	 */
	public static void main(String[] args) {
		engine = new GameEngine();
	}
	
	public static void startGameEngine(String[] args) {
		engine = new GameEngine();
	}
	
	public GameEngine() {
		initialize();
		run();
	}

	public void initialize() {
		frame = new JFrame("Pokemon Remastered (PC indev)");
		frame.setBounds(0,0, Screen.width, Screen.height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// TODO
		p1 = new Player(new OverworldPosition(0,0), new WorldPosition(0,0), new ArrayList<Item>(), true, frame);
		updateableAndDrawable.add(p1);
		
		i = new BufferedImage(Screen.width, Screen.height, BufferedImage.TYPE_INT_RGB);
		g2 = i.createGraphics();
		g = frame.getGraphics();
	}

	public void update(double deltaTime) {
		for(GameObject gameObject: updateableAndDrawable) {
			gameObject.update(deltaTime);
		}
		
		for(UpdateableObject upject : updateable) {
			upject.update(deltaTime);
		}
	}

	public void render(Graphics g, ImageObserver observer) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, Screen.width, Screen.height);
		g2.drawImage(Refrence.tileMap, 0, 0, frame);
		
		for(GameObject gameObject : updateableAndDrawable) {
			gameObject.draw(g2, observer);
		}
		for(StaticObject staject : statics) {
			staject.draw(g, observer);
		}
		g2.setColor(Color.GREEN);
		g2.setFont(new Font("Arial", 1, 20));
		g2.drawString(stats, 25, 60);
		
		g.drawImage(i, 0, 0, frame);
	}

	public void run() {
		long time = System.nanoTime();
		final double tick = 60.0;
		double ms = 1000000000 / tick;
		double deltaTime = 0;
		int ticks = 0;
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
				update(deltaTime);
				ticks++;
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
				stats = "Ticks: " + ticks + " FPS: " + fps + " GT: " + hours + ":" + minutes + ":" + seconds + " P.C: " + p1.wp.toString();
				System.out.println(stats);
				ticks = 0;
				fps = 0;
			}
		}
	}
}