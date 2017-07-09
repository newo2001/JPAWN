package me.newo2001;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class MouseHandler extends GLFWCursorPosCallback {
	
	private static int x;
	private static int y;
	
	@Override
	public void invoke(long window, double posX, double posY) {
		x = (int) Math.floor(posX);
		y = (int) Math.floor(posY);
	}
	
	public static int getX() {
		return x;
	}
	
	public static int getY() {
		return y;
	}
	
	/**
	 * Get the cursor location
	 * @return An array with index 0 as the x-coordinate and index 1 as the y-coordinate
	 */
	public static int[] getLocation() {
		int[] array = new int[2];
		array[0] = x;
		array[1] = y;
		return array;
	}
	
	
	
}
