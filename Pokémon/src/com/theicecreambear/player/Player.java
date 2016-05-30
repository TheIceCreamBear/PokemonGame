package com.theicecreambear.player;

import java.util.ArrayList;

import com.theicecreambear.game_object.GameObject;
import com.theicecreambear.interfaces.Drawable;
import com.theicecreambear.interfaces.Updateable;
import com.theicecreambear.item.Item;

public class Player extends GameObject implements Drawable, Updateable {
	
	// TODO add inventory
	ArrayList<Item> inventory = new ArrayList<Item>();
	
	@Override
	public void update(long deltaTime) {
		// TODO Auto-generated method stub
	}
	@Override
	public void draw(long deltaTime) {
		// TODO Auto-generated method stub
	}
}