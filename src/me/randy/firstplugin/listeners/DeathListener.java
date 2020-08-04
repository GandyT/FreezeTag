package me.randy.firstplugin.listeners;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.randy.firstplugin.Main;

public class DeathListener implements Listener {
	private static Main plugin;
	
	public DeathListener (Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void OnDeath (PlayerDeathEvent event) {
		Player player = event.getEntity();
		if (this.plugin.CurrentGame != null) {
			HashMap<String, Player> PlayerMap = this.plugin.CurrentGame.GetPlayers();
			int Remaining = PlayerMap.size() - 1;
			if (PlayerMap.get(player.getName()) != null) {
				this.plugin.CurrentGame.RemovePlayer(player.getName());
				PlayerMap.remove(player.getName());
				if (Remaining <= 1) {
					Player Winner = null;
					Iterator FinalPlayers = PlayerMap.entrySet().iterator();
					while (FinalPlayers.hasNext()) {
						Winner = (Player) FinalPlayers.next();
					}
					Bukkit.broadcastMessage("/title @a title {\"text\":\"" + Winner.getName() + " has won!\",\"color\":\"dark_red\"}");
					this.plugin.CurrentGame = null;
				}
				Bukkit.broadcastMessage("/gamemode 3 " + player.getName());
				Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " has died! " + Remaining + " players remaining!");
			}
		}
	}
}
