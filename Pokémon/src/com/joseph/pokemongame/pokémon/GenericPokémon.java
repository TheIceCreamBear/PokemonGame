package com.joseph.pokemongame.pokémon;

import java.awt.image.BufferedImage;

public class GenericPokémon {
	private int id;
	private String name;
	public BufferedImage sprite;
	
	public GenericPokémon() {
	}
	
	public int getPokémonID() {
		return this.id;
	}
	
	public String getPokémonName() {
		return this.name;
	}
}
