package me.itsjbey.blockplugins;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.itsjbey.blockplugins.events.EVENT_BlockBreak;
import me.itsjbey.blockplugins.events.EVENT_BlockPlace;
import me.itsjbey.blockplugins.events.EVENT_Interaction;
import me.itsjbey.blockplugins.events.EVENT_MobDamage;
import me.itsjbey.blockplugins.events.EVENT_MobSpawn;
import me.itsjbey.blockplugins.events.EVENT_PlayerCommand;
import me.itsjbey.blockplugins.events.EVENT_PlayerDamage;
import me.itsjbey.blockplugins.events.EVENT_PlayerEat;

public class Main extends JavaPlugin {

	String prefix;
	HashMap<String, String> configStrings = new HashMap<>();
	HashMap<String, Boolean> configBools = new HashMap<>();
	List<String> blockedCmds;
	String license;
	String newestVersion;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		loadConfig();
		if(configBools.get("StartMessageEnabled")) {
			System.out.println(" ");
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("██   ██ ██████  ██       ██████   ██████ ██   ██ ███████ ██████  ");
			System.out.println(" ██ ██  ██   ██ ██      ██    ██ ██      ██  ██  ██      ██   ██ ");
			System.out.println("  ███   ██████  ██      ██    ██ ██      █████   █████   ██████  ");
			System.out.println(" ██ ██  ██   ██ ██      ██    ██ ██      ██  ██  ██      ██   ██ ");
			System.out.println("██   ██ ██████  ███████  ██████   ██████ ██   ██ ███████ ██   ██ ");
			System.out.println("                                                                         ");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("             SUCCESSFULLY ACTIVATED XBLOCKER 1.0                         ");
			System.out.println("-------------------------------------------------------------------------");
			System.out.println(" ");
			/*if(isUpdateAvailable()) {
				System.out.println("       NEW VERSION (" + newestVersion + ") IS AVAILABLE NOW!!!");
			}*/
			System.out.println(" ");
			System.out.println(" ");
		}
		String enabled = "";
		if(getConfig().getBoolean("Partenable.CommandBlocking")) {
			Bukkit.getPluginManager().registerEvents(new EVENT_PlayerCommand(prefix, configStrings, configBools, blockedCmds), this);
			if(enabled == "") {
				enabled = "CommandBlocking";
			}else {
				enabled = enabled + ", CommandBlocking";
			}
			if(getConfig().getBoolean("Enabled.CustomConsumables")) {
				enabled = enabled + "(C)";
			}
		}
		if(getConfig().getBoolean("Partenable.MobDamageBlocking")) {
			Bukkit.getPluginManager().registerEvents(new EVENT_MobDamage(this), this);
			if(enabled == "") {
				enabled = "MobDamageBlocking";
			}else {
				enabled = enabled + ", MobDamageBlocking";
			}
			if(getConfig().getBoolean("Enabled.CustomMobDamage")) {
				enabled = enabled + "(C)";
			}
		}
		if(getConfig().getBoolean("Partenable.ItemConsumeBlocking")) {
			Bukkit.getPluginManager().registerEvents(new EVENT_PlayerEat(this), this);
			if(enabled == "") {
				enabled = "ItemConsumeBlocking";
			}else {
				enabled = enabled + ", ItemConsumeBlocking";
			}
			if(getConfig().getBoolean("Enabled.CustomConsumables")) {
				enabled = enabled + "(C)";
			}
		}
		if(getConfig().getBoolean("Partenable.PlayerDamageBlocking")) {
			Bukkit.getPluginManager().registerEvents(new EVENT_PlayerDamage(), this);
			if(enabled == "") {
				enabled = "PlayerDamageBlocking";
			}else {
				enabled = enabled + ", PlayerDamageBlocking";
			}
		}
		if(getConfig().getBoolean("Partenable.BlockBreakBlocking")) {
			Bukkit.getPluginManager().registerEvents(new EVENT_BlockBreak(this), this);
			if(enabled == "") {
				enabled = "BlockBreakBlocking";
			}else {
				enabled = enabled + ", BlockBreakBlocking";
			}
			if(getConfig().getBoolean("Enabled.CustomBreakableBlocks")) {
				enabled = enabled + "(C)";
			}
		}
		if(getConfig().getBoolean("Partenable.BlockPlaceBlocking")) {
			Bukkit.getPluginManager().registerEvents(new EVENT_BlockPlace(this), this);
			if(enabled == "") {
				enabled = "BlockPlaceBlocking";
			}else {
				enabled = enabled + ", BlockPlaceBlocking";
			}
			if(getConfig().getBoolean("Enabled.CustomPlaceableBlocks")) {
				enabled = enabled + "(C)";
			}
		}
		if(getConfig().getBoolean("Partenable.InteractionBlocking")) {
			Bukkit.getPluginManager().registerEvents(new EVENT_Interaction(this), this);
			if(enabled == "") {
				enabled = "InteractionBlocking";
			}else {
				enabled = enabled + ", InteractionBlocking";
			}
			if(getConfig().getBoolean("Enabled.CustomInteractables")) {
				enabled = enabled + "(C)";
			}
		}if(getConfig().getBoolean("MobSpawnBlocking")) {
			Bukkit.getPluginManager().registerEvents(new EVENT_MobSpawn(), this);
			if(enabled == "") {
				enabled = "MobSpawnBlocking";
			}else {
				enabled = enabled + ", MobSpawnBlocking";
			}
		}
		System.out.println("Enabled Parts: " + enabled);
	}
	
/*	private String getNewestVersion(String url){
        try {
            URL uri = new URL(url);
            URLConnection con = uri.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            return body;
        }catch(Exception ex){}
 
        return getDescription().getVersion();
	} */
	
	private boolean isUpdateAvailable() {
		if(newestVersion == getDescription().getVersion()) {
			return false;
		}else {
			return true;
		}
	}

	private void loadConfig() {
	//	newestVersion = getNewestVersion("https://zeusapi.net/products-versions-strings/XBlocker/version.php");
		prefix = getConfig().getString("Messages.Look.Prefix");
		license = getConfig().getString("Licensing.Key");
		blockedCmds = getConfig().getStringList("BlockedCmds");
		configStrings.put("CustomUsage", getConfig().getString("Messages.Extra.CustomUsage"));
		configStrings.put("NoPermission", getConfig().getString("Messages.Errors.NoPermission"));
		configStrings.put("Permission", getConfig().getString("Permission.Use"));
		configBools.put("CommandEnabled", getConfig().getBoolean("Partenable.CommandBlocking"));
		configBools.put("CustomMessageEnabled", getConfig().getBoolean("Enabled.CustomMessage"));
		configBools.put("StartMessageEnabled", getConfig().getBoolean("Enabled.ConsoleStartMessage"));
	}
	

}
