package com.joseph.pokemongame.player;

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

import com.joseph.pokemongame.gameobject.GameObject;
import com.joseph.pokemongame.handlers.InputHandler;
import com.joseph.pokemongame.item.InventoryItemGroup;
import com.joseph.pokemongame.item.Item;
import com.joseph.pokemongame.refrence.Refrence;

public class Player extends GameObject {
	
	public BufferedImage[] playerWalkingSprites;
	public BufferedImage[] playerRunningSprites;
	
	// TODO FIX inventory
	private ArrayList<Item> bag;
	// TODO Possible Inventory
	private HashMap<Item, Integer> bagWithCount;
	// TODO 12/13/2016
	private InventoryItemGroup[] playerInv;
	// TODO, observe the minecrafts to see how it does it, 3/16/2017
	
	
	private BufferedImage currentSprite;
	
	private TilePosition tilePos;
	private InputHandler handler;
	
	private boolean male;
	
	private boolean isRunning;
	private EnumDirection facingDirection;
	private boolean rightFootOut;
	
	private int moveTicks;
	private int moveCycles;
	private EnumDirection curentCycleDirection;
	
	private final int upKey;
	private final int downKey;
	private final int rightKey;
	private final int leftKey;
	private final int runKey;
	
	public Player(TilePosition tilePos, ArrayList<Item> bag, boolean male, Component c) {
		this.tilePos = tilePos;
		this.bag = bag;
		this.bagWithCount = new HashMap<Item, Integer>();
		this.male = male;
		this.handler = new InputHandler(c);
		this.initPlayerWalkingSprites();
		this.currentSprite = playerWalkingSprites[1];
		this.facingDirection = EnumDirection.DOWN;
		this.moveTicks = 0;
		this.moveCycles = 0;
		
		this.upKey = Refrence.PlayerRef.PLAYER_UP;
		this.downKey = Refrence.PlayerRef.PLAYER_DOWN;
		this.rightKey = Refrence.PlayerRef.PLAYER_RIGHT;
		this.leftKey = Refrence.PlayerRef.PLAYER_LEFT;
		this.runKey = Refrence.PlayerRef.PLAYER_RUN;
	}
	
	public Player(TilePosition owp, Component c) {
		this(owp, new ArrayList<Item>(), true, c);
	}
	
	public Player(boolean male, Component c) {
		this(new TilePosition(), new ArrayList<Item>(), male, c);
	}
	
	@Override
	public void update(double deltaTime) {
		if (this.handler.isKeyDown(KeyEvent.VK_ESCAPE)) {
			// TODO prompt for save, then exit properly.
			// TODO THIS NEEDS TO BE REPLACED LATER IN DEVELOPMENT WITH A MENU
			System.exit(-1);
		}
		
		if (Refrence.DEBUG_MODE) {
			System.err.println(tilePos);
		}
		// if (handler.isKeyDown(keyCode)) {
		// do bag
		// }
		
		this.movePlayer();
		this.changeRenderSprite();
	}
	
	@Override
	public void draw(Graphics g, ImageObserver observer) {
		g.drawImage(this.currentSprite, tilePos.getPixelX(), tilePos.getPixelY(), observer);
	}
	
	private void initPlayerWalkingSprites() {
		this.playerWalkingSprites = new BufferedImage[12];
		this.playerRunningSprites = new BufferedImage[12];
		if (male) {
			final String m = "male_";
			try {
				this.playerWalkingSprites[0] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "up.png"));
				this.playerWalkingSprites[1] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "down.png"));
				this.playerWalkingSprites[2] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "left.png"));
				this.playerWalkingSprites[3] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "right.png"));
				
				this.playerWalkingSprites[4] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + m + "up.png"));
				this.playerWalkingSprites[5] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + m + "down.png"));
				this.playerWalkingSprites[6] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + m + "left.png"));
				this.playerWalkingSprites[7] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + m + "right.png"));
				
				this.playerWalkingSprites[8] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + m + "up.png"));
				this.playerWalkingSprites[9] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + m + "down.png"));
				this.playerWalkingSprites[10] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + m + "left.png"));
				this.playerWalkingSprites[11] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + m + "right.png"));
				
				System.gc();
				
				this.playerRunningSprites[0] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "up.png"));
				this.playerRunningSprites[1] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "down.png"));
				this.playerRunningSprites[2] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "left.png"));
				this.playerRunningSprites[3] = ImageIO.read(new File(Refrence.PLAYER_STILL + m + "right.png"));
				
				this.playerRunningSprites[4] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_LEFT + m + "up.png"));
				this.playerRunningSprites[5] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_LEFT + m + "down.png"));
				this.playerRunningSprites[6] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_LEFT + m + "left.png"));
				this.playerRunningSprites[7] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_LEFT + m + "right.png"));
				
				this.playerRunningSprites[8] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_RIGHT + m + "up.png"));
				this.playerRunningSprites[9] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_RIGHT + m + "down.png"));
				this.playerRunningSprites[10] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_RIGHT + m + "left.png"));
				this.playerRunningSprites[11] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_RIGHT + m + "right.png"));
				
			} catch (IOException e) {
				System.err.println("One or more male player sprites don't exist");
				e.printStackTrace();
			}
		} else {
			// TODO make the female sprites
			final String f = "female_";
			try {
				this.playerWalkingSprites[0] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "up.png"));
				this.playerWalkingSprites[1] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "down.png"));
				this.playerWalkingSprites[2] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "left.png"));
				this.playerWalkingSprites[3] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "right.png"));
				
				this.playerWalkingSprites[4] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + f + "up.png"));
				this.playerWalkingSprites[5] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + f + "down.png"));
				this.playerWalkingSprites[6] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + f + "left.png"));
				this.playerWalkingSprites[7] = ImageIO.read(new File(Refrence.PLAYER_WALKING_LEFT + f + "right.png"));
				
				this.playerWalkingSprites[8] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + f + "up.png"));
				this.playerWalkingSprites[9] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + f + "down.png"));
				this.playerWalkingSprites[10] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + f + "left.png"));
				this.playerWalkingSprites[11] = ImageIO.read(new File(Refrence.PLAYER_WALKING_RIGHT + f + "right.png"));
				
				System.gc();
				
				this.playerRunningSprites[0] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "up.png"));
				this.playerRunningSprites[1] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "down.png"));
				this.playerRunningSprites[2] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "left.png"));
				this.playerRunningSprites[3] = ImageIO.read(new File(Refrence.PLAYER_STILL + f + "right.png"));
				
				this.playerRunningSprites[4] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_LEFT + f + "up.png"));
				this.playerRunningSprites[5] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_LEFT + f + "down.png"));
				this.playerRunningSprites[6] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_LEFT + f + "left.png"));
				this.playerRunningSprites[7] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_LEFT + f + "right.png"));
				
				this.playerRunningSprites[8] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_RIGHT + f + "up.png"));
				this.playerRunningSprites[9] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_RIGHT + f + "down.png"));
				this.playerRunningSprites[10] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_RIGHT + f + "left.png"));
				this.playerRunningSprites[11] = ImageIO.read(new File(Refrence.PLAYER_RUNNING_RIGHT + f + "right.png"));
			} catch (IOException e) {
				System.err.println("One or more male player sprites don't exist");
				e.printStackTrace();
			}
		}
		
		System.gc();
	}
	
	private void changeRenderSprite() {
		if (tilePos.getPixelX() % 22 == 0 && tilePos.getPixelY() % 22 == 0) {
			switch (this.facingDirection) {
				case UP:
					this.currentSprite = this.playerWalkingSprites[0];
					break;
				case DOWN:
					this.currentSprite = this.playerWalkingSprites[1];
					break;
				case LEFT:
					this.currentSprite = this.playerWalkingSprites[2];
					break;
				case RIGHT:
					this.currentSprite = this.playerWalkingSprites[3];
					break;
				default:
					break;
			}
		} else {
			if (!this.rightFootOut) {
				switch (this.facingDirection) {
					case UP:
						this.currentSprite = this.playerWalkingSprites[4];
						break;
					case DOWN:
						this.currentSprite = this.playerWalkingSprites[5];
						break;
					case LEFT:
						this.currentSprite = this.playerWalkingSprites[6];
						break;
					case RIGHT:
						this.currentSprite = this.playerWalkingSprites[7];
						break;
					default:
						this.currentSprite = this.playerWalkingSprites[1];
						break;
				}
			} else {
				switch (this.facingDirection) {
					case UP:
						this.currentSprite = this.playerWalkingSprites[8];
						break;
					case DOWN:
						this.currentSprite = this.playerWalkingSprites[9];
						break;
					case LEFT:
						this.currentSprite = this.playerWalkingSprites[10];
						break;
					case RIGHT:
						this.currentSprite = this.playerWalkingSprites[11];
						break;
					default:
						this.currentSprite = this.playerWalkingSprites[1];
						break;
				}
			}
		}
	}
	
	/**
	 * This method saved 256 lines of code over the old system and is way more efficient.
	 */
	private void movePlayer() {
		/* TODO list of needed additions to make perfect
		 * ticks counter
		 * running
		 */
		this.moveTicks++;
		switch (this.moveCycles) {
			case 0: // new Cycle
				this.curentCycleDirection = this.getNewMoveDirection();
				if (this.curentCycleDirection == EnumDirection.STILL) {
					this.moveTicks = 0;
					break;
				}
				if (this.isRunning) {
					this.moveCycles = 4;
					break;
				}
				this.moveCycles = 2;
				break;
			case 1: // end of cycle, does final move
				if (this.moveTicks == 60) {
					this.changePosition();
					this.moveCycles = 0;
					this.moveTicks = 0;
				}
				break;
			case 2: // Beginning of cycle, does middle move
				if (this.moveTicks >= 1 && this.moveTicks <= 15) {
					if (this.curentCycleDirection != this.getNewMoveDirection()) {
						this.moveTicks = 0;
						this.moveCycles = 0;
					}
				}
				if (this.moveTicks == 15) {
					this.facingDirection = this.curentCycleDirection;
				}
				if (this.moveTicks == 30) {
					this.rightFootOut = !this.rightFootOut;
					this.changePosition();
					this.moveCycles = 1;
				}
				break;
			case 3: // running version of cycle 1
				if (this.moveTicks == 30) {
					this.changePosition();
					this.moveCycles = 0;
					this.moveTicks = 0;
				}
				break;
			case 4: // Running version of cycle 2
				if (this.moveTicks >= 1 && this.moveTicks <= 7) {
					if (this.curentCycleDirection != this.getNewMoveDirection()) {
						this.moveTicks = 0;
						this.moveCycles = 0;
					}
				}
				if (this.moveTicks == 7) {
					this.facingDirection = this.curentCycleDirection;
				}
				if (this.moveTicks == 15) {
					this.rightFootOut = !this.rightFootOut;
					this.changePosition();
					this.moveCycles = 3;
				}
				break;
			case 5: // in place character move move and look
				break;
			default:
				System.err.println("Something went wrong, currnet move cycles is " + this.moveCycles);
				try {
					throw new Exception("Move Cycles above 5 or below 0. mC = " + this.moveCycles);
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
	private void changePosition() {
		switch (this.curentCycleDirection) {
			case DOWN: 
				tilePos.y += 1;
				break;
			case LEFT:
				tilePos.x -= 1;
				break;
			case RIGHT:
				tilePos.x += 1;
				break;
			case STILL:
				// TODO no-op?
				break;
			case UP:
				tilePos.y -= 1;
				break;
			default:
				break;
		}
	}
	
	private EnumDirection getNewMoveDirection() {
		this.isRunning = false;
		if (handler.isKeyDown(this.runKey) /*&& this.bag.contains(Items.runningShoes)*/) {
			this.isRunning = true;
		}
		if (handler.isKeyDown(upKey)) {
			return EnumDirection.UP;			
		} else if (handler.isKeyDown(downKey)) {
			return EnumDirection.DOWN;
		} else if (handler.isKeyDown(leftKey)) {
			return EnumDirection.LEFT;
		} else if (handler.isKeyDown(rightKey)) {
			return EnumDirection.RIGHT;
		} else {
			return EnumDirection.STILL;
		}
	}
	
	private enum EnumDirection {
		UP,
		DOWN,
		LEFT,
		RIGHT,
		STILL;
	}
}