package com.joseph.pokemongame.item.ball;

import com.joseph.pokemongame.pok�mon.GenericPok�mon;

public class MatserBall extends GenericBall {
	@Override
	public GenericPok�mon getStoredPok�mon() {
		return this.stored;
	}
}