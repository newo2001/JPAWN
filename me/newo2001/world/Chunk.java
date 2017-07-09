package me.newo2001.world;

import java.util.ArrayList;

import me.newo2001.Material;
import me.newo2001.blocks.Block;

public class Chunk {
	
	private ArrayList<ArrayList<Block>> blocks;
	private int x;
	private int y;
	
	/**
	 * Create a new chunk
	 * @Warning Don't use this method, use World.createChunk instead
	 * @see World.createChunk
	 * @param x The x-coordinate on chunk scale
	 * @param y The y-coordinate on chunk scale
	 */
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
	
	/**
	 * Create a new chunk
	 * @Warning Don't use this method, use World.createChunk instead
	 * @see World.createChunk
	 * @param x The x-coordinate on chunk scale
	 * @param y The y-coordinate on chunk scale
	 * @param blocks A pre-generated ArrayList-matrix for the blocks in the chunk
	 */
	public Chunk(int x, int y, ArrayList<ArrayList<Block>> blocks) {
		this.x = x;
		this.y = y;
		this.blocks = blocks;
	}
	
	/**
	 * Get all the blocks in the chunk
	 * @return An ArrayList-matrix of all the blocks in the chunk
	 */
	public ArrayList<ArrayList<Block>> getBlocks() {
		return blocks;
	}
	
	/**
	 * Overwrite the chunk's current blocks with new ones
	 * @param blocks A pre-generated ArrayList-matrix of new blocks to fill the chunk
	 */
	public void setBlocks(ArrayList<ArrayList<Block>> blocks) {
		this.blocks = blocks;
	}
	
	/**
	 * Get the block at a position in the chunk, these coordinates have to be in the range 0-15
	 * @param x The x-coordinate within the chunk
	 * @param y The y-coordinate within the chunk
	 * @return The block at that position
	 */
	public Block getBlock(int x, int y) {
		if (x > 15 || y > 15)
			return null;
		return blocks.get(x).get(y);
	}
	
	/**
	 * Set the block at a position in the chunk, these coordinates have to be in the range 0-15
	 * @param x The x-coordinate within the chunk
	 * @param y The y-coordinate within the chunk
	 * @param material The material of the block to place
	 */
	public void setBlock(int x, int y, Material material) {
		if (x > 15 || y > 15)
			return;
		setBlock(x, y, Material.getBlock(material));
	}
	
	/**
	 * Set the block at a position in the chunk, these coordinates have to be in the range 0-15
	 * @param x The x-coordinate within the chunk
	 * @param y The y-coordinate within the chunk
	 * @param block The block to place
	 */
	public void setBlock(int x, int y, Block block) {
		if (x > 15 || y > 15)
			return;
		blocks.get(x).set(y, block);
	}
	
	/**
	 * Get the chunk's x-coordinate on chunk-scale
	 * @return The chunk's x-coordinate on chunk-scale
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get the chunk's y-coordinate on chunk-scale
	 * @return The chunk's y-coordinate on chunk-scale
	 */
	public int getY() {
		return y;
	}
}
