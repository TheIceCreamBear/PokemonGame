package com.theicecreambear.game_object;

import com.theicecreambear.interfaces.Drawable;
import com.theicecreambear.interfaces.Updateable;
import com.theicecreambear.player.OverworldPosition;
import com.theicecreambear.player.WorldPosition;
import com.theicecreambear.world.Coordinate;

public class GameObject implements Drawable, Updateable {
	
	public OverworldPosition overPos;
	public WorldPosition pos;
	
	public Coordinate cords;

	// Explicit
	public GameObject(int x, int y) {
		cords = new Coordinate(x, y);
	}
	
	// Default
	public GameObject() {
		this(0,0);
	}
	
	
	@Override
	public void update(long deltaTime) {

	}

	@Override
	public void draw(long deltaTime) {
		
	}
	
}
