package com.joseph.pokemongame.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.joseph.pokemongame.engine.GameEngine;
import com.joseph.pokemongame.gui.ConsoleGuiOverlay;
import com.joseph.pokemongame.reference.Reference;

/**
 * GKELAH, or GlobalKeyEventHandlerAndListener, is a key event handler that listens for all
 * key events and does a specific action based on the state of the engine and 
 * @author Joseph
 */
public class GKELAH implements KeyListener {
	public GKELAH() {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO have a flag somewhere that dictates if the console is open. if it is this method must notify the console instance. if not then key pressed should have 
		if (GameEngine.console) {
			ConsoleGuiOverlay.getInstance().notifyInstanceOfKeyTyped(e);
		}
		System.err.println(e.getKeyChar());
		System.err.println(e.getID());
		System.err.println(e.getKeyCode());
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (GameEngine.console) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				ConsoleGuiOverlay.getInstance().notifyEnterPress();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SLASH) {
			GameEngine.getInstance().addIGuiOverlay(new ConsoleGuiOverlay(Reference.GuiBackgroundRef._32X6GENERIC_BACK));
			return;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
	}
}