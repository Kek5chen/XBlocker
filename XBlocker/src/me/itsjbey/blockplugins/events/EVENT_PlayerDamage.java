package me.itsjbey.blockplugins.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EVENT_PlayerDamage implements Listener{
	
	
	@EventHandler
	public void PlayerDamageEvent(EntityDamageEvent e) {
		e.setCancelled(true);
	}
}
