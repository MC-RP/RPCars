package com.mcrp.rpcars;

import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class RoleplayCars extends JavaPlugin {
	
	public Cars carManager;
	public CarEvents eventManager;
	
	public ProtocolManager protocolManager;
	
	
	public void onEnable() {
		
		carManager = new Cars();
		eventManager = new CarEvents(this, carManager);
		
		getLogger().info("RP Cars by FozieGaming has been Enabled :>");
		protocolManager.addPacketListener(eventManager);
		getServer().getPluginManager().registerEvents(eventManager, this);
	}
	
	public void onDisable() {
		
	}
	
	public void onLoad() {
		protocolManager = ProtocolLibrary.getProtocolManager();
	}
	
}
