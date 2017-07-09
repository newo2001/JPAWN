package me.newo2001.renderer;

import me.newo2001.Main;

public class Renderer {
	
	private Main game;
	private WorldRenderer worldRenderer;
	
	public Renderer(Main instance) {
		game = instance;
		worldRenderer = new WorldRenderer(game);
	}
	
	public void render() {
		worldRenderer.render();
		GuiRenderer.render();
	}
	
}
