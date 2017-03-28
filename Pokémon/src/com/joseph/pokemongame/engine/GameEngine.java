package com.joseph.pokemongame.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.joseph.pokemongame.gameobject.GameObject;
import com.joseph.pokemongame.gameobject.locks.RenderLockObject;
import com.joseph.pokemongame.gui.ConsoleGuiOverlay;
import com.joseph.pokemongame.gui.IGuiOverlay;
import com.joseph.pokemongame.handlers.GKELAH;
import com.joseph.pokemongame.interfaces.IDrawable;
import com.joseph.pokemongame.interfaces.IUpdateable;
import com.joseph.pokemongame.item.Item;
import com.joseph.pokemongame.player.Player;
import com.joseph.pokemongame.player.TilePosition;
import com.joseph.pokemongame.reference.Reference;
import com.joseph.pokemongame.screen.Screen;
import com.joseph.pokemongame.threads.RenderThread;
import com.joseph.pokemongame.threads.ShutdownThread;

/**
 * 
 * @author Joseph Terribile - Current Maintainer
 * @author David Santamaria - Original Author
 *
 */
public class GameEngine {
	private EnumRenderState state;
	private IGuiOverlay openGui;
	
	private static boolean running = true;
	private static GameEngine instance;
	private static String stats = "";
	private JFrame frame;
	private Graphics g;
	private Graphics g2;
	private BufferedImage i;
	private Player p1;
	private GKELAH gkelah;
	
	private ShutdownThread sDownThread;
	private RenderThread renderThread;
	private RenderLockObject rlo;
	
	/* The three types of Game Objects */
	// 8/29/2016 TODO possibly make these maps, idk
	// TODO static? 3/16/2017
	private static ArrayList<GameObject> updateableAndDrawable = new ArrayList<GameObject>();
	private static ArrayList<IUpdateable> updateable = new ArrayList<IUpdateable>();
	private static ArrayList<IDrawable> drawable = new ArrayList<IDrawable>();
	private static ArrayList<IGuiOverlay> guiElements = new ArrayList<IGuiOverlay>();
	
	public static boolean console = false;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (System.getProperty("sun.arch.data.model").contains("32")) {
			JOptionPane.showMessageDialog(null, "This application does not support the 32 bit JVM, please upgrade to 64bit. The application will now exit.", "JVM Not Supported", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		if (Reference.DEBUG_MODE) {
			
		}
		// -Xms 1024m -Xmx 2048m
		long mem = Runtime.getRuntime().maxMemory();
		System.out.println(mem + "B");
		System.out.println((mem / 1024 / 1024) + "MB");
		System.err.println("x: " + (Screen.TILE_POS_WIDTH * 11) + "y: " + (Screen.HEIGHT * 11));
		Thread.currentThread().setName("EngineUpdateThread1");
		instance = new GameEngine();
		instance.run();
	}
	
//	public static void startGameEngine(String[] args) {
//		instance = new GameEngine();
//		instance.run();
//	}
	
	public GameEngine() {
		initialize();
	}

	public void initialize() {
		this.sDownThread = new ShutdownThread("Shutdown");
		Runtime.getRuntime().addShutdownHook(sDownThread);
		
		this.rlo = new RenderLockObject();
		this.renderThread = new RenderThread("RenderThread", this.rlo, this);
		this.renderThread.start();

		this.state = EnumRenderState.NORMAL_MAP;
		
		this.gkelah = new GKELAH();
		
		this.frame = new JFrame("Pokemon Remastered (PC indev)");
		this.frame.setBounds(0, 0, Screen.TILE_POS_WIDTH * 11, Screen.TILE_POS_HEIGHT * 11);
		this.frame.setVisible(true);
		this.frame.addKeyListener(gkelah);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// TODO
		this.p1 = new Player(new TilePosition(10, 10), new ArrayList<Item>(), true, this.frame);
		updateableAndDrawable.add(this.p1);
		
		
		this.i = new BufferedImage(Screen.TILE_POS_WIDTH * 11, Screen.TILE_POS_HEIGHT * 11, BufferedImage.TYPE_INT_RGB);
		this.g2 = this.i.createGraphics();
		this.g = this.frame.getGraphics();
	}

	public void update(double deltaTime) {
		switch (this.state) {
			case BAG:
				break;
			case BATTLE:
				break;
			case GUI_OVERLAY:
				updateGui(deltaTime);
				break;
			case NORMAL_MAP:
				updateNormal(deltaTime);
				break;
			default:
				break;
		}
	}

	public void render(Graphics g, ImageObserver observer) {
		switch (this.state) {
			case BAG:
				break;
			case BATTLE:
				break;
			case GUI_OVERLAY:
				renderGui(g, observer);
				break;
			case NORMAL_MAP:
				renderNormal(g, observer);
				break;
			default:
				break;
		}
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
				update(deltaTime); // TODO, multy threaded update
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
				if (Reference.DEBUG_MODE) {
					long freeBefore = Runtime.getRuntime().freeMemory();
					System.out.println(freeBefore + "B");
					System.out.println((freeBefore / 1024 / 1024) + "MB");
				}
				System.gc();
				if (Reference.DEBUG_MODE) {
					long freeAfter = Runtime.getRuntime().freeMemory();
					System.out.println(freeAfter + "B");
					System.out.println((freeAfter / 1024 / 1024) + "MB");
				}
			}
		}
	}
	
	private void updateGui(double deltaTime) {
		IGuiOverlay overlayToRemove = null;
		boolean shouldRemove = false;
		for (IGuiOverlay overlay : guiElements) {
			overlay.updateUpdateableGraphicsElements(deltaTime);
			if (overlay.removeGui()) {
				shouldRemove = true;
				overlayToRemove = overlay;
			}
		}
		if (shouldRemove) {
			this.removeIGuiOverlay(overlayToRemove);
		}
	}
	
	private void renderGui(Graphics g, ImageObserver observer) {
		for (IGuiOverlay overlay : guiElements) {
			overlay.drawGuiBackground(g, observer);
			overlay.drawUpdateableGraphicsElements(g, observer);
		}
	}
	
	private void updateNormal(double deltaTime) {
		for(GameObject gameObject: updateableAndDrawable) {
			gameObject.update(deltaTime);
		}
		
		for(IUpdateable upject : updateable) {
			upject.update(deltaTime);
		}
	}
	
	private void renderNormal(Graphics g, ImageObserver observer) {
		this.g2.setColor(Color.BLACK);
		this.g2.fillRect(0, 0, Screen.TILE_POS_WIDTH * 11, Screen.TILE_POS_HEIGHT * 11);
		this.g2.drawImage(Reference.Maps.LARGE_MAP, 0, 0, this.frame);
//		this.g2.drawImage(Reference.Maps.LARGE_MAP, 0, 0, this.frame); TODO break the map into smaller sections

		for (GameObject gameObject : updateableAndDrawable) {
			gameObject.draw(g2, observer);
		}
		
		for (IDrawable staject : drawable) {
			staject.draw(g, observer);
		}
		
		for (IGuiOverlay gui : guiElements) {
			gui.hashCode(); // TODO ?
		}
		
		this.g2.setColor(Color.GREEN);
		this.g2.setFont(Reference.DEBUG_TEXT_FONT);
		this.g2.drawString(stats, 25, 60);
		
		g.drawImage(this.i, 0, 0, this.frame);
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
	
	public EnumRenderState getState() {
		return this.state;
	}
	
	public IGuiOverlay getOpenGui() {
		return openGui;
	}
	
	public void addIGuiOverlay(IGuiOverlay overlay) {
		if (overlay instanceof ConsoleGuiOverlay) {
			console = true;
		}
		guiElements.add(overlay);
		this.openGui = overlay;
		this.state = EnumRenderState.GUI_OVERLAY;
	}
	
	private void removeIGuiOverlay(IGuiOverlay overlay) {
		// TODO think about how to deal with GUI's that have sub GUI's
		if (this.openGui == overlay) {
			guiElements.remove(overlay);
			this.openGui = null;
			this.state = EnumRenderState.NORMAL_MAP;
		}
		
	}
	
	public static GameEngine getInstance() {
		return instance;
	}
	
	public static boolean isRunning() {
		return running;
	}
	
	public enum EnumRenderState {
		NORMAL_MAP,
		/** Draws the stored Gui over its self, and doesnt draw the map */
		GUI_OVERLAY,
		BATTLE, 
		BAG;
	}
}