package com.theicecreambear.engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.theicecreambear.game_object.GameObject;
import com.theicecreambear.screen.Screen;

public class GameEngine {
	
	public static boolean running = true;
	public JFrame frame;
	public static GameEngine engine;
	Graphics g;
	BufferedImage i;
	static String stats;
	
	ArrayList<GameObject> updateAndDrawable = new ArrayList<GameObject>();
	
	public static void main(String[] args) {
		engine = new GameEngine();
	}
	
	public GameEngine() {
		initialize();
		run();
	}

	public void initialize() {
		frame = new JFrame("");
		frame.setBounds(0,0, Screen.width, Screen.height);
	}

	public void update(double deltaTime) {

	}

	public void render() {

	}

	public void run() {
		long time = System.nanoTime();
		final double tick = 60.0;
		double ms = 1000000000 / tick;
		double deltaTime = 0;
		int ticks = 0;
		int fps = 0;
		long timer = System.currentTimeMillis();
		long frameLimit = 60;
		long currentTime;
		 int seconds = 0;
		 int minutes = 0;
		 int hours = 0;

		while (running) {

			if (deltaTime >= 1) {
				update(deltaTime);
				ticks++;
				deltaTime--;
			}
			
			render();
			fps++;
			
			while(deltaTime < frameLimit) {
				currentTime = System.nanoTime();
				deltaTime += (currentTime - time) / ms;
				time = currentTime;
			}
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				stats = "Ticks: " + ticks + " FPS: " + fps + " " + hours + ":" + minutes + ":" + seconds;
				ticks = 0;
				fps = 0;
			}
		}
	}
}
