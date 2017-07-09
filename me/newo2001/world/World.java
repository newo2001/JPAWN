package me.newo2001.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import me.newo2001.Location;
import me.newo2001.Material;
import me.newo2001.blocks.Block;
import me.newo2001.entity.Entity;
import me.newo2001.tileentity.TileEntity;

public class World {
	
	private HashMap<Integer, HashMap<Integer, Chunk>> chunks;
	private HashMap<UUID, Entity> entities;
	private ArrayList<TileEntity> tileEntities;
	
	/**
	 * Create a new world
	 */
	public World() {
		entities = new HashMap<UUID, Entity>();
		chunks = new HashMap<Integer, HashMap<Integer, Chunk>>();
	}
	
	/**
	 * Create a new chunk
	 * @param x The chunk's x-coordinate on chunk scale
	 * @param y The chunk's y-coordinate on chunk scale
	 * @return The instance of the new chunk for chaining
	 */
	public Chunk createChunk(int x, int y) {
		Chunk chunk = new Chunk(x, y);
		if (!chunks.containsKey(x))
			chunks.put(x, new HashMap<Integer, Chunk>());
		HashMap<Integer, Chunk> row = chunks.get(x);
		row.put(y, chunk);
		chunks.put(x, row);
		return chunk;
	}
	
	/**
	 * Create a new chunk
	 * @param x The chunk's x-coordinate on chunk scale
	 * @param y The chunk's y-coordinate on chunk scale
	 * @param blocks A pre-generated ArrayList-matrix of blocks in the chunk
	 * @return The instance of the new chunk for chaining
	 */
	public Chunk createChunk(int x, int y, ArrayList<ArrayList<Block>> blocks) {
		Chunk chunk = new Chunk(x, y, blocks);
		if (!chunks.containsKey(x))
			chunks.put(x, new HashMap<Integer, Chunk>());
		HashMap<Integer, Chunk> row = chunks.get(x);
		chunks.put(x, row);
		return chunk;
	}
	
	/**
	 * Get all the generated chunks in this world
	 * @return A HashMap-matrix of all the currently generated chunks
	 */
	public HashMap<Integer, HashMap<Integer, Chunk>> getChunks() {
		return chunks;
	}
	
	/**
	 * Get the chunk at this position
	 * @param x The chunk's x-coordinate on chunk-scale
	 * @param y The chunk's y-coordinate on chunk-scale
	 * @return The instance of the requested chunk or null if it didn't exist
	 */
	public Chunk getChunk(int x, int y) {
		if (chunks.containsKey(x) && chunks.get(x).containsKey(y))
			return chunks.get(x).get(y);
		return null;
	}
	
	/**
	 * Get the chunk at this location
	 * @param location The location of the chunk in block coordinates
	 * @return The instance of the requested chunk or null if it didn't exist
	 */
	public Chunk getChunkAt(Location location) {
		int x = location.getBlockX();
		int y = location.getBlockY();
		if (chunks.containsKey((int) Math.floor(x / 16)) && chunks.get((int) Math.floor(x / 16)).containsKey((int) Math.floor(y / 16)))
			return chunks.get((int) Math.floor(x / 16)).get((int) Math.floor(y / 16));
		return null;
	}
	
	/**
	 * set a block at this location
	 * @param The location to set the block at
	 * @param The block instance you want the block to be
	 */
	public void setBlock(Location location, Block block) {
		int x = location.getBlockX();
		int y = location.getBlockY();
		int chunkX = (int) Math.floor(x / 16);
		int chunkY = (int) Math.floor(y / 16);
		Chunk chunk;
		if (!chunks.containsKey(chunkX)|| !chunks.get(chunkX).containsKey(chunkY)) {
			chunk = createChunk(chunkX, chunkY);
			populateChunk(chunk);
		} else
			chunk = chunks.get(chunkX).get(chunkY);
		chunk.setBlock(x % 16, y % 16, block);
	}
	
	/**
	 * Set a block at this location
	 * @param location The location to set the block at
	 * @param material The material to set the block to
	 */
	public void setBlock(Location location, Material material) {
		setBlock(location, Material.getBlock(material));
	}
	
	/**
	 * Get the block at this location
	 * @param location The location of the block inside the chunk, coordinates has to be in the range 0-15
	 * @return The requested block instance
	 */
	public Block getBlockAt(Location location) {
		int x = location.getBlockX();
		int y = location.getBlockY();
		int chunkX = (int) Math.floor(x / 16);
		int chunkY = (int) Math.floor(y / 16);
		if (!chunks.containsKey(chunkX)|| !chunks.get(chunkX).containsKey(chunkY))
			return null;
		Chunk chunk = chunks.get(chunkX).get(chunkY);
		return chunk.getBlock(x % 16, y % 16);
	}
	
	/**
	 * Populate the chunk at the requested coordinates using the world-generator
	 * @param x The chunk's x-coordinate on chunk scale
	 * @param y The chunk's y-coordinate on chunk scale
	 * @return The instance of the chunk for chaining
	 */
	public Chunk populateChunk(int x, int y) {
		if (this.getChunk(x, y) == null)
			this.createChunk(x, y);
		return populateChunk(this.getChunk(x, y));
	}
	
	/**
	 * Populate the chunk instance using the world-generator
	 * @param chunk The chunk instance to populate
	 * @return The instance of the chunk after population for chaining
	 */
	public Chunk populateChunk(Chunk chunk) {
		Random random = new Random();
		for (int chunkX = 0; chunkX < 16; chunkX++) {
			for (int chunkY = 0; chunkY < 16; chunkY++) {
				double rand = random.nextDouble();
				if (rand < 0.025) {
					chunk.setBlock(chunkX, chunkY, Material.IRON);
				} else if (rand < 0.06) {
					chunk.setBlock(chunkX, chunkY, Material.COPPER);
				} else if (rand < 0.15) {
					chunk.setBlock(chunkX, chunkY, Material.STONE);
				} else {
					chunk.setBlock(chunkX, chunkY, Material.DIRT);
				}
			}
		}
		return chunk;
	}
	
	/**
	 * Spawn an entity in this world
	 * @Warning Don't call this method, it already gets called in the constructor of the Entity class
	 * @param entity The entity instance that is being spawned
	 * @return The entity instance of chaining
	 */
	public Entity spawnEntity(Entity entity) {
		entities.put(entity.getUUID(), entity);
		return entity;
	}
	
	/**
	 * Kill an entity by UUID
	 * @param uuid The UUID of the entity to kill
	 */
	public void killEntity(UUID uuid) {
		entities.remove(uuid);
	}
	
	/**
	 * Get all the entities in this world
	 * @return A Collection of all entities in this world
	 */
	public Collection<Entity> getEntities() {
		return entities.values();
	}
	
	/**
	 * Get an entity by UUID
	 * @param uuid The UUID of the entity
	 * @return The entity instance of the entity with that uuid or null if it doesn't exist
	 */
	public Entity getEntity(UUID uuid) {
		return entities.get(uuid);
	}
	
	public ArrayList<TileEntity> getTileEntities() {
		return tileEntities;
	}
}
