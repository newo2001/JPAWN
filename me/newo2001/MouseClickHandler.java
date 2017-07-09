package me.newo2001;

import me.newo2001.gui.Gui;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.*;

public class MouseClickHandler extends GLFWMouseButtonCallback {
	
	private static boolean[] down = new boolean[12];
	private static boolean[] press = new boolean[12];
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		down[button] = action != GLFW_RELEASE;
		press[button] = action == GLFW_PRESS;
		
		if (action == GLFW_PRESS) {
			int x = MouseHandler.getX();
			int y = MouseHandler.getY();
			Gui.onMousePressed(button, x, y);
		}
	}
	
	public static boolean isMouseDown(int keycode) {
		return down[keycode];
	}

	public static boolean isMousePressed(int keycode) {
		return press[keycode];
		
	}
	
	public static void update() {
		press = new boolean[12];
	}
	
}
