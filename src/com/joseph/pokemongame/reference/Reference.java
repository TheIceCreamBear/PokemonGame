package com.joseph.pokemongame.reference;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Reference {
	public static final boolean DEBUG_MODE = false;
	
	public static final Font DEBUG_TEXT_FONT = new Font("Arial", 1, 20);
	public static final Font TEXT_BOX_FONT = new Font("Arial", 1, 20);
	
	public static final Color CURSOR_COLOR = new Color(96, 96, 96);
	
	public static final String DIRPREFIX = System.getProperty("user.dir");
	public static final String PLAYER_SPRITES = "resources/sprites/player/";
	public static final String PLAYER_STILL = "resources/sprites/player/still/";
	public static final String PLAYER_WALKING_RIGHT = "resources/sprites/player/walking/right/";
	public static final String PLAYER_WALKING_LEFT = "resources/sprites/player/walking/left/";
	public static final String PLAYER_RUNNING_RIGHT = "resources/sprites/player/walking/right/";
	public static final String PLAYER_RUNNING_LEFT = "resources/sprites/player/running/left/";
	public static final String TILES = "resources/tiles/tile/";
	public static final String GUI_IMAGES = "resources/gui/";
	public static final String GUI_BACKGROUNDS = "resources/gui/backgrounds/";
	public static final String TILE_MAP_SHEET = "resources/tiles/test_player_movement.png";
	public static final String LARGE_MAP_SHEET = "resources/tiles/1kTileSquare.png";
	
	public static class Maps {
		public static Image TILE_MAP;
		public static Image LARGE_MAP;
		static {
			try {
				TILE_MAP = ImageIO.read(new File(TILE_MAP_SHEET));
				LARGE_MAP = ImageIO.read(new File(LARGE_MAP_SHEET));
				System.gc();
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
	
	public static class GuiBackgroundRef {
		/**
		 * Generic background for 32 tile wide by 6 tile high GuiOverlays
		 */
		public static BufferedImage _32X6GENERIC_BACK;
		static {
			try {
				_32X6GENERIC_BACK = ImageIO.read(new File(Reference.GUI_BACKGROUNDS + "textBoxBack.png"));
			} catch (IOException e) {
				System.err.println("Failed to aquire the background file for one or more GUI Overlays");
				e.printStackTrace();
			}
		}
	}
}