package com.joseph.pokemongame.player;

import com.joseph.pokemongame.screen.Screen;

/**
 * The box location of an object based on its position.
 * @author Joseph
 */
public class TilePosition {

	public int x;
	public int y;
	public final int MAX_X;
	public final int MAX_Y;
	
	public TilePosition(int x, int y) {
		this.x = x;
		this.y = y;
		this.MAX_X = Screen.WIDTH / 11; // TODO add correct values
		this.MAX_Y = Screen.HEIGHT / 11;
	}
	
	public TilePosition() {
		this(0, 0);
	}
	
	public int getPixelX() {
		return this.x * 11;
	}
	
	public int getPixelY() {
		return this.y * 11;
	}
	
	public String toString() {
		return "x: " + x + " y: " + y + " PixelX: " + this.getPixelX() + " PixelY: " + this.getPixelY();
	}
}