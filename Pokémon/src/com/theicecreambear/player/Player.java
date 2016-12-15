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

import javax.imageio.ImageIO;

import com.theicecreambear.gameobject.GameObject;
import com.theicecreambear.handlers.InputHandler;
import com.theicecreambear.item.InventoryItemGroup;
import com.theicecreambear.item.Item;
import com.theicecreambear.refrence.Refrence;

public class Player extends GameObject {
	
	public BufferedImage[] playerWalkingSprites;
	
	// TODO FIX inventory
	private ArrayList<Item> bag;
	// TODO Possible Inventory
	private HashMap<Item, Integer> bagWithCount;
	// TODO 12/13/16
	private InventoryItemGroup[] playerInv;
	
	private BufferedImage currentSprite;
	
	private OverworldPosition owp;
	// TODO change back to private
	public WorldPosition wp;
	private InputHandler handler;
	
	public boolean male;
	
	public boolean isRunning;
	public boolean isStill;
	public Direction facingDirection;
	public boolean rightFootOut;
	
	private int moveTicks = 0;
	private int moveCycles = 0;
	private Direction curentCycleDirection;
	
	private final int upKey;
	private final int downKey;
	private final int rightKey;
	private final int leftKey;
	
	public Player(OverworldPosition owp, WorldPosition wp, ArrayList<Item> bag, boolean male, Component c) {
		this.owp = owp;
		this.wp = wp;
		this.bag = bag;
		this.bagWithCount = new HashMap<Item, Integer>();
		this.male = male;
		this.handler = new InputHandler(c);
		this.initPlayerWalkingSprites();
		this.currentSprite = playerWalkingSprites[1];
		this.facingDirection = Direction.DOWN;
		
		this.upKey = Refrence.PlayerRef.PLAYER_UP;
		this.downKey = Refrence.PlayerRef.PLAYER_DOWN;
		this.rightKey = Refrence.PlayerRef.PLAYER_RIGHT;
		this.leftKey = Refrence.PlayerRef.PLAYER_LEFT;
	}
	
	public Player(OverworldPosition owp, WorldPosition wp, Component c) {
		this(owp, wp, new ArrayList<Item>(), true, c);
	}
	
	public Player(boolean male, Component c) {
		this(new OverworldPosition(), new WorldPosition(), new ArrayList<Item>(), male, c);
	}
	
	@Override
	public void update(double deltaTime) {
		if (handler.isKeyDown(KeyEvent.VK_ESCAPE)) {
			// TODO prompt for save, then exit properly.
			System.exit(-1);
		}
		
		// if (handler.isKeyDown(keyCode)) {
		// do bag
		// }
		
		isRunning = isStill ? false : handler.isKeyDown(KeyEvent.VK_SHIFT) /* && this.bag.contains(Items.runningShoes) */ ? true : false;
		
		this.movePlayer();
						
		this.changeRenderSprite();
	}
	
	@Override
	public void draw(Graphics g, ImageObserver observer) {
		g.drawImage(this.currentSprite, wp.x, wp.y, observer);
	}
	
	private void initPlayerWalkingSprites() {
		playerWalkingSprites = new BufferedImage[12];
		if (male) {
			final String m = "male_";
			try {
				playerWalkingSprites[0] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "up.png"));
				playerWalkingSprites[1] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "down.png"));
				playerWalkingSprites[2] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "left.png"));
				playerWalkingSprites[3] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "right.png"));
				
				playerWalkingSprites[4] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + m + "up.png"));
				playerWalkingSprites[5] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + m + "down.png"));
				playerWalkingSprites[6] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + m + "left.png"));
				playerWalkingSprites[7] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + m + "right.png"));
				
				playerWalkingSprites[8] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + m + "up.png"));
				playerWalkingSprites[9] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + m + "down.png"));
				playerWalkingSprites[10] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + m + "left.png"));
				playerWalkingSprites[11] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + m + "right.png"));
				
			} catch (IOException e) {
				System.err.println("One or more male player sprites don't exist");
				e.printStackTrace();
			}
		} else {
			// TODO make the female sprites
			final String f = "female_";
			try {
				playerWalkingSprites[0] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "up.png"));
				playerWalkingSprites[1] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "down.png"));
				playerWalkingSprites[2] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "left.png"));
				playerWalkingSprites[3] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "right.png"));
				
				playerWalkingSprites[4] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + f + "up.png"));
				playerWalkingSprites[5] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + f + "down.png"));
				playerWalkingSprites[6] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + f + "left.png"));
				playerWalkingSprites[7] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + f + "right.png"));
				
				playerWalkingSprites[8] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + f + "up.png"));
				playerWalkingSprites[9] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + f + "down.png"));
				playerWalkingSprites[10] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + f + "left.png"));
				playerWalkingSprites[11] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + f + "right.png"));
				
			} catch (IOException e) {
				System.err.println("One or more male player sprites don't exist");
				e.printStackTrace();
			}
		}
		
		System.gc();
	}
	
	private void changeRenderSprite() {
		if (wp.x % 22 == 0 && wp.y % 22 == 0) {
			switch (facingDirection) {
				case DOWN:
					currentSprite = playerWalkingSprites[1];
					break;
				case LEFT:
					currentSprite = playerWalkingSprites[2];
					break;
				case RIGHT:
					currentSprite = playerWalkingSprites[3];
					break;
				case UP:
					currentSprite = playerWalkingSprites[0];
					break;
				default:
					break;
			}
		} else {
			if (!rightFootOut) {
				switch (facingDirection) {
					case UP:
						currentSprite = playerWalkingSprites[4];
						break;
						
					case DOWN:
						currentSprite = playerWalkingSprites[5];
						break;
						
					case LEFT:
						currentSprite = playerWalkingSprites[6];
						break;
						
					case RIGHT:
						currentSprite = playerWalkingSprites[7];
						break;
						
					default:
						currentSprite = playerWalkingSprites[1];
						break;
				}
			} else {
				switch (facingDirection) {
					case UP:
						currentSprite = playerWalkingSprites[8];
						break;
					case DOWN:
						currentSprite = playerWalkingSprites[9];
						break;
					case LEFT:
						currentSprite = playerWalkingSprites[10];
						break;
					case RIGHT:
						currentSprite = playerWalkingSprites[11];
						break;
						
					default:
						currentSprite = playerWalkingSprites[1];
						break;
				}
			}
		}
	}
	
	/**
	 * This method saved 256 lines of code over the old system and is way more efficient.
	 */
	private void movePlayer() {
		/* TODO list of needed aditions to make perfect
		 * feet
		 * running
		 */
		this.moveTicks++;
		switch (this.moveCycles) {
			case 0: // new Cycle
				this.curentCycleDirection = this.getNewMoveDirection();
				this.moveCycles = 2;
				break;
			case 1: // end of cycle, does final move
				if (this.moveTicks == 60) {
					this.changePosition(curentCycleDirection);
					this.moveCycles = 0;
					this.moveTicks = 0;
				}
				break;
			case 2: // Beginning of cycle, does middle move
				if (this.moveTicks == 15) {
					this.facingDirection = this.curentCycleDirection;
				}
				if (this.moveTicks == 30) {
					this.rightFootOut = !this.rightFootOut;
					this.changePosition(curentCycleDirection);
					this.moveCycles = 1;
				}
				break;
			case 3: // in place character move move and look
				break;
			default:
				System.err.println("Something went wrong, currnet move cycles is " + this.moveCycles);
				try {
					throw new Exception("Move Cycles above 3 or below 0. mC = " + this.moveCycles);
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.moveCycles = 0;
				
		}
	}
	
	/**
	 * Only to be used when the ticks have elapsed, does not account for timing so
	 * the caller has to handle timing and call this a the right time.
	 * @param dir - direction of movement of the player
	 */
	private void changePosition(Direction dir) {
		switch (dir) {
			case DOWN: 
				wp.y += 11;
				break;
			case LEFT:
				wp.x -= 11;
				break;
			case RIGHT:
				wp.x += 11;
				break;
			case STILL:
				// TODO figure out what to do in this case
				break;
			case UP:
				wp.y -= 11;
				break;
			default:
				break;
		}
	}
	
	private Direction getNewMoveDirection() {
		if (handler.isKeyDown(upKey)) {
			return Direction.UP;			
		} else if (handler.isKeyDown(downKey)) {
			return Direction.DOWN;
		} else if (handler.isKeyDown(leftKey)) {
			return Direction.LEFT;
		} else if (handler.isKeyDown(rightKey)) {
			return Direction.RIGHT;
		} else {
			return Direction.STILL;
		}
	}
	
	private enum Direction {
		UP,
		DOWN,
		LEFT,
		RIGHT,
		STILL;
	}
}