package com.joseph.pokemongame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import com.joseph.pokemongame.engine.GameEngine;
import com.joseph.pokemongame.handlers.InputHandler;
import com.joseph.pokemongame.reference.Reference;

public class DialogueBoxOverlay implements IGuiOverlay {
	private BufferedImage back;
	private FontRenderContext frc;
	private String message;
	private ArrayList<String> lines;
	private boolean shouldRemove;
	private boolean increaseOnUpdate;
	private int messageCharsToShow;
	private int x;
	private int y;
	
	public static DialogueBoxOverlay tempInstanceVar;
	
	public DialogueBoxOverlay() {
		this("", Reference.GuiBackgroundRef._32X6GENERIC_BACK);
	}
	
	public DialogueBoxOverlay(String message, BufferedImage back) {
		this.back = back;
		this.message = message;
		this.x = 24 * 22;
		this.y = 41 * 22;
		this.shouldRemove = false;
		this.increaseOnUpdate = true;
		Graphics2D g2d = (Graphics2D) GameEngine.getInstance().getG();
		this.frc = g2d.getFontRenderContext();
		this.lines = new ArrayList<String>();
		
		tempInstanceVar = this;
	}
	
	@Override
	public void drawGuiBackground(Graphics g, ImageObserver observer) {
		g.drawImage(back, x, y, observer); // TODO fix pos
	}
	
	@Override
	public void drawUpdateableGraphicsElements(Graphics g, ImageObserver observer) {
		if (this.back == null) {return;}
		if (messageCharsToShow != 0) {
			g.setColor(Color.DARK_GRAY);
			g.setFont(Reference.TEXT_BOX_FONT);
			g.drawString(message.substring(0, messageCharsToShow), x + 20, y + 20);
		}
	}
	
	@Override
	public void updateUpdateableGraphicsElements(double deltaTime) {
		// TODO deal with line wrapping and maybe make smaller
		if (this.back == null) {return;}
		
		if (this.increaseOnUpdate) {
			this.messageCharsToShow += 1;
			this.increaseOnUpdate = false;
		}
		if (!this.increaseOnUpdate) {
			this.increaseOnUpdate = true;
		}
		
		if (this.messageCharsToShow >= this.message.length()) {
			this.messageCharsToShow = this.message.length();
		}
		
		if (Reference.TEXT_BOX_FONT.getStringBounds(message, frc).getWidth() >= back.getWidth() - 100) {
			// TODO
		}
		
		if (InputHandler.handler.isKeyDown(KeyEvent.VK_R)) {
			this.setMessage("David is a weirdo who does things with Ubuntu and also helps me occasionaly with the varopus problems I have in programming.");
		}
		
		if (InputHandler.handler.isKeyDown(KeyEvent.VK_ESCAPE)) {
			this.shouldRemove = true;
		}
	}
	
	public void setMessage(String message) {
		this.message = message;
		this.messageCharsToShow = 0;
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