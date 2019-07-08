package me.itsjbey.blockplugins.events;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.NumberConversions;

public class EVENT_PlayerEat implements Listener {

	JavaPlugin jp;
	Boolean customConsumablesEnable;
	List<Material> consumables = new ArrayList<>();

	public EVENT_PlayerEat(JavaPlugin j) {
		jp = j;
		loadConfig();
	}

	@EventHandler
	public void EatEvent(PlayerItemConsumeEvent e) {
		if (customConsumablesEnable) {
			if (!consumables.contains(e.getItem().getType())) {
				e.setCancelled(true);
			}
		} else {
			e.setCancelled(true);
		}
	}

	private void loadConfig() {
		customConsumablesEnable = jp.getConfig().getBoolean("Enabled.CustomConsumables");
		if (customConsumablesEnable) {
			int i = 0;
			for (String s : jp.getConfig().getStringList("CustomConsumables")) {
				for (Material m : Material.values()) {
					if(NumberUtils.isNumber(s)) {
						if(m.getId() == NumberConversions.toInt(s)) {
							consumables.add(m);
							i++;
						}
					}else {
						if(m.name().equalsIgnoreCase(s)) {
							consumables.add(m);
							i++;
						}
					}
				}
			}
			System.out.println("Added " + i + " Item(s) to the consumable List.");
		}
	}
}
