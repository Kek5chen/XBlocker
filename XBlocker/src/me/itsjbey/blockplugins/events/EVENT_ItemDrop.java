package me.itsjbey.blockplugins.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class EVENT_ItemDrop implements Listener {
	
	@EventHandler
	public void DropEvent(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
}
