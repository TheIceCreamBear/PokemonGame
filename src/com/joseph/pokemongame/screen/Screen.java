package com.joseph.pokemongame.screen;

import java.awt.Toolkit;

public class Screen {
	public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public static final int TILE_POS_WIDTH = WIDTH / 11;
	public static final int TILE_POS_HEIGHT = HEIGHT / 11;
}