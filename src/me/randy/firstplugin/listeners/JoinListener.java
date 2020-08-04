package me.randy.firstplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;

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
			Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has joined for the first time!");
		}
	}
}
