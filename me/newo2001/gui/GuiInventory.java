package me.newo2001.gui;

import me.newo2001.Main;

public class GuiInventory extends GuiContainer {
	
	public GuiInventory() {
		super(100, 100, 480, 360, Main.getPlayer().getInventory());
		createSlots();
	}
	
	private void createSlots() {
		addSlot(0, 32, 32);
		addSlot(1, 64, 64);
	}
	
}
