package me.itsjbey.blockplugins.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.itsjbey.blockplugins.enums.Interactables;

public class EVENT_Interaction implements Listener {

	Boolean customInteract;
	List<Material> allowed = new ArrayList<>();
	JavaPlugin jp;
	
	public EVENT_Interaction(JavaPlugin p) {
		jp = p;
		loadConfig();
	}
	
	@EventHandler
	public void InteractionEvent(PlayerInteractEvent e) {
		if(!allowed.contains(e.getClickedBlock().getType())) {
			if(e.getAction() == Action.PHYSICAL) {
				if(customInteract) {
					if(!allowed.contains(e.getClickedBlock().getType())) {
						e.setCancelled(true);
					}
				}else {
					e.setCancelled(true);
				}
			}else if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				for (Interactables i : Interactables.values()) {
					if(e.getClickedBlock().getType().name().contains(i.name())) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
	
	private void loadConfig() {
		customInteract = jp.getConfig().getBoolean("Enabled.CustomInteractables");
		int i = 0;
		for (String s : jp.getConfig().getStringList("Interactables")) {
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
		System.out.println("Added " + i + " Interactable Block(s) to the list.");
	}
}
