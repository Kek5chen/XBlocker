package me.itsjbey.blockplugins;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDGetter implements CommandExecutor {
	
	String prefix;
	HashMap<String, Boolean> configBools;
	HashMap<String, String> configStrings;
	String trueKey;
	String licenseKey;
	
	public CMDGetter(String p, HashMap<String, Boolean> cb, HashMap<String, String> cs, String t, String l) {
		prefix = p;
		configBools = cb;
		configStrings = cs;
		trueKey = t;
		licenseKey = l;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(configBools.get("Enabled")) {
			if(licenseKey.equals(trueKey)) {
				if ((cmd.getName().equalsIgnoreCase("plugins")) || (cmd.getName().equalsIgnoreCase("blockplugins")) || (cmd.getName().equalsIgnoreCase("pl")) || (cmd.getName().equalsIgnoreCase("?")) || (cmd.getName().equalsIgnoreCase("ver")) && ((sender instanceof Player))) {
					if(sender.hasPermission(configStrings.get("Permission"))) {
						if(configBools.get("CustomMessage")) {
							sender.sendMessage(prefix + (configStrings.get("CustomMessage").replace("{PLUGINS}", Bukkit.getPluginManager().getPlugins().toString())));
						}else {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
}
