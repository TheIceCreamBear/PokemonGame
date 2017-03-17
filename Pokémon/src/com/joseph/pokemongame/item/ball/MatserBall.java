package com.joseph.pokemongame.item.ball;

import com.joseph.pokemongame.pokémon.GenericPokémon;

public class MatserBall extends GenericBall {
	@Override
	public GenericPokémon getStoredPokémon() {
		return this.stored;
	}
}