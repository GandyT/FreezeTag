package me.randy.freezetag;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Game {
	
	// PUBLIC:
	public HashMap<String, Player>PlayerMap = new HashMap<String, Player>();
	public HashMap<String, Location>LocationMap = new HashMap<String, Location>();
	public String tagger = null;
	
	// PRIVATE:
	private Main plugin;
	
	
	public Game (Main plugin, Player[] players) {
		
		if (players.length <= 1) {
			Bukkit.broadcastMessage(ChatColor.DARK_RED + "There must be atleast 2 players to start the game");
			return;
		}
		
		this.plugin = plugin;
		
		// Store Players in HashMap
		for (Player player : players) {
			PlayerMap.put(player.getName(), player);
		}
		
		// Random Tagger
		int random = ThreadLocalRandom.current().nextInt() % PlayerMap.size();
		tagger = players[random].getName();
	}
}