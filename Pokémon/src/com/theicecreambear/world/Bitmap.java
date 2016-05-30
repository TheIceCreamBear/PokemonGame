package com.theicecreambear.world;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import com.theicecreambear.interfaces.Drawable;
import com.theicecreambear.screen.Screen;
import com.theicecreambear.tiles.Tile;

public class Bitmap implements Drawable {
	
	/*
	 * Worldmap = something
	 * bitmap = worldmap that player sees i.e. Smaller worldmap??
	 * bitmap extends Worldmap ??
	 * 
	 * TODO Think about how to do a worldmap
	 */
	Tile[][] map;
	
	public void initMap() {
		for(int i = 0; i < MAX_X; i++) {
			for(int j = 0; j < MAX_Y; j++) {
				map[i][j] = new Tile(i % 3);
			}
		}
	}
	// TODO find amount of tiles in overworld map
	public final int MAX_X = Screen.width / Tile.width;
	public final int MAX_Y = Screen.height / Tile.height;

	@Override
	public void draw(Graphics g, ImageObserver observer) {
		// TODO Auto-generated method stub
	}
}
