package me.newo2001.blocks;

import java.awt.Color;

import me.newo2001.Material;
import me.newo2001.tileentity.TileEntity;

public class Block {
	
	private Material type;
	private Color color;
	private boolean hasTileEntity;
	private TileEntity tileEntity;
	
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
	
	public boolean getHasTileEntity() {
		return hasTileEntity;
	}
	
	public void setHasTileEntity(boolean hasTileEntity) {
		this.hasTileEntity = hasTileEntity;
	}
	
	public TileEntity getTileEntity() {
		return tileEntity;
	}
	
	public void setTileEntity(TileEntity tileEntity) {
		this.tileEntity = tileEntity;
	}
	
	public void onCreate() {
		
	}
	
}
