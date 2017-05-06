package me.newo2001.world;

import java.util.ArrayList;

import me.newo2001.Material;
import me.newo2001.blocks.Block;

public class Chunk {
	
	private ArrayList<ArrayList<Block>> blocks;
	private int x;
	private int y;
	
	public Chunk(int x, int y) {
		this.x = x;
		this.y = y;
		
		blocks = new ArrayList<ArrayList<Block>>();
		for (int i = 0; i < 16; i++) {
			ArrayList<Block> row = new ArrayList<Block>();
			for (int j = 0; j < 16; j++) {
				row.add(Material.getBlock(Material.AIR));
			}
			blocks.add(row);
		}
	}
	
	public Chunk(int x, int y, ArrayList<ArrayList<Block>> blocks) {
		this.x = x;
		this.y = y;
		this.blocks = blocks;
	}
	
	public ArrayList<ArrayList<Block>> getBlocks() {
		return blocks;
	}
	
	public void setBlocks(ArrayList<ArrayList<Block>> blocks) {
		this.blocks = blocks;
	}
	
	public Block getBlock(int x, int y) {
		if (x > 15 || y > 15)
			return null;
		return blocks.get(x).get(y);
	}
	
	public void setBlock(int x, int y, Material material) {
		setBlock(x, y, Material.getBlock(material));
	}
	
	public void setBlock(int x, int y, Block block) {
		blocks.get(x).set(y, block);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
