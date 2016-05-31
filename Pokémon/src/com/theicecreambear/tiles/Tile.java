package com.theicecreambear.tiles;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.theicecreambear.player.WorldPosition;
import com.theicecreambear.refrence.Refrence;

public class Tile {

	// In terms of pixels
	public static int width;
	public static int height;

	BufferedImage tile;
	public int id;
	WorldPosition pw;

	public Tile(int id) {
		tile = getTile(id);
	}

	public BufferedImage getTile(int id) {
		try {
			return ImageIO.read(new File(Refrence.tiles + id));
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "There was an error reading the tiles");
			JOptionPane.showMessageDialog(null, "Error at class Tile method getTile()");
		}
		return null;
	}

	public Tile() {
		this(0);
	}
}
