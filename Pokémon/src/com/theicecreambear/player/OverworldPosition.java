package com.theicecreambear.player;

import com.theicecreambear.screen.Screen;

public class OverworldPosition extends Position {

	public int x;
	public int y;
	public final int MAX_X;
	public final int MAX_Y;
	
	public OverworldPosition(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.MAX_X = Screen.width / 11; // TODO add correct values
		this.MAX_Y = Screen.height / 11;
	}
	
	public OverworldPosition() {
		this(0,0);
	}
}