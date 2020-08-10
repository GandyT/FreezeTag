package me.randy.freezetag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

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
	
	public void loop () {
		if (this.plugin.CurrentGame == null) return;
		
		/* RANDOM EVENT TO SPICE THINGS UP */
		int random = ThreadLocalRandom.current().nextInt() % 5; // 5 Events
		Iterator PlayerIterator = PlayerMap.entrySet().iterator();
		PotionEffect Effect;
		switch (random) {
			case 0:
				Bukkit.broadcastMessage(ChatColor.AQUA + "Glow Event! 15 Seconds!");
				Effect = new PotionEffect(PotionEffectType.GLOWING, 15, 1);
				break;
			case 1:
				Bukkit.broadcastMessage(ChatColor.AQUA + "Speed Event! 15 Seconds!");
				Effect = new PotionEffect(PotionEffectType.SPEED, 15, 1);
				break;
			case 2:
				Bukkit.broadcastMessage(ChatColor.AQUA + "Jump Event! 15 Seconds!");
				Effect = new PotionEffect(PotionEffectType.JUMP, 15, 1);
				break;
			case 3:
				Bukkit.broadcastMessage(ChatColor.AQUA + "Invisibility Event! 10 Seconds!");
				Effect = new PotionEffect(PotionEffectType.INVISIBILITY, 10, 1);
				break;
			case 4:
				Bukkit.broadcastMessage(ChatColor.AQUA + "Floating Event! 15 Seconds!");
				Effect = new PotionEffect(PotionEffectType.LEVITATION, 15, 1);
				break;
		}
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(this.plugin, new Runnable() {
			@Override
			public void run () {
				loop();
			}
 		}, 2400L);
		// 2400L is 2 Minutes
	}
}