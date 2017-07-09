package me.newo2001;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;

public class KeyboardHandler extends GLFWKeyCallback {
	
	private static boolean[] down = new boolean[65536];
	private static boolean[] press = new boolean[65536];
	
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		press[key] = action == GLFW_PRESS;
		down[key] = action != GLFW_RELEASE;
	}
	
	/**
	 * Check if a key is pressed
	 * @param keycode The LWJGL-key-constant for the given key
	 * @return true if the key is pressed, false if they key is not pressed
	 */
	public static boolean isKeyDown(int keycode) {
		return down[keycode];
	}

	public static boolean isKeyPressed(int keycode) {
		return press[keycode];
	}
	
	public static void update() {
		press = new boolean[65536];
	}

}
