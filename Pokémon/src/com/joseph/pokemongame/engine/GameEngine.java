package com.joseph.pokemongame.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.joseph.pokemongame.gameobject.GameObject;
import com.joseph.pokemongame.gameobject.locks.RenderLockObject;
import com.joseph.pokemongame.gui.IGuiOverlay;
import com.joseph.pokemongame.interfaces.IDrawable;
import com.joseph.pokemongame.interfaces.IUpdateable;
import com.joseph.pokemongame.item.Item;
import com.joseph.pokemongame.player.TilePosition;
import com.joseph.pokemongame.player.Player;
import com.joseph.pokemongame.refrence.Refrence;
import com.joseph.pokemongame.screen.Screen;
import com.joseph.pokemongame.threads.RenderThread;

/**
 * 
 * @author Joseph Terribile - Current Maintainer
 * @author David Santamaria - Original Author
 *
 */
public class GameEngine {
	private RenderState state;
	
	private static boolean running = true;
	private static GameEngine instance;
	private static String stats = "";
	private JFrame frame;
	private Graphics g;
	private Graphics g2;
	private BufferedImage i;
	private Player p1;
	
	private RenderThread renderThread;
	private RenderLockObject rlo;
	
	/* The three types of Game Objects */
	// 8/29/2016 TODO possibly make these maps, idk
	// TODO static? 3/16/2017
	static ArrayList<GameObject> updateableAndDrawable = new ArrayList<GameObject>();
	static ArrayList<IUpdateable> updateable = new ArrayList<IUpdateable>();
	static ArrayList<IDrawable> drawable = new ArrayList<IDrawable>();
	private static ArrayList<IGuiOverlay> guiElements = new ArrayList<IGuiOverlay>();
	
	/**
	 * @deprecated - This method may get to be removed in the final export of the game so that 
	 * {@link com.theicecreambear.pokemongame.Main#main(String[]) Main.main()} will be invoked when executing 
	 * the exported .jar file.
	 * @param args
	 */
	public static void main(String[] args) {
		// -Xms 1024m -Xmx 2048m
		System.out.println(Runtime.getRuntime().maxMemory());
		System.err.println("x: " + Screen.width + "y: " + Screen.height);
		instance = new GameEngine();
		instance.run();
		Thread.currentThread().setName("EngineUpdateThread1");
	}
	
	public static void startGameEngine(String[] args) {
		instance = new GameEngine();
		instance.run();
	}
	
	public GameEngine() {
		initialize();
	}

	public void initialize() {
		this.state = RenderState.NORMAL_MAP;
		
		this.rlo = new RenderLockObject();
		this.renderThread = new RenderThread("RenderThread", this.rlo, this);
		this.renderThread.start();
		
		this.frame = new JFrame("Pokemon Remastered (PC indev)");
		this.frame.setBounds(0, 0, Screen.width, Screen.height);
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// TODO
		this.p1 = new Player(new TilePosition(10, 10), new ArrayList<Item>(), true, this.frame);
		updateableAndDrawable.add(this.p1);
		
		
		this.i = new BufferedImage(Screen.width, Screen.height, BufferedImage.TYPE_INT_RGB);
		this.g2 = this.i.createGraphics();
		this.g = this.frame.getGraphics();
	}

	public void update(double deltaTime) {
		for(GameObject gameObject: updateableAndDrawable) {
			gameObject.update(deltaTime);
		}
		
		for(IUpdateable upject : updateable) {
			upject.update(deltaTime);
		}
	}

	public void render(Graphics g, ImageObserver observer) {
		this.g2.setColor(Color.BLACK);
		this.g2.fillRect(0, 0, Screen.width, Screen.height);
		this.g2.drawImage(Refrence.Maps.tileMap, 0, 0, this.frame);
		
		for (GameObject gameObject : updateableAndDrawable) {
			gameObject.draw(g2, observer);
		}
		
		for (IDrawable staject : drawable) {
			staject.draw(g, observer);
		}
		
		for (IGuiOverlay gui : guiElements) {
			gui.draw(g, observer);
		}
		
		this.g2.setColor(Color.GREEN);
		this.g2.setFont(Refrence.DEBUG_TEXT_FONT);
		this.g2.drawString(stats, 25, 60);
		
		g.drawImage(this.i, 0, 0, this.frame);
	}

	public void run() {
		// TODO THREAD SECURITY, ONLY THE MAIN THRAD SHOULD CALL THIS AND ONLY THIS CLASS
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
				ticks++;
				update(deltaTime);
				deltaTime--;
			}
			
//			render(g, frame); // TODO make like the non cpu/gpu locked tron game.
			synchronized (rlo) {
				rlo.setWasNotified(true);
				rlo.notify();
			}
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
				System.out.println(Runtime.getRuntime().freeMemory());
				System.gc();
				System.out.println(Runtime.getRuntime().freeMemory());
			}
		}
	}

	public enum RenderState {
		NORMAL_MAP,
		CONSOLE,
		BATTLE, 
		BAG;
	}
	
	private void drawMap() {
		
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public Graphics getG() {
		return this.g;
	}
	
	public Graphics getG2() {
		return this.g2;
	}
	
	public BufferedImage getI() {
		return this.i;
	}
	
	public RenderState getState() {
		return this.state;
	}
	
	public static GameEngine getInstance() {
		return instance;
	}
	
	public static boolean isRunning() {
		return running;
	}
}