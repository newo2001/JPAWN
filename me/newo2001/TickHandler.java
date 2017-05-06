package me.newo2001;

import me.newo2001.entity.Entity;

public class TickHandler {
	
	private Main game;
	private int tps = 30;
	
	public TickHandler(Main instance) {
		game = instance;
	}
	
	public int getTPS() {
		return tps;
	}
	
	public void setTPS(int tps) {
		this.tps = tps;
	}
	
	public void tick() {
		for (Entity entity : game.getWorld().getEntities()) {
			entity.update();
		}
	}
	
}
