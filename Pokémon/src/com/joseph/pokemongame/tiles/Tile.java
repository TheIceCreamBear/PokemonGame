package com.joseph.pokemongame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import com.joseph.pokemongame.interfaces.Drawable;
import com.joseph.pokemongame.player.OverworldPosition;

public class Tile implements Drawable {

	// In terms of pixels
	public static final int TILE_WIDTH = 22;
	public static final int TILE_HEIGHT = 22;

	public int id;
	private BufferedImage tile;
	private OverworldPosition owp;

	@Override
	public void draw(Graphics g, ImageObserver observer) {
		g.drawImage(this.tile, owp.getPixelX(), owp.getPixelY(), observer);
	}
}