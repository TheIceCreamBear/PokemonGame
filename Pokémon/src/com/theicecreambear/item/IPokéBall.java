package com.theicecreambear.item;

import com.theicecreambear.pok�mon.GenericPok�mon;

public interface IPok�Ball {
	/**
	 * The cahnce out of 100 the ball has of captureing the Pok�mon
	 */
	public int captureChance = 0;
	
	/**
	 * The current Pok�mon being stroed in the Pok�Ball 
	 */
	public GenericPok�mon storedPok�mon = null;
	
	/**
	 * The method to get the Pok�mon in the ball
	 * @return - The stored Pok�mon
	 */
	public default GenericPok�mon getStoredPok�mon() {
		return storedPok�mon;
	}
}