package me.newo2001.gui;

import java.awt.Color;

import me.newo2001.util.Geometry;

public class Gui {
	public int width;
	public int height;
	public int x;
	public int y;
	private static Gui currentGui;
	
	public Gui(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void drawGui() {
		Geometry.drawRect(x, y, width, height, Color.LIGHT_GRAY);
		Geometry.drawRectBorder(x, y, width, height, Color.BLACK);
	}
	
	public static Gui getCurrentGui() {
		return currentGui;
	}
	
	public static void openGui(Gui gui) {
		currentGui = gui;
	}
	
	public static void closeGui() {
		currentGui = null;
	}
	
	public static void onMousePressed(int keycode, int x, int y) {
		if (currentGui instanceof GuiContainer) {
			GuiContainer.onMousePressed(keycode, x, y);
		}
	}
}
