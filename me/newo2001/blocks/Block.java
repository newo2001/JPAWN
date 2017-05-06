package me.newo2001.blocks;

import java.awt.Color;

import me.newo2001.Material;

public abstract class Block {
	
	private Material type;
	private Color color;
	
	/**
	 * Create a new block instance
	 * @param type The material type this block is made of
	 */
	public Block(Material type) {
		this.type = type;
		this.color = new Color(0f, 0f, 0f);
	}
	
	/** 
	 * @return the material this block is made of
	 */
	public Material getType() {
		return type;
	}
	
	/**
	 * @return The block's color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * @param color The color for drawing the block
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
}
