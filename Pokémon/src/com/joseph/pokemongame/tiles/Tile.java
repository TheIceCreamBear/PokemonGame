package com.joseph.pokemongame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import com.joseph.pokemongame.interfaces.IDrawable;
import com.joseph.pokemongame.player.TilePosition;

public class Tile implements IDrawable {

	// In terms of pixels
	public static final int TILE_WIDTH = 22;
	public static final int TILE_HEIGHT = 22;

	public int id;
	private BufferedImage tile;
	private TilePosition tilePos;

	@Override
	public void draw(Graphics g, ImageObserver observer) {
		g.drawImage(this.tile, tilePos.getPixelX(), tilePos.getPixelY(), observer);
	}
}