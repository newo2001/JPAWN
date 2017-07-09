package me.newo2001.entity;

import me.newo2001.KeyboardHandler;
import me.newo2001.Location;
import me.newo2001.container.Container;
import me.newo2001.container.ContainerInventory;
import me.newo2001.gui.Gui;
import me.newo2001.gui.GuiInventory;
import me.newo2001.item.ItemStack;
import static org.lwjgl.glfw.GLFW.*;

public class Player extends EntityLiving {
	
	private ContainerInventory inventory;
	private ItemStack cursorItem;
	
	public Player(Location location) {
		super(location);
		inventory = new ContainerInventory();
	}
	
	@Override
	public void update() {
		if (KeyboardHandler.isKeyDown(GLFW_KEY_W)) {
			setY(getY() - getMovementSpeed());
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_A)) {
			setX(getX() - getMovementSpeed());
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_S)) {
			setY(getY() + getMovementSpeed());
		}
		if (KeyboardHandler.isKeyDown(GLFW_KEY_D)) {
			setX(getX() + getMovementSpeed());
		}
		if (KeyboardHandler.isKeyPressed(GLFW_KEY_E)) {
			if (Gui.getCurrentGui() != null) {
				Gui.closeGui();
			} else {
				Gui.openGui((Gui) new GuiInventory());
			}
		}
	}
	
	public void openInventory() {
		Gui.openGui(new GuiInventory());
	}
	
	public void closeInventory() {
		if (Gui.getCurrentGui() instanceof GuiInventory) {
			Gui.closeGui();
		}
	}
	
	public Container getInventory() {
		return inventory;
	}
	
	public void setCursorItem(ItemStack stack) {
		cursorItem = stack;
	}
	
	public ItemStack getCursorItem() {
		return cursorItem;
	}
}
