package me.newo2001.renderer;

import me.newo2001.Main;
import me.newo2001.blocks.Block;
import me.newo2001.entity.Player;
import me.newo2001.renderer.WorldRenderer;
import me.newo2001.util.Geometry;
import me.newo2001.world.Chunk;
import me.newo2001.world.World;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class WorldRenderer {
	
	private Main game; 

	/**
	 * @param instance An instnace of the Main class for later reference
	 */
	public WorldRenderer(Main instance) {
		this.game = instance;
	}
	
	public void render() {
		World world = game.getWorld();
		Player player = game.getPlayer();
		int width = game.getGameWidth();
		int height = game.getGameHeight();
		
		//Render world
		for (HashMap<Integer, Chunk> chunks : world.getChunks().values()) {
			for (Chunk chunk : chunks.values()) {
				int x = chunk.getX() * 16;
				for (ArrayList<Block> blocks : chunk.getBlocks()) {
					int y = chunk.getY() * 16;
					for (Block block : blocks) {
						Color color = block.getColor();
						int pixelX = (int) ((float) x * 16f - (player.getX() * 16f));
						int pixelY = (int) ((float) y * 16f - (player.getY() * 16f));
						Geometry.drawRect(pixelX, pixelY, 16, 16, color);
						Geometry.drawRectBorder(pixelX, pixelY, 16, 16, Color.GRAY);
						y++;
					}
					x++;
				}
			}
		}
		
		Geometry.drawCircle((int) (width / 2) + 8,(int) (height / 2) + 8, 8, Color.RED);
		Geometry.drawCircleBorder((int) (width / 2) + 8, (int) (height / 2) + 8, 8, Color.GRAY);
	}
}
