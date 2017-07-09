package me.newo2001.renderer;

import me.newo2001.gui.Gui;

public class GuiRenderer {
	
	public static void render() {
		if (Gui.getCurrentGui() != null) {
			Gui.getCurrentGui().drawGui();
		}
	}
}
