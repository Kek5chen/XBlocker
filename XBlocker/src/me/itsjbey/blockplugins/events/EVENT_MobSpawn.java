package me.itsjbey.blockplugins.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;


public class EVENT_MobSpawn implements Listener {
	
	@EventHandler
	public void MobSpawnEvent(EntitySpawnEvent e) {
		if(e.getEntity().getType() != EntityType.PLAYER) {
			if(e.getEntity().getType() != EntityType.DROPPED_ITEM) {
				e.setCancelled(true);
			}
		}
	}
}
