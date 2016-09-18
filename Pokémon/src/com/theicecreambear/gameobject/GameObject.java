package com.theicecreambear.gameobject;

import com.theicecreambear.interfaces.Drawable;
import com.theicecreambear.interfaces.Updateable;
import com.theicecreambear.player.OverworldPosition;
import com.theicecreambear.player.WorldPosition;
import com.theicecreambear.world.Coordinate;

public abstract class GameObject extends GenericObject implements Drawable, Updateable {
	
	// Explicit
	public GameObject(OverworldPosition owp, WorldPosition wp, int x, int y) {
		this.owp = owp;
		this.wp = wp;
		cords = new Coordinate(x, y);
	}
	
	// More explicit default
	public GameObject(int x, int y) {
		this(new OverworldPosition(), new WorldPosition(), 0,0);
	}
	
	// Default
	public GameObject() {
		this(0,0);
	}
	
	
	
}