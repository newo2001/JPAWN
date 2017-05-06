package me.newo2001;

import me.newo2001.blocks.*;

public enum Material {
	AIR,
	DIRT,
	STONE,
	COPPER,
	IRON;
	
	/**
	 * @param material The material to create a new block from
	 * @return A new instance of the block from that material
	 */
	public static Block getBlock(Material material) {
		switch(material) {
		case AIR:
			return new Air();
		case DIRT:
			return new Dirt();
		case STONE:
			return new Stone();
		case COPPER:
			return new Copper();
		case IRON:
			return new Iron();
		default:
			return null;
		}
	}
}