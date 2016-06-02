package com.theicecreambear.item;

import com.theicecreambear.pokémon.GenericPokémon;

public interface IPokéBall {
	/**
	 * The cahnce out of 100 the ball has of captureing the Pokémon
	 */
	public int captureChance = 0;
	
	/**
	 * The current Pokémon being stroed in the PokéBall 
	 */
	public GenericPokémon storedPokémon = null;
	
	/**
	 * The method to get the Pokémon in the ball
	 * @return - The stored Pokémon
	 */
	public default GenericPokémon getStoredPokémon() {
		return storedPokémon;
	}
}