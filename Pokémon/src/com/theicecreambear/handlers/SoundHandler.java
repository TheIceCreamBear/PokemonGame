package com.theicecreambear.handlers;

import java.awt.event.KeyEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Made For My Personal Sound Board
 * @author Joseph Terribile
 *
 */
public class SoundHandler {
	
	public boolean running = true;

	public static final int VICTORY = 0;
	public static final int COMBO_BREAKER = 1;
	public static final int AIRHORN = 2;
	public static final int WILHELM = 3;
	public static final int SUPRISE_MOTHER_FUCKER = 4;
	
	public static final int CREW_REMIX = 100;
	public static final int CREW_REMIX_INS = 101;
	
	public static SoundHandler handler;
	
	private static String dirPrefix;
	
	private static AudioInputStream victoryIN;
	private static AudioInputStream cBreIN;
	private static AudioInputStream airIN;
	private static AudioInputStream wilIN;
	private static AudioInputStream supIN;
	private static AudioInputStream crewIN;
	private static AudioInputStream crewInsIN;

	
	private static Clip victoryClip;
	private static Clip cBreClip;
	private static Clip airClip;
	private static Clip wilClip;
	private static Clip supClip;
	private static Clip crewClip;
	private static Clip crewInsClip;
	
	public SoundHandler() {
		this("C:/Sounds/");
	}
	
	public SoundHandler(String dirPrefixIn) {
		dirPrefix = dirPrefixIn;
		handler = this;
	}
	
	public void playSound(int id) {
		try {
			switch (id) {
				case 0: {
					Thread.sleep(500);
					victoryIN = AudioSystem.getAudioInputStream(new File(dirPrefix + "victory.wav"));
					victoryClip = AudioSystem.getClip();
					victoryClip.open(victoryIN);
					victoryClip.start();
					break;
				}
				case 1: {
					Thread.sleep(500);
					cBreIN = AudioSystem.getAudioInputStream(new File(dirPrefix + "comboBreaker.wav"));
					cBreClip = AudioSystem.getClip();
					cBreClip.open(cBreIN);
					cBreClip.start();
					break;
				}
				case 2: {
					Thread.sleep(100);
					airIN = AudioSystem.getAudioInputStream(new File(dirPrefix + "airhorn.wav"));
					airClip = AudioSystem.getClip();
					airClip.open(airIN);
					airClip.start();
					break;
				}
				case 3: {
					wilIN = AudioSystem.getAudioInputStream(new File(dirPrefix + "wilhelm.wav"));
					wilClip = AudioSystem.getClip();
					wilClip.open(wilIN);
					wilClip.start();
					break;
				}
				case 4: {
					supIN = AudioSystem.getAudioInputStream(new File(dirPrefix + "surprisemotherfucker.wav"));
					supClip = AudioSystem.getClip();
					supClip.open(supIN);
					supClip.start();
					Thread.sleep(1000);
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("An Error occoured while playing the sound.");
			e.printStackTrace();
		}
	}
	
	public void playMusic(int id) {
		try {
			switch (id) {
				case 100: {
					crewIN = AudioSystem.getAudioInputStream(new File(dirPrefix + "TheCrewMix2.wav"));
					crewClip = AudioSystem.getClip();
					crewClip.open(crewIN);
					crewClip.start();
					break;
				}
				case 101: {
					crewInsIN = AudioSystem.getAudioInputStream(new File(dirPrefix + "TheCrewMix2INS.wav"));
					crewInsClip = AudioSystem.getClip();
					crewInsClip.open(crewInsIN);
					crewInsClip.start();
					break;
				}
			}
		} catch (Exception e) {
			System.err.println("An Error occoured while playing the sound.");
			e.printStackTrace();
		}
	}
	
	public void stopSound(int id) {
		switch (id) {
			case 1: {
				
			}
			case 100: {
				crewClip.stop();
				break;
			}
			case 101: {
				crewInsClip.stop();
				break;
			}
		}
	}
	
	public static SoundHandler getNewSoundHandler(String dirPrefix) {
		if (dirPrefix != null) {
			new SoundHandler(dirPrefix);
		} else {
			new SoundHandler();
		}
		return handler;
	}
	
	public void loopHandler() {
		while (running) {
			if (InputHandler.handler.isKeyDown(KeyEvent.VK_W)) {
				this.playSound(AIRHORN);
			}
		}
	}
}