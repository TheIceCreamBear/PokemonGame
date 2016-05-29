package com.theicecreambear.player;

public class Chunk {
	
	public int x;
	public int y;
	
	private OverworldPosition chunkPos;
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		
		chunkPos = new OverworldPosition(x, y);
	}
}