package com.joseph.pokemongame.gameobject;

public class Trigger extends UpdateableObject {
	boolean condition = false;
	
	public Trigger() {
		// TODO
	}
	
	public void start() {
		while(!condition) {
			update();
		}
		
		event();
	}
	
	public void update() {
		
	}
	
	public void event() {
		
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}
}
