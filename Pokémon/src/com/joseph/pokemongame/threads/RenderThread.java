package com.joseph.pokemongame.threads;

import com.joseph.pokemongame.engine.GameEngine;
import com.joseph.pokemongame.gameobject.locks.RenderLockObject;

public class RenderThread extends Thread {
	private final RenderLockObject rlo;
	private final GameEngine gEngine;
	public RenderThread(String name, RenderLockObject rlo, GameEngine ge) {
		super(name);
		this.rlo = rlo;
		this.gEngine = ge;
	}
	
	@Override
	public void run() {
		synchronized (rlo) {
			while (GameEngine.isRunning()) {
				try {
					rlo.wait();
					if (!rlo.wasNotified()) {
						continue;
					} else {
						gEngine.render(gEngine.getG(), gEngine.getFrame());
						rlo.setWasNotified(false);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(-1);
				}
			}
		}
	}
}