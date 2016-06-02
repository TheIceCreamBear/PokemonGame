package com.theicecreambear.player;

import com.theicecreambear.screen.Screen;

public class WorldPosition extends Position {

	public int x;
	public int y;
	public final int MAX_X;
	public final int MAX_Y;
		
	public WorldPosition(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.MAX_X = Screen.width; // TODO add correct values
		this.MAX_Y = Screen.height;
	}
	
	public WorldPosition() {
		this(0,0);
	}
	
	public String toString() {
		return "x: " + x + " %: " + x % 22 +" y: " + y + " %: " + y % 22;
	}
}