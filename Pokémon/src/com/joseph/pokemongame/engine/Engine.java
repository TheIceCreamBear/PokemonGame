package com.joseph.pokemongame.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.joseph.pokemongame.handlers.InputHandler;
import com.joseph.pokemongame.handlers.SoundHandler;


/**
 * @deprecated - Very old, please don't use. Doesn't even really work.
 * @author David Santamaria
 * @version 0.2.6 Contains everything necessary to update and render a game.
 *          Contains a <code> clock </code> that updates and renders for every
 *          in-game tick. Ticks and fps are now shown on-screen during play
 */
public class Engine {

	public static boolean paused = false;
	public int fps = 150;
	public int gameWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public int gameHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static String stats = "";

	public JFrame frame;
	public InputHandler hHandler;
	public static Graphics g2;
	public static Graphics2D g2d;
	public BufferedImage i;
	private boolean running = true;
	
	// TODO git rid of me
	public int x = gameWidth / 2;
	public int y = gameHeight / 2;
	public static BufferedImage i2;
	
	private Thread soundThread;
	
	public static void initOldEngine(String[] args) {
		new Engine();
	}

	/**
	 * Invokes the <code> run </code> method Terminates the program if stops
	 * running
	 */
	public Engine() {
		run();
		System.exit(-1);
	}

	/**
	 * Initializes variables
	 */
	void initialize() {		
		// Window stuf
		frame = new JFrame("Game");
		frame.setSize(new Dimension(gameWidth, gameHeight));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(gameWidth - 30, gameHeight - 30));

		// Objects
		hHandler = new InputHandler(frame);
		
		// Graphics Stuff
		i = new BufferedImage(gameWidth, gameHeight, BufferedImage.TYPE_INT_RGB);
		g2d = i.createGraphics();
		g2 = frame.getGraphics();

		try {
			i2 = ImageIO.read(new File("C:/Sprites/player_sprite.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		soundThread = new Thread(new Runnable() {
			@Override
			public void run() {
				SoundHandler sHandler = SoundHandler.getNewSoundHandler(null);
				sHandler.loopHandler();
			}
		}, "Sound Thread");
		soundThread.start();
		
		
	}

	// Run the engine
	void run() {
		initialize();
		clock();
	}

	/**
	 * The game's "clock".
	 */
	void clock() {
		long time = System.nanoTime();
		final double tick = 60.0;
		double ms = 400000000 / tick;
		double deltaTime = 0;
		int ticks = 0;
		int fps = 0;
		long timer = System.currentTimeMillis();
		int seconds = 0;
		int minutes = 0;
		int hours = 0;

		while (running) {
			long currentTime = System.nanoTime();
			deltaTime += (currentTime - time) / ms;

			time = currentTime;
			if (deltaTime >= 1) {
				update();
				ticks++;
				deltaTime--;
			}
			render();

			fps++;			

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				seconds++;
				if (seconds > 59) {
					seconds %= 60;
					minutes++;
				}
				if (minutes > 59) {
					minutes %= 60;
					hours++;
				}
				if (hours > 23) {
					// NOOP
				}

				stats = "Ticks: " + ticks + ", FPS: " + fps + ", " + hours + ":" + minutes + ":"  + seconds;
				ticks = 0;
				fps = 0;
			}
		}
	}

	/**
	 * Updates the Engine
	 */
	void update() {
		
	}

	/**
	 * Renders the image, sets all the colors, draws all the shapes
	 */
	void render() {
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, gameWidth, gameHeight);
		
		g2d.setFont(new Font("Arial", 1, 25));
		g2d.setColor(Color.GREEN);
		g2d.drawString(Engine.stats, 10, 60);
		g2.drawImage(i, 0, 0, frame);
		
		g2.drawImage(i2, x, y, frame);
	}

	// Pause may be configured differently to different games
	public void pause() {
		if (InputHandler.handler.isKeyDown(KeyEvent.VK_U))
			paused = false;

		if (paused) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pause();
		} else
			clock();
	}
}