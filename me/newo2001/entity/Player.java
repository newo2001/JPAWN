package me.newo2001.entity;

import me.newo2001.KeyboardHandler;
import me.newo2001.Location;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends EntityLiving {
	
	public Player(Location location) {
		super(location);
	}
	
	@Override
	public void update() {
		if (KeyboardHandler.isKeyDown(GLFW_KEY_W)) {
			setY(getY() + getMovementSpeed());
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_A)) {
			setX(getX() - getMovementSpeed());
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_S)) {
			setY(getY() - getMovementSpeed());
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_D)) {
			setX(getX() + getMovementSpeed());
		}
		
		if (getWorld().getChunkAt(getLocation()) == null) {	
			getWorld().populateChunk((int) Math.floor((int) getX() / 16), (int) Math.floor((int) getY() / 16));
		}
	}
}
