package com.mcrp.rpcars;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;

public class CarEvents extends PacketAdapter implements Listener{
	
	Cars carManager;
	
	
	public CarEvents(Plugin plugin, Cars carManager) {
		super(plugin, PacketType.Play.Client.STEER_VEHICLE);
		this.carManager = carManager;
	}

}
