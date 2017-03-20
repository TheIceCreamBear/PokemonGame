package com.joseph.pokemongame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.joseph.pokemongame.handlers.InputHandler;
import com.joseph.pokemongame.reference.Reference;

public class TemporaryDialogueBoxOverlay implements IGuiOverlay {
	private BufferedImage back;
	private String message;
	private boolean shouldRemove;
	private int messageCharsToShow;
	private int x;
	private int y;
	
	public static TemporaryDialogueBoxOverlay tempInstanceVar;
	
	public TemporaryDialogueBoxOverlay() {
		this("");
	}
	
	public TemporaryDialogueBoxOverlay(String message) {
		try {
			this.back = ImageIO.read(new File(Reference.GUI_BACKGROUNDS + "textBoxBack.png"));
		} catch (IOException e) {
			System.err.println("Failed to aquire the background file for the GUI Overlay " + this);
			e.printStackTrace();
			this.back = null;
		}
		this.message = message;
		this.x = 24 * 22;
		this.y = 41 * 22;
		this.shouldRemove = false;
		
		tempInstanceVar = this;
	}
	
	@Override
	public void drawGuiBackground(Graphics g, ImageObserver observer) {
		// TODO Auto-generated method stub
		g.drawImage(back, x, y, observer); // TODO fix pos
	}
	
	@Override
	public void drawUpdateableGraphicsElements(Graphics g, ImageObserver observer) {
		// TODO Auto-generated method stub
		if (this.back == null) {return;}
		if (messageCharsToShow != 0) {
			g.setColor(Color.DARK_GRAY);
			g.setFont(Reference.TEXT_BOX_FONT);
			g.drawString(message.substring(0, messageCharsToShow), x + 20, y + 20);
		}
		
	}
	
	@Override
	public void updateUpdateableGraphicsElements(double deltaTime) {
		// TODO Auto-generated method stub
		if (this.back == null) {return;}
		this.messageCharsToShow += 2;
		if (this.messageCharsToShow >= this.message.length()) {
			this.messageCharsToShow = this.message.length();
		}
		
		if (InputHandler.handler.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.shouldRemove = true;
		}
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean removeGui() {
		return this.shouldRemove;
	}

	@Override
	public void setGuiToRemove() {
		this.shouldRemove = true;
	}
}