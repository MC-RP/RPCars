package com.mcrp.rpcars;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;


public class CarEntity implements Serializable {
	
	private static final long serialVersionUID = 7417929529680961180L;

	private transient Entity entity;
	
	private String entityUUID;
	private String worldUUID;
	private double x,z; // Standard Drive Direction

	
	public CarEntity(Entity e) {
		entity = e;
		entityUUID = e.getUniqueId().toString();
		worldUUID = e.getWorld().getUID().toString();
		x = 0;
		z = 0;
	}
	


	public String getEntityUUID() {
		return entityUUID;
	}
	public String getWorldUUID() {
		return worldUUID;
	}
	
	public Entity getEntityDirect() {
		for(Entity e : Bukkit.getWorld(UUID.fromString(worldUUID)).getEntitiesByClass(Minecart.class)) {
			if(e.getUniqueId().toString().equalsIgnoreCase(entityUUID))
				setEntity(e);
				return e;
		}
		
		return null;
	}
	
	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public void setDir(Vector v) {
		x = v.getX();
		z = v.getZ();
	}
	
	public Vector getDirection() {
		return new Vector(x,-0.25,z);
	}
		
}
