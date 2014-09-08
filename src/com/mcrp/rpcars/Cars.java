package com.mcrp.rpcars;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

public class Cars {
	public ArrayList<CarEntity> ingCars;
	
	public ArrayList<Car> carTypes;
	
	public Cars() {
		ingCars = new ArrayList<CarEntity>();
	}
	
	public boolean isCar(Entity ent) {
		for(CarEntity e : ingCars)
			if(ent.getUniqueId().toString().equalsIgnoreCase(e.getEntityUUID()))
				return true;
		
		return false;
	}
	
	public void addCar(CarEntity e) {
		Bukkit.getLogger().info("Added a car!");
		if(ingCars.contains(e))
			return;
		ingCars.add(e);
	}

	public CarEntity getCar(Entity vehicle) {
		for(CarEntity e : ingCars)
			if(vehicle.getUniqueId().toString().equalsIgnoreCase(e.getEntityUUID()))
				return e;
		Bukkit.getLogger().info("Returning Null Wierd");
		return null;
	}
	
	
}
