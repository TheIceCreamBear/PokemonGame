package com.joseph.pokemongame.reference;

import java.awt.event.KeyEvent;

import com.joseph.pokemongame.starter.Starter;

public class KeyBindings {
	public static int playerRun;
	
	public static void loadKeyBindings() {
		if (Starter.runKeyInField.getText() == null) {
			playerRun = KeyEvent.VK_SHIFT;
		}
	}
}