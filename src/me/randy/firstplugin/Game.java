package me.randy.firstplugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.ChatColor;

public class Game {
	// PRIVATE:
	private HashMap<String, Player> GamePlayers = new HashMap<String, Player>();
	private Main plugin;
	private GameLoop loop;
	private int count = 0;
	
	/* CONSTRUCTOR */
	public Game (Player[] players, Main plugin) {
		this.plugin = plugin;
		for (Player player : players) {
			GamePlayers.put(player.getName(), player);
		}
	}
	
	/* GAME LOOP */
	private class GameLoop extends BukkitRunnable {
		public void run () {
			if (plugin.CurrentGame == null) {
				Bukkit.broadcastMessage(ChatColor.DARK_RED + "No Instance of a game running");
				return;
			}
			count += 20;
			int TicksRemaining = 6000 - count;
			if (TicksRemaining <= 200 && TicksRemaining > 0) {
				Bukkit.broadcastMessage("Swapping in " + Integer.toString(TicksRemaining / 20));
			} else if (count >= 6000) {
				count = 0;
				Swap();
				Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Swapped!");
				Bukkit.broadcastMessage(ChatColor.DARK_RED + "5 minutes till the next swap!");
			}
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
			scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
				@Override
				public void run() {
					loop.run();
				}
			}, 20L);
		}
	}
	
	/* ACTIONS */
	public void Start () {
		this.loop = new GameLoop();
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                loop.run();
            }
        }, 20L);
	}
	public HashMap<String, Player> GetPlayers () {
		return this.GamePlayers;
	}
	
	public void RemovePlayer (String Username) {
		GamePlayers.remove(Username);
	}
	
	public void Swap () {
		// Variables
		Iterator PlayerIterator = GamePlayers.entrySet().iterator();
		Location PlayerLocation = null;
		Player firstPlayer = null;
		
		// Teleport Iterator
		while (PlayerIterator.hasNext()) {
			Map.Entry Element = (Map.Entry) PlayerIterator.next();
			Player IteratedPlayer = (Player) Element.getValue();
			
			Location NextLocation = IteratedPlayer.getLocation();
			
			if (PlayerLocation != null) {
				IteratedPlayer.teleport(PlayerLocation);
			} else {
				firstPlayer = (Player) IteratedPlayer;
			}
			
			PlayerLocation = NextLocation;
		}
		// Teleport first player to last player location
		firstPlayer.teleport(PlayerLocation);
	}
}
