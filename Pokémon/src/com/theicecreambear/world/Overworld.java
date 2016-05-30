package com.theicecreambear.world;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Overworld {
	public int overworldX;
	public int overworldY;

	public BufferedImage overWorldMap;

	public void setWorldMap(BufferedImage img) {
		overWorldMap = img;
	}

	public void setWorldMap(File directory) {
		try {
			overWorldMap = ImageIO.read(directory);
		} catch (Exception e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "There was an error reading the world map Image
		}
	}
}