package com.theicecreambear.player;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import com.theicecreambear.engine.GameEngine;
import com.theicecreambear.gameobject.GameObject;
import com.theicecreambear.handlers.InputHandler;
import com.theicecreambear.interfaces.Drawable;
import com.theicecreambear.interfaces.Updateable;
import com.theicecreambear.item.Item;
import com.theicecreambear.item.Items;
import com.theicecreambear.refrence.Refrence;

public class Player extends GameObject implements Drawable, Updateable {

	public BufferedImage[] playerWalkingSprites;
	
	// TODO FIX inventory
	private ArrayList<Item> bag;
	// TODO Possible Inventory
	private HashMap<Item, Integer> bagWithCount;

	private BufferedImage currentSprite;

	private OverworldPosition owp;
	// TODO change back to private
	public WorldPosition wp;
	private InputHandler handler;
	private double scale = .04;

	public boolean male;
	public boolean isRunning;
	public boolean isInMovingPlus;
	public boolean isInMovingMinus;
	public boolean isStill;
	public String direction = "";
	public boolean footOut;
	public boolean rightFootOut;
	
	private int localTicks = 0;
	private int lastTick = 0;

	public Player(OverworldPosition owp, WorldPosition wp, ArrayList<Item> bag, boolean male, Component c) {
		this.owp = owp;
		this.wp = wp;
		this.bag = bag;
		this.bagWithCount = new HashMap<Item, Integer>();
		this.male = male;
		this.handler = new InputHandler(c);
		this.initPlayerWalkingSprites();
		this.currentSprite = playerWalkingSprites[1];
	}

	public Player(OverworldPosition owp, WorldPosition wp, Component c) {
		this(owp, wp, new ArrayList<Item>(), true, c);
	}

	public Player(boolean male, Component c) {
		this(new OverworldPosition(), new WorldPosition(), new ArrayList<Item>(), male, c);
	}


	@Override
	public void update(double deltaTime) {
		this.lastTick = this.localTicks;
		this.localTicks++;
		
		
		if (handler.isKeyDown(KeyEvent.VK_ESCAPE) && GameEngine.instance.isConsoleShowing()) {
			GameEngine.instance.hideConsole();
		}
		
		if (handler.isKeyDown(KeyEvent.VK_ESCAPE) && !GameEngine.instance.isConsoleShowing()) {
			System.exit(-1);
		}
		
		if (handler.isKeyDown(KeyEvent.VK_DIVIDE)) {
			GameEngine.instance.showConsole();
			// TODO Console showing stuffs
		}
		
		if (GameEngine.instance.isConsoleShowing()) {
			return;
		}
		
		if (!handler.isMoveKeyDown()) {
			this.localTicks = 0;
		}

//		if (!(handler.isKeyDown(KeyEvent.VK_RIGHT) || handler.isKeyDown(KeyEvent.VK_D)
//				|| handler.isKeyDown(KeyEvent.VK_LEFT) || handler.isKeyDown(KeyEvent.VK_A)
//				|| handler.isKeyDown(KeyEvent.VK_UP) || handler.isKeyDown(KeyEvent.VK_W)
//				|| handler.isKeyDown(KeyEvent.VK_DOWN) || handler.isKeyDown(KeyEvent.VK_S))) {
//			isStill = true;
//		} else {
//			isStill = false;
//		}
		
		isRunning = isStill ? false : handler.isKeyDown(KeyEvent.VK_SHIFT) /*&& this.bag.contains(Items.runningShoes)*/ ? true : false;

		switch (direction) {
		case "up":
		case "down":
		case "left":
		case "right":
		}
		
		// TODO isOnlyKeyDown
		if (((handler.isKeyDown(KeyEvent.VK_RIGHT) || handler.isKeyDown(KeyEvent.VK_D)) && !isOtherMoveKeyDown(KeyEvent.VK_D))/* || isInMoving*/) {
			isInMovingPlus = true;
			if (isRunning) {
				// TODO test how % 60 works for this
				if (this.localTicks == 15) {
					wp.x += 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 30) {
					wp.x += 11;
					isStill = true;
				}
				
				if (this.localTicks == 45) {
					wp.x += 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 60) {
					wp.x += 11;
					isStill = true;
					this.localTicks = 0;
				}
			} else {
				if (this.localTicks == 30) {
					wp.x += 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 60) {
					wp.x += 11;
					isStill = true;
					this.localTicks = 0;
				}
			}

			direction = "right";
		}

		if (handler.isKeyDown(KeyEvent.VK_LEFT) || handler.isKeyDown(KeyEvent.VK_A) && !isOtherMoveKeyDown(KeyEvent.VK_A)) {
			isInMovingMinus = true;
			if (isRunning) {
				if (this.localTicks == 15) {
					wp.x -= 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 30) {
					wp.x -= 11;
					isStill = true;
				}
				
				if (this.localTicks == 45) {
					wp.x -= 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 60) {
					wp.x -= 11;
					isStill = true;
					this.localTicks = 0;
				}
			} else {
				if (this.localTicks == 30) {
					wp.x -= 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 60) {
					wp.x -= 11;
					isStill = true;
					this.localTicks = 0;
				}
			}
			
			direction = "left";
		}
		// TODO - Possible Soultion to this madness would to 
		// check every 30 ticks and then move the player by 11 pixles
		if (handler.isKeyDown(KeyEvent.VK_UP) || handler.isKeyDown(KeyEvent.VK_W) && !isOtherMoveKeyDown(KeyEvent.VK_W)) {
			isInMovingMinus = true;
			if (isRunning) {
				if (this.localTicks == 15) {
					wp.y -= 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 30) {
					wp.y -= 11;
					isStill = true;
				}
				
				if (this.localTicks == 45) {
					wp.y -= 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 60) {
					wp.y -= 11;
					isStill = true;
					this.localTicks = 0;
				}
			} else {
				if (this.localTicks == 30) {
					wp.y -= 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 60) {
					wp.y -= 11;
					isStill = true;
					this.localTicks = 0;
				}
			}
			
			direction = "up";
		}

		if (handler.isKeyDown(KeyEvent.VK_DOWN) || handler.isKeyDown(KeyEvent.VK_S) && !isOtherMoveKeyDown(KeyEvent.VK_S)) {
			isInMovingPlus = true;
			if (isRunning) {
				if (this.localTicks == 15) {
					wp.y += 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 30) {
					wp.y += 11;
					isStill = true;
				}
				
				if (this.localTicks == 45) {
					wp.y += 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 60) {
					wp.y += 11;
					isStill = true;
					this.localTicks = 0;
				}
			} else {
				if (this.localTicks == 30) {
					wp.y += 11;
					rightFootOut = !rightFootOut;
					isStill = false;
				}
				
				if (this.localTicks == 60) {
					wp.y += 11;
					isStill = true;
					this.localTicks = 0;
				}
			}
			
			direction = "down";
		}
		
		// KEPP IN TILE CODE
		if (isInMovingPlus) {
			if (wp.x % 22 == 11) {
				if (this.localTicks == 60) {
					wp.x += 11;
					isStill = true;
					this.localTicks = 0;
				}
			}
			if (wp.y % 22 == 11) {
				if (this.localTicks == 60) {
					wp.y += 11;
					isStill = true;
					this.localTicks = 0;
				}
			}
		}
		if (isInMovingMinus) {
			if (wp.x % 22 == 11) {
				if (this.localTicks == 60) {
					wp.x -= 11;
					isStill = true;
					this.localTicks = 0;
				}
			}
			if (wp.y % 22 == 11) {
				if (this.localTicks == 60) {
					wp.y -= 11;
					isStill = true;
					this.localTicks = 0;
				}
			}
		}
		// END KEEP IN TILE CODE
		
		// PLAYER SPRITES
		try {
			if (isStill) {
				switch (direction) {
				case "up":
					currentSprite = playerWalkingSprites[0];
					break;

				case "down":
					currentSprite = playerWalkingSprites[1];
					break;

				case "left":
					currentSprite = playerWalkingSprites[2];
					break;

				case "right":
					currentSprite = playerWalkingSprites[3];
					break;

				default:
					currentSprite = playerWalkingSprites[1];
					break;
				}
			} else {
				if (!rightFootOut) {
					switch (direction) {
					case "up":
						currentSprite = playerWalkingSprites[4];
						break;

					case "down":
						currentSprite = playerWalkingSprites[5];
						break;

					case "left":
						currentSprite = playerWalkingSprites[6];
						break;

					case "right":
						currentSprite = playerWalkingSprites[7];
						break;

					default:
						currentSprite = playerWalkingSprites[1];
						break;
					}
				} else {
					switch (direction) {
					case "up":
						currentSprite = playerWalkingSprites[8];
						break;
					case "down":
						currentSprite = playerWalkingSprites[9];
						break;
					case "left":
						currentSprite = playerWalkingSprites[10];
						break;
					case "right":
						currentSprite = playerWalkingSprites[11];
						break;

					default:
						currentSprite = playerWalkingSprites[1];
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void draw(Graphics g, ImageObserver observer) {
		g.drawImage(this.currentSprite, wp.x, wp.y, observer);
	}
	
	private boolean isOtherMoveKeyDown(int currentKey) {
		if (currentKey == KeyEvent.VK_UP || currentKey == KeyEvent.VK_W) {
			if (handler.isKeyDown(KeyEvent.VK_DOWN) || handler.isKeyDown(KeyEvent.VK_S)) {
				return true;
			}
			if (handler.isKeyDown(KeyEvent.VK_LEFT) || handler.isKeyDown(KeyEvent.VK_A)) {
				return true;
			}
			if (handler.isKeyDown(KeyEvent.VK_RIGHT) || handler.isKeyDown(KeyEvent.VK_D)) {
				return true;
			}
			
		}
		if (currentKey == KeyEvent.VK_DOWN || currentKey == KeyEvent.VK_S) {
			if (handler.isKeyDown(KeyEvent.VK_UP) || handler.isKeyDown(KeyEvent.VK_W)) {
				return true;
			}
			if (handler.isKeyDown(KeyEvent.VK_LEFT) || handler.isKeyDown(KeyEvent.VK_A)) {
				return true;
			}
			if (handler.isKeyDown(KeyEvent.VK_RIGHT) || handler.isKeyDown(KeyEvent.VK_D)) {
				return true;
			}
			
		}
		if (currentKey == KeyEvent.VK_LEFT || currentKey == KeyEvent.VK_A) {
			if (handler.isKeyDown(KeyEvent.VK_DOWN) || handler.isKeyDown(KeyEvent.VK_S)) {
				return true;
			}
			if (handler.isKeyDown(KeyEvent.VK_UP) || handler.isKeyDown(KeyEvent.VK_W)) {
				return true;
			}
			if (handler.isKeyDown(KeyEvent.VK_RIGHT) || handler.isKeyDown(KeyEvent.VK_D)) {
				return true;
			}
			
		}
		if (currentKey == KeyEvent.VK_RIGHT || currentKey == KeyEvent.VK_D) {
			if (handler.isKeyDown(KeyEvent.VK_DOWN) || handler.isKeyDown(KeyEvent.VK_S)) {
				return true;
			}
			if (handler.isKeyDown(KeyEvent.VK_LEFT) || handler.isKeyDown(KeyEvent.VK_A)) {
				return true;
			}
			if (handler.isKeyDown(KeyEvent.VK_UP) || handler.isKeyDown(KeyEvent.VK_W)) {
				return true;
			}
			
		}
		
		return false;
	}

	private void initPlayerWalkingSprites() {
		playerWalkingSprites = new BufferedImage[12];
		try {
			playerWalkingSprites[0] = ImageIO.read(new File(Refrence.PLAYER_STILL + "up.png"));
			playerWalkingSprites[1] = ImageIO.read(new File(Refrence.PLAYER_STILL + "down.png"));
			playerWalkingSprites[2] = ImageIO.read(new File(Refrence.PLAYER_STILL + "left.png"));
			playerWalkingSprites[3] = ImageIO.read(new File(Refrence.PLAYER_STILL + "right.png"));

			playerWalkingSprites[4] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + "up.png"));
			playerWalkingSprites[5] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + "down.png"));
			playerWalkingSprites[6] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + "left.png"));
			playerWalkingSprites[7] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + "right.png"));

			playerWalkingSprites[8] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + "up.png"));
			playerWalkingSprites[9] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + "down.png"));
			playerWalkingSprites[10] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + "left.png"));
			playerWalkingSprites[11] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + "right.png"));

		} catch (IOException e) {
			System.err.println("One or more player sprites don't exist");
			e.printStackTrace();
		}
		
		System.gc();
	}
	
	private boolean isOnlyKeyDown(int keyNum) {
		
		return false;
	}

	/**
	 * @deprecated - Currently not in use, only here for nostilgia
	 * @author Joseph
	 *
	 */
	public enum State {
		RIGHTRUN("rightR", true, 1), 
		LEFTRUN("leftR", true, 1), 
		DOWNRUN("downR", true, 1), 
		UPRUN("upR", true, 1), 
		RIGHTWALK("rightW", false, 1), 
		LEFTWALK("leftW", false, 1), 
		DOWNWALK("downW", false, 1), 
		UPWALK("upW", false, 1), 
		NOTMOVING("none", false, 1);

		private static final Map<String, State> NAME_STATE_MAP = new HashMap<String, State>();
		private String direction;
		private int foot; // 0 left, 1 no, 2 right

		State(String direction, boolean running, int foot) {
			this.direction = direction;
			this.foot = foot;
		}

		public String getDirection() {
			return this.direction;
		}

		public int getfoot() {
			return foot;
		}

		// 0 left, 1 no, 2 right
		public void nextFoot() {
			if (foot >= 0 && foot != 2) {
				foot++;
			} else if (foot <= 2 && foot != 0) {
				foot--;
			}

		}

		public static State getState(String direction) {
			return (State) NAME_STATE_MAP.get(direction);
		}

		static {
			for (State state : values()) {
				if (NAME_STATE_MAP.containsKey(state.getDirection())) {
					throw new Error("Clash in State Name pools! Cannot insert " + state);
				}
				NAME_STATE_MAP.put(state.getDirection(), state);
			}
		}
	}
}