package com.theicecreambear.player;

public class TilePosition {
	private WorldPosition worldPos;
	private int totalXTiles;
	private int totalYTiles;
	
	public TilePosition(WorldPosition pos) {
		this.worldPos = pos;
		this.totalXTiles = worldPos.MAX_X / 22;
		this.totalYTiles = worldPos.MAX_Y / 22;
	}
	
	public void keepInTile() {
		
	}
}