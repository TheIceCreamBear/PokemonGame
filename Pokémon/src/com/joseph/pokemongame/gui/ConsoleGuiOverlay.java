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
import com.joseph.pokemongame.reference.Reference;

public class ConsoleGuiOverlay implements IGuiOverlay {
	private BufferedImage back;
	private FontRenderContext frc;
	private String command;
	private boolean cursor;
	private boolean shouldRemove;
	private int cursorTick;
	private int x;
	private int y;
	
	private static ConsoleGuiOverlay instance;
	
	public ConsoleGuiOverlay(BufferedImage back) {
		this.back = back;
		this.command = "";
		Graphics2D g2d = (Graphics2D) GameEngine.getInstance().getG();
		this.frc = g2d.getFontRenderContext();
		this.shouldRemove = false;
		this.cursor = true;
		this.cursorTick = 0;
		this.x = 1 * 22;
		this.y = 2 * 22;
		
		instance = this;
	}
	
	@Override
	public void drawGuiBackground(Graphics g, ImageObserver observer) {
		g.drawImage(back, x, y, observer);
	}
	
	@Override
	public void drawUpdateableGraphicsElements(Graphics g, ImageObserver observer) {
		// TODO Auto-generated method stub
		g.setColor(Color.DARK_GRAY);
		g.setFont(Reference.TEXT_BOX_FONT);
		g.drawString(this.command, this.x + 20, this.y + 20);
		if (this.cursor) {
			int xOffset = (int) Reference.TEXT_BOX_FONT.getStringBounds(command, frc).getWidth() + 1;
			g.setColor(Reference.CURSOR_COLOR);
			g.fillRect(this.x + 20 + xOffset, this.y + 5, 2, 15);
		}
	}
	
	@Override
	public void updateUpdateableGraphicsElements(double deltaTime) {
		// TODO Auto-generated method stub
		// TODO when command executes
		if (this.cursorTick > 0) {
			this.cursorTick--;
		}
		if (this.cursorTick == 0) {
			this.cursor = !this.cursor;
			this.cursorTick = 30;
		}
	}
	
	@Override
	public boolean removeGui() {
		return this.shouldRemove;
	}
	
	@Override
	public void setGuiToRemove() {
		this.shouldRemove = true;
	}
	
	public void notifyInstanceOfKeyTyped(KeyEvent event) {
		if (event.getID() == KeyEvent.KEY_TYPED) {
			char temp = event.getKeyChar();
			if ((int) temp == 27) { // escape
				return;
			}
			if ((int) temp == 8) { // BackSpace
				if (command.length() == 1) {
					return;
				}
				command = command.substring(0, command.length() - 1);
				return;
			}
			command += temp;
		}
	}
	
	public void notifyEnterPress() {
		// TODO process press by boolean value
	}
	
	public static boolean isConsoleOpen() {
		return !instance.shouldRemove;
	}
	
	public static ConsoleGuiOverlay getInstance() {
		return instance;
	}
}