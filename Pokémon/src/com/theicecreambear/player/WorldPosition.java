package com.theicecreambear.player;

import com.theicecreambear.screen.Screen;

public class WorldPosition extends Position implements Cloneable {

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
	
	public WorldPosition(OverworldPosition pos) {
		this(pos.x * 22, pos.y * 22);
	}
	
	public String toString() {
		return "WP=x: " + x + " %: " + x % 22 + " y: " + y + " %: " + y % 22;
	}
	
	public boolean equals(WorldPosition pos) {
		if (this.x == pos.x && this.y == pos.y) {
			return true;
		}
		return false;
	}
	
	public Object clone() {
		return new WorldPosition(x, y);
	}
	
}