package me.newo2001.entity;

import me.newo2001.Location;

public abstract class EntityLiving extends Entity {
	
	private int health;
	private int maxHealth;
	private float movementSpeed;
	
	public EntityLiving(Location location) {
		super(location);
		movementSpeed = 0.1f;
	}
	
	/**
	 * @param The new health of the entity
	 */
	public void setHealth(int health) {
		this.health = health;
		if (health <= 0)
			kill();
	}
	
	/**
	 * @return The maximum amount of health of the entity
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * @param maxHealth The new maxiumum amount of health of the entity
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	/**
	 * @return The entity's current health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * @param damage The amount of damage to deal to the entity
	 */
	public void damage(int damage) {
		health = Math.max(health - damage, 0);
		if (health == 0)
			kill();
	}
	
	/**
	 * @return The movement-speed of the entity in blocks per second
	 */
	public float getMovementSpeed() {
		return movementSpeed;
	}
	
	/**
	 * @param speed The new movement-speed of the entity in blocks per second
	 */
	public void setMovementSpeed(float speed) {
		movementSpeed = speed;
	}
	
}
