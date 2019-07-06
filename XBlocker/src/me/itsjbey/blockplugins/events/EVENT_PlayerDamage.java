package me.itsjbey.blockplugins.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EVENT_PlayerDamage implements Listener{
	
	
	@EventHandler
	public void PlayerDamageEvent(EntityDamageEvent e) {
		if(e.getEntityType() == EntityType.PLAYER) {
			e.setCancelled(true);
		}
	}
}
