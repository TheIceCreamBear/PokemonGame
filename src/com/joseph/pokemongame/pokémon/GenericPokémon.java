package com.joseph.pokemongame.pok�mon;

import java.awt.image.BufferedImage;

public class GenericPok�mon {
	private int id;
	private String name;
	public BufferedImage sprite;
	
	public GenericPok�mon() {
	}
	
	public int getPok�monID() {
		return this.id;
	}
	
	public String getPok�monName() {
		return this.name;
	}
}
