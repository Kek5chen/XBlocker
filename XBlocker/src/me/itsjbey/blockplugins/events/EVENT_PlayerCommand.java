package me.itsjbey.blockplugins.events;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

public class EVENT_PlayerCommand implements Listener {

	String prefix;
	HashMap<String, String> configStrings;
	HashMap<String, Boolean> configBools;
	List<String> blockedCmds;

	public EVENT_PlayerCommand(String p, HashMap<String, String> cs, HashMap<String, Boolean> cb, List<String> b) {
		prefix = p;
		configStrings = cs;
		configBools = cb;
		blockedCmds = b;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void cmdPreEvent(PlayerCommandPreprocessEvent e) {
		if (configBools.get("CommandEnabled")) {
			for (String cmd : blockedCmds) {
				if (e.getMessage().startsWith("/" + cmd)) {
					if (e.getPlayer().hasPermission(configStrings.get("Permission"))) {
						if (e.getMessage().startsWith("/pl") || e.getMessage().startsWith("/minecraft:pl")
								|| e.getMessage().startsWith("/?") || e.getMessage().startsWith("/minecraft:?")
								|| e.getMessage().startsWith("/plugin")
								|| e.getMessage().startsWith("/minecraft:plugins")) {
							if (configBools.get("CustomMessageEnabled")) {
								e.setCancelled(true);
								String pls = "";
								if (configStrings.get("CustomUsage").contains("{PLUGINS}")) {
									int i = 0;
									for (Plugin p : Bukkit.getPluginManager().getPlugins()) {
										i++;
										if (i != Bukkit.getPluginManager().getPlugins().length) {
											pls = pls + p.getName() + ", ";
										} else {
											pls = pls + p.getName();
										}
									}
								}
								e.getPlayer()
										.sendMessage(configStrings.get("CustomUsage").replace("&", "§")
												.replace("{PREFIX}", prefix.replace("&", "§"))
												.replace("{AMOUNT}", "" + Bukkit.getPluginManager().getPlugins().length)
												.replace("{PLUGINS}", pls));
							}
						}

					} else {
						e.getPlayer().sendMessage(configStrings.get("NoPermission").replace("&", "§"));
						e.setCancelled(true);
					}
				}
			}
		}
	}
}
