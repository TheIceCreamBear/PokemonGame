package com.theicecreambear.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.theicecreambear.interfaces.Drawable;
import com.theicecreambear.player.WorldPosition;
import com.theicecreambear.refrence.Refrence;

public class Tile implements Drawable {

	// In terms of pixels
	public static final int TILE_WIDTH = 22;
	public static final int TILE_HEIGHT = 22;

	public int id;
	private BufferedImage tile;
	private WorldPosition wp;

	public Tile(int id, WorldPosition pos) {
		this.tile = getTile(id);
		this.wp = (WorldPosition) pos.clone();
		
	}
	
	public Tile(int id, int x, int y) {
		this(id, new WorldPosition(x, y));
	}

	public Tile() {
		this(0, 0, 0);
	}
	
	public BufferedImage getTile(int id) {
		try {
			return ImageIO.read(new File(Refrence.TILES + id + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "There was an error reading the tiles");
			JOptionPane.showMessageDialog(null, "Error at class Tile method getTile()");
		}
		return null;
	}

	@Override
	public void draw(Graphics g, ImageObserver observer) {
		g.drawImage(this.tile, wp.x, wp.y, observer);
	}
	
	public boolean equals(Tile tile1) {
		if (this.tile.equals(tile1.tile) && this.wp.equals(tile1.wp)) {
			return true;
		}
		return false;
	}
}