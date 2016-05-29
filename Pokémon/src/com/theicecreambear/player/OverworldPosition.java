package com.theicecreambear.player;

public class OverworldPosition extends Position {

	public int x;
	public int y;
	public final int MAX_X;
	public final int MAX_Y;
	
	public OverworldPosition(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.MAX_X = 10; // TODO add correct values
		this.MAX_Y = 10;
	}
}