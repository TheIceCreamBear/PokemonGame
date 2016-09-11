package com.theicecreambear.player;

public class Position {
	
	public int x;
	public int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "x: " + x + " %: " + x % 22 +" y: " + y + " %: " + y % 22;
	}
}