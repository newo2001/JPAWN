package me.newo2001.gui;

import java.awt.Color;

import me.newo2001.Main;
import me.newo2001.MouseHandler;
import me.newo2001.container.Container;
import me.newo2001.entity.Player;
import me.newo2001.item.ItemStack;
import me.newo2001.util.Geometry;
import me.newo2001.util.StackUtils;
import static org.lwjgl.glfw.GLFW.*;

public class GuiContainer extends Gui {

	private final Container container;
	private Slot[] slots;
	
	public GuiContainer(int x, int y, int width, int height, Container container) {
		super(x, y, width, height);
		this.container = container;
		this.slots = new Slot[container.getSlots()];
	}
	
	public Container getContainer() {
		return container;
	}
	
	public void addSlot(int id, int x, int y) {
		slots[id] = new Slot(id, x, y);
	}
	
	public Slot getSlot(int id) {
		return slots[id];
	}
	
	public Slot[] getSlots() {
		return slots;
	}
	
	public void slotClicked(int id, int button) {
		Player player = Main.getPlayer();
		ItemStack cursor = player.getCursorItem();
		ItemStack stack = container.getSlotContents(id);
		if (button == GLFW_MOUSE_BUTTON_1) {
			if (StackUtils.areStackable(cursor, stack)) {
				//Combine the ItemStacks
				if (stack != null && cursor != null) {
					ItemStack[] stacks = StackUtils.combineItemStacks(cursor, stack);
					container.setSlotContents(id, stacks[0]);
					player.setCursorItem(stacks[1]);
				//Put ItemStack in container
				} else if (cursor != null && stack == null) {
					container.setSlotContents(id, cursor);
					player.setCursorItem(null);
				//Pickup ItemStack from the slot
				} else if (cursor == null && stack != null) {
					player.setCursorItem(stack);
					container.setSlotContents(id, null);
				}
			//Swap the ItemStacks
			} else {
				container.setSlotContents(id, cursor);
				player.setCursorItem(stack);
			}
		}
		
		if (button == GLFW_MOUSE_BUTTON_2) {
			if (StackUtils.areStackable(cursor, stack)) {
				//Split ItemStacks
				if (player.getCursorItem() == null) {
					ItemStack[] stacks = StackUtils.splitItemStack(stack);
					player.setCursorItem(stacks[0]);
					container.setSlotContents(id, stacks[1]);
				//Put a single item into the slot
				} else {
					container.setSlotContents(id, cursor.setStackSize(1));
					player.setCursorItem(cursor.setStackSize(cursor.getStackSize() - 1));
					if (cursor.getStackSize() - 1 == 0) {
						player.setCursorItem(null);
					}
				}
			}
		}
	}
	
	public Slot getMouseOverSlot() {
		int mouseX = MouseHandler.getX();
		int mouseY = MouseHandler.getY();
		for (Slot slot : getSlots()) {
			if (mouseX - x > slot.getX() && mouseX - x < slot.getX() + slot.getWidth() && mouseY - y > slot.getY() && mouseY - y < slot.getY() + slot.getHeight()) {
				return slot;
			}
		}
		return null;
	}
	
	@Override
	public void drawGui() {
		Player player = Main.getPlayer();
		super.drawGui();
		if (getMouseOverSlot() != null) {
			Slot slot = getMouseOverSlot();
			Geometry.drawRect(x + slot.getX(), y + slot.getY() + 1, slot.getWidth() - 1, slot.getHeight() - 1, Color.GRAY);
		}
		for (Slot slot : slots) {
			Geometry.drawRectBorder(slot.getX() + x, slot.getY() + y, slot.getWidth(),slot.getHeight(), Color.BLACK);
			if (container.getItemInSlot(slot.getId()) != null) {
				Geometry.drawCircle(slot.getX() + x + slot.getWidth() / 2, slot.getY() + y + slot.getHeight() / 2, slot.getWidth() / 2 - 1, container.getSlotContents(slot.getId()).getItem().getColor());
			}
		}
		if (player.getCursorItem() != null) {
			Geometry.drawCircle(MouseHandler.getX(), MouseHandler.getY(), 15, player.getCursorItem().getItem().getColor());
		}
	}
	
	public static void onMousePressed(int button, int x, int y) {
		GuiContainer gui = (GuiContainer) Gui.getCurrentGui();
		Slot slot = gui.getMouseOverSlot();
		if (slot != null) {
			gui.slotClicked(slot.getId(), button);
		}
	}
}
