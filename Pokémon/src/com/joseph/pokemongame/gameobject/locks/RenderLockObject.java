package com.joseph.pokemongame.gameobject.locks;

public class RenderLockObject {
	private boolean wasNotified;
	
	public RenderLockObject() {
		this.wasNotified = false;
	}
	
	public boolean wasNotified() {
		return this.wasNotified;
	}
	
	public void setWasNotified(boolean wasNotified) {
		this.wasNotified = wasNotified;
	}
}