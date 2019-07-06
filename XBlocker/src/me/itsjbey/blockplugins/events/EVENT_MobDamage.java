package me.itsjbey.blockplugins.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class EVENT_MobDamage implements Listener{
	
	Boolean customMobsEnable;
	JavaPlugin jp;
	List<EntityType> blockedEntities = new ArrayList<>();
	
	public EVENT_MobDamage(JavaPlugin jp) {
		this.jp = jp;
		loadConfig();
	}
	
	@EventHandler
	public void MobDamageEvent(EntityDamageEvent e) {
		if(blockedEntities.contains(e.getEntityType())) {
			e.setCancelled(true);
		}
	}
	
	private void loadConfig() {
		customMobsEnable = jp.getConfig().getBoolean("Partenable.MobDamageBlocking");
		int ents = 0;
		if(customMobsEnable) {
			for (String e : jp.getConfig().getStringList("InvincibleEntities")) {
				for (EntityType et : EntityType.values()) {
					if(e.toUpperCase().equals(et.name())) {
						blockedEntities.add(et);
						ents++;
					}
				}
			}
			System.out.println("Added " + ents + " Entitie(s) to the invincibles list");
		}
	}
}
