package com.mcrp.rpcars;

import org.bukkit.event.Listener;

import org.bukkit.plugin.Plugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.Packets.Client;

public class CarEvents extends PacketAdapter implements Listener{
	
	Cars carManager;
	
	
	public CarEvents(Plugin plugin, Cars carManager) {
		super(plugin, PacketType.Play.Client.STEER_VEHICLE);
		this.carManager = carManager;
	}
	
	@Override
	public void onPacketReceiving(PacketEvent e) {
		if(e.getPacketType() != PacketType.Play.Client.STEER_VEHICLE)
			return;
		float forwardModifier = e.getPacket().getFloat().read(1);
		float sidewayModifier = e.getPacket().getFloat().read(0);
		e.getPlayer().sendMessage("STEERING: " + forwardModifier + ", " + sidewayModifier);
	}

}
