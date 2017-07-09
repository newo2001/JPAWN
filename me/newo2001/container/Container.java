package me.newo2001.container;

import me.newo2001.Material;
import me.newo2001.item.Item;
import me.newo2001.item.ItemStack;
import me.newo2001.tileentity.TileEntity;

public class Container {
	
	private ItemStack[] inventory;
	private final int slots;
	private TileEntity tileEntity;
	
	public Container(int slots) {
		this.slots = slots;
		inventory = new ItemStack[slots];
	}
	
	public int getSlots() {
		return slots;
	}
	
	public void setSlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
	}
	
	public ItemStack getSlotContents(int slot) {
		return inventory[slot];
	}
	
	public Material getMaterialInSlot(int slot) {
		return inventory[slot].getMaterial();
	}
	
	public Item getItemInSlot(int slot) {
		if (inventory[slot] == null) {
			return null;
		}
		return inventory[slot].getItem();
	}
	
	public int getItemCount(Material material) {
		int count = 0;
		for (ItemStack stack : inventory) {
			if (stack.getMaterial() == material) {
				count += stack.getStackSize();
			}
		}
		return count;
	}
	
	public TileEntity getTileEntity() {
		return tileEntity;
	}
	
	public void setTileEntity(TileEntity tileEntity) {
		this.tileEntity = tileEntity;
	}
	
	public boolean fitsInSlot(int id, ItemStack stack) {
		return (stack.getMaterial() == inventory[id].getMaterial() && stack.getDamage() == inventory[id].getDamage() && inventory[id].getStackSize() + stack.getStackSize() <= stack.getItem().getMaxStackSize()) || inventory[id] == null || stack == null;
	}
	
	/**
	 * Get the first empty slot in the container
	 * @return The id of the first empty slot or -1 if there is no empty slot
	 */
	public int firstEmptySlot() {
		for (int i = 0; i < slots; i++) {
			if (inventory[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
}
