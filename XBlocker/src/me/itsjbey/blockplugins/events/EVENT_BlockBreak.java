package me.itsjbey.blockplugins.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EVENT_BlockBreak implements Listener {

	Boolean customBreak;
	List<Material> allowed = new ArrayList<>();
	JavaPlugin jp;
	
	public EVENT_BlockBreak(JavaPlugin p) {
		jp = p;
		loadConfig();
	}
	
	@EventHandler
	public void BlockBreakEvent(BlockBreakEvent e) {
		if(customBreak) {
			if(!allowed.contains(e.getBlock().getType())) {
				e.setCancelled(true);
			}
		}else {
			e.setCancelled(true);
		}
	}
	
	private void loadConfig() {
		customBreak = jp.getConfig().getBoolean("Enabled.CustomBreakableBlocks");
		int i = 0;
		for (String s : jp.getConfig().getStringList("BreakableBlocks")) {
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
		System.out.println("Added " + i + " Breakable Block(s) to the list.");
	}
}
