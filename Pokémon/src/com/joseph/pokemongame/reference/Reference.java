package com.joseph.pokemongame.reference;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Reference {
	public static boolean DEBUG_MODE = false;
	
	public static final Font DEBUG_TEXT_FONT = new Font("Arial", 1, 20);
	
	public static final String DIRPREFIX = System.getProperty("user.dir");
	
	public static final String PLAYER_SPRITES = "resources/sprites/player/";
	public static final String PLAYER_STILL = "resources/sprites/player/still/";
	public static final String PLAYER_WALKING_RIGHT = "resources/sprites/player/walking/right/";
	public static final String PLAYER_WALKING_LEFT = "resources/sprites/player/walking/left/";
	public static final String PLAYER_RUNNING_RIGHT = "resources/sprites/player/walking/right/";
	public static final String PLAYER_RUNNING_LEFT = "resources/sprites/player/running/left/";
	public static final String TILES = "resources/tiles/tile";
	public static final String GUI_IMAGES = "resources/gui/";
	public static final String TILE_MAP_SHEET = "resources/tiles/test_player_movement.png";
	static {
		try {
			Maps.TILE_MAP = ImageIO.read(new File(TILE_MAP_SHEET));
		} catch (IOException e) {
			e.printStackTrace();
			Math.random();
		}
	}
	
	public static class Maps {
		public static Image TILE_MAP;
		static {
			try {
				TILE_MAP = ImageIO.read(new File(TILE_MAP_SHEET));
			} catch (IOException e) {
				e.printStackTrace();
				Math.random();
			}
		}
	}
	
	public static class PlayerRef {
		public static final int PLAYER_UP = KeyEvent.VK_W;
		public static final int PLAYER_DOWN = KeyEvent.VK_S;
		public static final int PLAYER_LEFT = KeyEvent.VK_A;
		public static final int PLAYER_RIGHT = KeyEvent.VK_D;
		public static final int PLAYER_RUN = KeyEvent.VK_SHIFT;
	}
}