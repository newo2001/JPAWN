package me.newo2001;

import me.newo2001.blocks.*;
import me.newo2001.item.*;

public enum Material {
	AIR,
	DIRT,
	STONE,
	COPPER,
	IRON,
	ITEM_STONE,
	COPPER_ORE,
	IRON_ORE;
	
	/**
	 * Get the block associated with a given material type
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
	
	public static Item getItem(Material material) {
		switch(material) {
		case ITEM_STONE:
			return new ItemStone();
		case COPPER_ORE:
			return new CopperOre();
		case IRON_ORE:
			return new IronOre();
		default:
			return null;
		}
	}
}