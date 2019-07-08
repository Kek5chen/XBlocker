package me.itsjbey.blockplugins.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class EVENT_Pickup implements Listener {

	@EventHandler
	public void PickupEvent(PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}
}
