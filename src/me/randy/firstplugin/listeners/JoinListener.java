package me.randy.firstplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.randy.firstplugin.Main;

public class JoinListener implements Listener {
	private static Main plugin;
	
	public JoinListener (Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void OnJoin (PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPlayedBefore()) {
			Bukkit.broadcastMessage("/title @a title {\"text\":\"" + player.getName() + " has joined!\",\"color\":\"dark_red\"}");
		}
	}
}
