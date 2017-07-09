package me.newo2001.item;

import me.newo2001.Material;

public class ItemStack {
	
	private Item item;
	private int stackSize;
	private int damage;
	
	public ItemStack(Material material, int stackSize, int damage) {
		this.item = Material.getItem(material);
		this.stackSize = stackSize;
		this.damage = damage;
	}
	
	public ItemStack(Material material, int stackSize) {
		this(material, stackSize, 0);
	}
	
	public ItemStack(Material material) {
		this(material, 1, 0);
	}
	
	public Item getItem() {
		return item;
	}
	
	public ItemStack setItem(Item item) {
		this.item = item;
		return this;
	}
	
	public ItemStack setItem(Material material) {
		this.item = Material.getItem(material);
		return this;
	}
	
	public Material getMaterial() {
		return item.getMaterial();
	}
	
	public int getStackSize() {
		return stackSize;
	}
	
	public ItemStack setStackSize(int stackSize) {
		this.stackSize = Math.max(Math.min(stackSize, item.getMaxStackSize()), 1);
		return this;
	}
	
	/**
	 * Add a certain amount of items to an ItemStack
	 * @param amount The amount to add
	 * @return The amount that it failed to add
	 */
	public int addStackSize(int amount) {
		stackSize = Math.min(stackSize + amount, item.getMaxStackSize());
		return Math.max(amount - (item.getMaxStackSize() - stackSize), 0);
	}
	
	public int getDamage() {
		return damage;
	}
	
	public ItemStack setDamage(int damage) {
		this.damage = damage;
		return this;
	}
}
