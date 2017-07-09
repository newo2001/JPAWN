package me.newo2001.tileentity;

import me.newo2001.Location;
import me.newo2001.container.Container;

public class TileEntityContainer extends TileEntity {
	
	private final Container container;
	
	public TileEntityContainer(Location location, Container container) {
		super(location);
		this.container = container;
	}
	
	public Container getContainer() {
		return container;
	}

}
