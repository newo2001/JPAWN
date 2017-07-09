package me.newo2001.util;

import me.newo2001.item.ItemStack;

public class StackUtils {
	
	public static ItemStack[] splitItemStack(ItemStack stack) {
		ItemStack[] stacks = new ItemStack[2];
		if (stack == null) {
			stacks[0] = null;
			stacks[1] = null;
			return stacks;
		}
		
		stacks[0] = stack.setStackSize((int) Math.ceil(stack.getStackSize() / 2));
		stacks[1] = stack.setStackSize((int) Math.floor(stack.getStackSize() / 2));
		if (stacks[1].getStackSize() == 0) {
			stacks[1] = null;
		}
		return stacks;
	}
	
	/**
	 * Combines 2 ItemStacks into 1 if possible
	 * @param stack1 The first ItemStack
	 * @param stack2 The second ItemStack
	 * @return null if the stacks were not stackable or an ItemStack array with 2 indexes. If 2 stacks were needed they will both have ItemStacks otherwise the second index will be null
	 */
	public static ItemStack[] combineItemStacks(ItemStack stack1, ItemStack stack2) {
		if (!areStackable(stack1, stack2)) {
			return null;
		}
		
		ItemStack[] stacks = new ItemStack[2];
		if (stack1 == null) {
			stacks[0] = stack2;
			return stacks;
		} else if (stack2 == null) {
			stacks[0] = stack1;
			return stacks;
		}
		
		int size = stack2.getStackSize();
		stack1.setStackSize(Math.max(stack1.getStackSize() + stack2.getStackSize(), stack1.getItem().getMaxStackSize()));
		size -= Math.max(stack1.getStackSize() + stack2.getStackSize(), stack1.getItem().getMaxStackSize());
		if (size <= 0) {
			stack2 = null;
		} else {
			stack2.setStackSize(size);
		}
		
		stacks[0] = stack1;
		stacks[1] = stack2;
		return stacks;
	}
	
	public static boolean areStackable(ItemStack stack1, ItemStack stack2) {
		return ((stack1 == null || stack2 == null ) || (stack1.getMaterial() == stack2.getMaterial() && stack1.getDamage() == stack2.getDamage()));
	}
	
	
}
