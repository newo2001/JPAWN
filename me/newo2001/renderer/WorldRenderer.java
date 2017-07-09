package me.newo2001.renderer;

import me.newo2001.Location;
import me.newo2001.Main;
import me.newo2001.blocks.Block;
import me.newo2001.entity.Player;
import me.newo2001.renderer.WorldRenderer;
import me.newo2001.util.Geometry;
import me.newo2001.world.World;

import java.awt.Color;
import java.util.ArrayList;

public class WorldRenderer {
	
	private Main game; 
	private int renderDistance;

	/**
	 * Create a new WorldRenderer instance
	 * @param instance An instance of the Main class for later reference
	 */
	public WorldRenderer(Main instance) {
		this.game = instance;
		this.renderDistance = 4;
	}
	
	public void render() {
		World world = Main.getWorld();
		Player player = Main.getPlayer();
		int width = game.getGameWidth();
		int height = game.getGameHeight();
		Location location = player.getLocation();
		
		//System.out.println(location.getX() + ", " + location.getBlockX() + ", " + location.getChunkX());
		
		//Render world
		for (int chunkX = location.getChunkX() - renderDistance; chunkX < location.getChunkX() + renderDistance; chunkX++) {
			for (int chunkY = location.getChunkY() - renderDistance; chunkY < location.getChunkY() + renderDistance; chunkY++) {
				if (world.getChunk(chunkX, chunkY) == null) {
					world.createChunk(chunkX, chunkY);
					world.populateChunk(chunkX, chunkY);
				}
				int x = (chunkX - location.getChunkX()) * 16;
				for (ArrayList<Block> blocks : world.getChunk(chunkX, chunkY).getBlocks()) {
					int y = (chunkY - location.getChunkY()) * 16;
					for (Block block : blocks) {
						Color color = block.getColor();
						int pixelX = x * 16 + (int) (width / 2) - (location.getBlockX() % 16) * 16 - (int) Math.floor(((location.getX() - (float) location.getBlockX()) * 16f));
						int pixelY = y * 16 + (int) (height / 2) - (location.getBlockY() % 16) * 16 - (int) Math.floor(((location.getY() - (float) location.getBlockY()) * 16f));
						Geometry.drawRect(pixelX, pixelY, 16, 16, color);
						Geometry.drawRectBorder(pixelX, pixelY, 16, 16, Color.GRAY);
						y++;
					}
					x++;
				}
				
				int chunkDrawX = (chunkX - location.getChunkX()) * 16 * 16 + (width / 2) - (location.getBlockX() % 16) * 16 - (int) Math.floor(((location.getX() - (float) location.getBlockX()) * 16f));
				int chunkDrawY = (chunkY - location.getChunkY()) * 16 * 16 + (height / 2) - (location.getBlockY() % 16) * 16 - (int) Math.floor(((location.getY() - (float) location.getBlockY()) * 16f));
				Geometry.drawRectBorder(chunkDrawX, chunkDrawY, 16*16, 16*16, Color.YELLOW);
			}
		}
		
		Geometry.drawCircle((int) (width / 2), (int) (height / 2), 8, Color.RED);
		Geometry.drawCircleBorder((int) (width / 2), (int) (height / 2), 8, Color.GRAY);
	}
	
	/**
	 * Get the current render-distance in chunks
	 * @return The render-distance in chunks
	 */
	public int getRenderDistance() {
		return renderDistance;
	}
	
	/**
	 * Set the current render-distance in chunks
	 * @param renderDistance The render-distance in chunks
	 */
	public void setRenderDistance(int renderDistance) {
		this.renderDistance = renderDistance;
	}
}
