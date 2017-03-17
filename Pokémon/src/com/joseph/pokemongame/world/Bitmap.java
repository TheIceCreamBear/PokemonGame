package com.joseph.pokemongame.world;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import com.joseph.pokemongame.interfaces.IDrawable;
import com.joseph.pokemongame.screen.Screen;
import com.joseph.pokemongame.tiles.Tile;

public class Bitmap implements IDrawable {
	
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
				map[i][j] = new Tile();
			}
		}
	}
	
	// TODO find amount of tiles in overworld map
	public final int MAX_X = Screen.width / Tile.TILE_WIDTH;
	public final int MAX_Y = Screen.height / Tile.TILE_HEIGHT;

	@Override
	public void draw(Graphics g, ImageObserver observer) {
		// TODO Auto-generated method stub
	}
}
