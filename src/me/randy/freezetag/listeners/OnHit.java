package me.randy.freezetag.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.randy.freezetag.Main;

public class OnHit implements Listener {
	// PRIVATE:
	private Main plugin;
	
	public OnHit (Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void PlayerHit (EntityDamageByEntityEvent event) {
		
		if (this.plugin.CurrentGame == null) return;
		
		Player Receiver = (Player) event.getEntity();
		Player Damager = (Player) event.getDamager();
		
		if (
				this.plugin.CurrentGame.tagger == Damager.getName() && 
				this.plugin.CurrentGame.LocationMap.get(Receiver.getName()) == null
		) {
			// Got Hit by Tagger
			this.plugin.CurrentGame.LocationMap.put(Receiver.getName(), Receiver.getLocation());
			Bukkit.broadcastMessage(ChatColor.AQUA + Receiver.getName() + " has been tagged!");
			
			if (this.plugin.CurrentGame.PlayerMap.size() - 1 == this.plugin.CurrentGame.LocationMap.size()) {
				// Game End. Everyone has been frozen!
				Bukkit.broadcastMessage(ChatColor.GREEN + "Game Over! The Tagger Wins!");
				this.plugin.CurrentGame = null;
			}
			
			return;
		}
		
		if (
				this.plugin.CurrentGame.tagger != Damager.getName() && 
				this.plugin.CurrentGame.LocationMap.get(Receiver.getName()) != null
		) {
			// Got Unfrozen
			this.plugin.CurrentGame.LocationMap.remove(Receiver.getName());
			Bukkit.broadcastMessage(ChatColor.AQUA + Receiver.getName() + "has been unfrozen by " + Damager.getName());
			return;
		}
		
	}
}
