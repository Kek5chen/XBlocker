package me.itsjbey.blockplugins.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EVENT_BlockPlace implements Listener {

	Boolean customPlace;
	List<Material> allowed = new ArrayList<>();
	JavaPlugin jp;
	
	public EVENT_BlockPlace(JavaPlugin p) {
		jp = p;
		loadConfig();
	}
	
	@EventHandler
	public void BlockPlaceEvent(BlockPlaceEvent e) {
		if(customPlace) {
			if(!allowed.contains(e.getBlock().getType())) {
				e.setCancelled(true);
			}
		}else {
			e.setCancelled(true);
		}
	}
	
	private void loadConfig() {
		customPlace = jp.getConfig().getBoolean("Enabled.CustomPlaceableBlocks");
		int i = 0;
		for (String s : jp.getConfig().getStringList("PlaceableBlocks")) {
			try {
				allowed.add(Material.getMaterial(Integer.getInteger(s)));
				i++;
			} catch (Exception e) {
				try {
					allowed.add(Material.getMaterial(s.toUpperCase()));
					i++;
				} catch (Exception e2) {}
			}
		}
		System.out.println("Added " + i + " Placable Block(s) to the list.");
	}
}
