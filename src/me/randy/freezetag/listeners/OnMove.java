package me.randy.freezetag.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.randy.freezetag.Main;

public class OnMove implements Listener {
	
	private Main plugin;
	public OnMove (Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void PlayerMove (PlayerMoveEvent Event) {
		
		if (this.plugin.CurrentGame == null) return;
		
		Player player = Event.getPlayer();
		
		if (this.plugin.CurrentGame.LocationMap.get(player.getName()) != null) {
			// If Player is Frozen, Teleport them back to the last saved position
			// Position is saved every time someone is hit by the tagger.
			player.teleport(this.plugin.CurrentGame.LocationMap.get(player.getName()));
		}
		
	}
}
