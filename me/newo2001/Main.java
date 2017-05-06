package me.newo2001;

import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import me.newo2001.entity.Player;
import me.newo2001.renderer.WorldRenderer;
import me.newo2001.world.World;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;

public class Main {
	
	private GLFWKeyCallback keyCallback;
	private GLFWWindowSizeCallback windowSizeCallback;
	private long window;
	private World world;
	private Player player;
	private WorldRenderer worldRenderer;
	private TickHandler tickHandler;
	private ArrayList<Integer> viewport = new ArrayList<Integer>();
	
	private final int virtual_width = 1280;
	private final int virtual_height = 720;

	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");
		
		init();
		
		while (!glfwWindowShouldClose(window))
			loop();
		
		
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
	}
	
	private void initOpenGL() {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		
		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(virtual_width, virtual_height, "Hello World!", 0, 0);
		viewport.add(virtual_width);
		viewport.add(virtual_height);
		
		// Get the thread stack and push a new frame
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			
			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
  		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);

		// Make the window visible
		glfwShowWindow(window);
		
		//Callback for scaling the graphics when window size changes
		windowSizeCallback = new GLFWWindowSizeCallback() {
			@Override
			public void invoke(long window, int width, int height) {
				scaleWindow();
			}
		};
		
		glfwSetWindowSizeCallback(window, windowSizeCallback);
		
		//Keyboard input callback
		keyCallback = new KeyboardHandler();
		glfwSetKeyCallback(window, keyCallback);
	}
	
	private void scaleWindow() {
		// Both these values must be your real window size, so of course these values can't be static
		int screen_width = getRawWindowWidth();
		int screen_height = getRawWindowHeight();
		float targetAspectRatio = (float) virtual_width / (float) virtual_height;
		 
		// figure out the largest area that fits in this resolution at the desired aspect ratio
		int width = screen_width;
		int height = (int) (width / targetAspectRatio + 0.5f);
		
		if (height > screen_height) {
			//It doesn't fit our height, we must switch to pillarbox then
		    height = screen_height;
		    width = (int) (height * targetAspectRatio + 0.5f);
		}
		 
		// set up the new viewport centered in the backbuffer
		int vp_x = (screen_width / 2) - (width / 2);
		int vp_y = (screen_height / 2) - (height / 2);
		
		glViewport(vp_x, vp_y, width, height);
		viewport.set(0, width);
		viewport.set(1, height);
		
		// Now we use glOrtho
		glMatrixMode(GL_PROJECTION);
		glPushMatrix();
		glLoadIdentity();
		glOrtho(0, screen_width, screen_height, 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		glLoadIdentity();
		
		// Push in scale transformations
		glMatrixMode(GL_MODELVIEW);
		glPushMatrix();
		 
		//Now to calculate the scale considering the screen size and virtual size
		float scale_x = (float) ((float) (screen_width) / (float) virtual_width);
		float scale_y = (float) ((float) (screen_height)) / (float) virtual_height;
		glScalef(scale_x, scale_y, 1.0f);
	}
	
	private void init() {
		initOpenGL();
		
		world = new World();
		worldRenderer = new WorldRenderer(this);
		tickHandler = new TickHandler(this);
		
		for (int x = -4; x < 4; x++) {
			for (int y = -4; y < 4; y++) {
				world.populateChunk(x, y);
			}
		}
		
		player = (Player) world.spawnEntity(new Player(new Location(world, 0, 0)));
	}
	
	private void loop() {
		GL.createCapabilities();
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwPollEvents();
		tickHandler.tick();
		worldRenderer.render();
		
		glfwSwapBuffers(window);
		scaleWindow();
	}
	
	/**
	 * Use get(0) for width and get(1) for height
	 * @see getRawWindowWidth
	 * @see getRawWindowHeight
	 * @Warning Don't use these coordinates in drawing anything to the screen, they are just for getting the dimensions of the window
	 * @return An ArrayList with the width and height of the window in pixels
	 */
	public ArrayList<Integer> getRawWindowSize(long window) {
		IntBuffer w = BufferUtils.createIntBuffer(1);
		IntBuffer h = BufferUtils.createIntBuffer(1);
		glfwGetWindowSize(window, w, h);
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(w.get(0));
		list.add(h.get(0));
		return list;
	}
	
	/**
	 * @see getRawWindowSize
	 * @see getRawWindowHeight
	 * @Warning Don't use these coordninates in drawing anything to the screen, they are just for getting the dimensions of the window
	 * @return The height of the window in pixels
	 */
	public int getRawWindowWidth() {
		return getRawWindowSize(window).get(0);
	}
	
	/**
	 * @see getRawWindowSize
	 * @see getRawWindowWidth
	 * @Warning Don't use these coordinates in drawing anything to the screen, they are just for getting the dimensions of the window
	 * @return The height of the window in pixels
	 */
	public int getRawWindowHeight() {
		return getRawWindowSize(window).get(1);
	}
	
	/**
	 * Use get(0) for width and get(1) for height
	 * @see getViewportWidth
	 * @see getViewportHeight
	 * @Warning Don't use these coordinates in drawing anything to the screen, they are just for getting the dimensions of the viewport
	 * @return An ArrayList containing The height and width of the viewport in pixels
	 */
	public ArrayList<Integer> getViewportSize() {
		return viewport;
	}
	
	/**
	 * @Warning Don't use these coordinates in drawing anything to the screen, they are just for getting the dimensions of the viewport
	 * @see getViewportSize
	 * @see getViewportHeight
	 * @return The width of the viewport in pixels
	 */
	public int getViewportWidth() {
		return viewport.get(0);
	}
	
	/**
	 * @see getViewportSize
	 * @see getViewportWidth
	 * @Warning Don't use these coordinates in drawing anything to the screen, they are just for getting the dimensions of the viewport
	 * @return The height of the viewport in pixels
	 */
	public int getViewportHeight() {
		return viewport.get(1);
	}
	
	/**
	 * Use get(0) for width and get(1) for height
	 * @see getGameWidth
	 * @see getGameHeight
	 * @return An ArrayList with the screen dimensions for OpenGL drawing
	 */
	public ArrayList<Integer> getGameSize() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(virtual_width);
		list.add(virtual_height);
		return list;
	}
	
	/**
	 * @see getGameSize
	 * @see getGameHeight
	 * @return The width of the screen forOopenGL drawing
	 */
	public int getGameWidth() {
		return virtual_width;
	}
	
	/**
	 * @see getGameSize
	 * @see getGameWidth
	 * @return The height of the screen for OpenGL drawing
	 */
	public int getGameHeight() {
		return virtual_height;
	}
	
	/**
	 * @return The OpenGL window-ID
	 */
	public long getWindow() {
		return window;
	}
	
	/**
	 * @return The world instance
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * @return The player instance
	 */
	public Player getPlayer() {
		return player;
	}
}
