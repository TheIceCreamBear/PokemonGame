package com.theicecreambear.gameobject;

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
}
