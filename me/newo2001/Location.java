package me.newo2001;

import me.newo2001.blocks.Block;
import me.newo2001.world.World;

public class Location {
	
	private float x;
	private float y;
	private World world;
	
	public Location(World world, float x, float y) {
		this.x = x;
		this.y = y;
		this.world = world;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getBlockX() {
		return (int) Math.floor(x);
	}
	
	public int getBlockY() {
		return (int) Math.floor(y);
	}
	
	public int getChunkX() {
		return ((int) Math.floor(x)) % 16;
	}
	
	public int getChunkY() {
		return ((int) Math.floor(y)) % 16;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = (float) x;
	}
	
	public void setY(int y) {
		this.y = (float) y;
	}
	
	public Block getBlock() {
		return world.getBlockAt(this);
	}
	
	public Material getMaterial() {
		if (getBlock() == null)
			return null;
		return getBlock().getType();
	}
	
	public void setBlock(Block block) {
		world.setBlock(this, block);
	}
	
	public void setBlock(Material material) {
		setBlock(Material.getBlock(material));
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
}
