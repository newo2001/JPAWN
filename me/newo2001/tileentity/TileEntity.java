package me.newo2001.tileentity;

import me.newo2001.Location;

public class TileEntity {
	
	private Location location;
	
	public TileEntity(Location location) {
		this.location = location;
	}
	
	public void update() {
		
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
}
