package me.newo2001;

import me.newo2001.entity.Entity;

public class TickHandler {
	
	public static void tick() {
		for (Entity entity : Main.getWorld().getEntities()) {
			entity.update();
		}
		/*(for (TileEntity te : game.getWorld().getTileEntities()) {
			te.update();
		}*/
	}
	
}
