package me.newo2001.item;

import java.awt.Color;

import me.newo2001.Material;

public class Item {
	
	private int maxStackSize;
	private final String name;
	private final Material material;
	private Color color;
	
	public Item(String name, Material material) {
		maxStackSize = 100;
		this.name = name;
		this.material = material;
		color = Color.BLACK;
	}
	
	public String getName() {
		return name;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	public int getMaxStackSize() {
		return maxStackSize;
	}
	
	public void setMaxStackSize(int maxStackSize) {
		this.maxStackSize = maxStackSize;
	}
	
	public Material getMaterial() {
		return material;
	}
}
