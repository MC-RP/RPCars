package com.mcrp.rpcars;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

public class CarEvents extends PacketAdapter implements Listener{
	
	Cars carManager;
	
	public CarEvents(Plugin plugin, Cars carManager) {
		super(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.STEER_VEHICLE);
		this.carManager = carManager;
	}
	
	@EventHandler
	public void entityEnter(VehicleEnterEvent e) {
		if(e.getVehicle().getType() != EntityType.MINECART){
			Bukkit.getLogger().info("Not Minecart");
			return;
		}
		if(!carManager.isCar(e.getVehicle())) {
			Bukkit.getLogger().info("Not Car");
			return;
		}
		if(!(e.getEntered() instanceof Player)) {

			Bukkit.getLogger().info("Not Player");
			e.setCancelled(true);
			return;
		}
		Bukkit.getLogger().info("Entered Car :D");
		Player p = (Player) e.getEntered();
		CarEntity car = carManager.getCar(p.getVehicle());
		car.setDir(p.getEyeLocation().getDirection());
		e.getVehicle().getLocation().setDirection(p.getEyeLocation().getDirection());
		return;
	}
	
	@Override
	public void onPacketReceiving(PacketEvent e) {
		if(e.getPacketType() != PacketType.Play.Client.STEER_VEHICLE)
			return;
		
		if(!e.getPlayer().isInsideVehicle())
			return;
		
		if(!carManager.isCar(e.getPlayer().getVehicle()))
			return;
		
		CarEntity car = carManager.getCar(e.getPlayer().getVehicle());
		
		int speedFactor = 100;
		
		float forwardModifier = e.getPacket().getFloat().read(1);
		// float sidewayModifier = e.getPacket().getFloat().read(0); //TODO: Add steering :>
		if(forwardModifier != 0) {
			
			e.getPlayer().getVehicle().setVelocity(car.getDirection().multiply(forwardModifier).multiply(speedFactor));
		}
	}
	
	@EventHandler
	public void onPlayerRightClickGround(PlayerInteractEvent e) {
		if(e.getPlayer().getItemInHand().getType() == Material.MINECART && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Entity ent = e.getPlayer().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(new Vector(0,2,0)), EntityType.MINECART);
			carManager.addCar(new CarEntity(ent));
		}
	}
	
}
