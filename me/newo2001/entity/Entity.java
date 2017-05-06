package me.newo2001.entity;

import java.util.UUID;

import me.newo2001.Location;
import me.newo2001.world.World;

public abstract class Entity {
	
	private Location location;
	private UUID uuid;
	private String displayName;
	private boolean displayNameVisible;
	
	/**
	 * Spawn a new entity
	 * @param location The location to spawn the entity at
	 */
	public Entity(Location location) {
		this.location = location;
		displayName = "";
		displayNameVisible = false;
		uuid = UUID.randomUUID();
		getWorld().spawnEntity(this);
	}
	
	/**
	 * @return The display-name of the entity
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	/**
	 * @param name The name to set the entity's display-name to
	 */
	public void setDisplayName(String name) {
		displayName = name;
	}
	
	/**
	 * @return If the entity's display-name is visible
	 */
	public boolean getDisplayNameVisible() {
		return displayNameVisible;
	}
	
	/**
	 * @param visible The visibility of the entity's display-name
	 */
	public void setDisplayNameVisible(boolean visible) {
		displayNameVisible = visible;
	}
	
	/**
	 * @param The new location of the entity
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * @return The entity's current location
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * @return The world the entity currently belongs to
	 */
	public World getWorld() {
		return location.getWorld();
	}
	
	/**
	 * @param x The entity's new x-coordinate
	 */
	public void setX(float x) {
		location.setX(x);
	}
	
	/**
	 * @param x The entity's new x-coordinate
	 */
	public void setX(int x) {
		location.setX(x);
	}
	
	/**
	 * @return The enitty's x-coordinate
	 */
	public float getX() {
		return location.getX();
	}
	
	/**
	 * @param y The entity's new y-coordinate
	 */
	public void setY(float y) {
		location.setY(y);
	}
	
	/**
	 * @param y The entity's new y-coordinate
	 */
	public void setY(int y) {
		location.setY(y);
	}
	
	/**
	 * @return The entity's y-coordinate
	 */
	public float getY() {
		return location.getY();
	}
	
	/**
	 * Kill the entity to effectively remove it
	 */
	public void kill() {
		getWorld().killEntity(uuid);
	}
	
	/**
	 * @param uuid The entity's new UUID
	 */
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * @return The entity's UUID
	 */
	public UUID getUUID() {
		return uuid;
	}
	
	/**
	 * update gets called every update-tick for every entity, override this if the entity need updating
	 */
	public void update() {
		
	}
}
