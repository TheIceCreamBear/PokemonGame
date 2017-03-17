package com.joseph.pokemongame.item.ball;

import com.joseph.pokemongame.pokémon.GenericPokémon;

public interface IPokéBall {
	/**
	 * The method to get the Pokémon in the ball
	 * @return - The stored Pokémon
	 */
	public GenericPokémon getStoredPokémon();
}