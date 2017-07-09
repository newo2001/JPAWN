package me.newo2001.gui;

public class Slot {
	
	private int x;
	private int y;
	private int id;
	private final int width = 32;
	private final int height = 32;
	
	public Slot(int id, int x, int y) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
