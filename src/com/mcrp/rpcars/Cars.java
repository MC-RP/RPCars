package com.mcrp.rpcars;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;

public class Cars {
	public ArrayList<CarEntity> ingCars;
	
	public ArrayList<Car> carTypes;
	
	public static Entity getMinecartEntity(CarEntity e) {
		for(Entity ent : Bukkit.getWorld(UUID.fromString(e.getWorldUUID())).getEntitiesByClass(Minecart.class)) {
			if(ent.getUniqueId().toString() == e.getEntityUUID())
				return ent;
		}
		
		return null;
		
	}
	
}
