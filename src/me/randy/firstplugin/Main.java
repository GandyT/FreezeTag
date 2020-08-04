package me.randy.firstplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.randy.firstplugin.listeners.JoinListener;

import java.util.concurrent.ThreadLocalRandom;

public final class Main extends JavaPlugin {
	
	public Game CurrentGame = null;
	@Override
	public void onEnable () {
		/* COMMANDS */
		
		/* EVENTS */
		new JoinListener(this);
		
		System.out.println("Plugin is now enabled");
	}
	
	@Override
	public void onDisable () {
		// when plugin ends
		System.out.println("Plugin is now disabled");
	}
	
	private void setTimeout(Runnable runnable, int delay) {
		try {
	        Thread.sleep(delay);
	        runnable.run();
	    }
	    catch (Exception e){
	        System.err.println(e);
	    }
	}
	
	private void GameLoop () {
		int int_random = ThreadLocalRandom.current().nextInt() % 8;
		if (int_random == 0) int_random = 5;
		this.CurrentGame.Swap();
		Bukkit.broadcastMessage("/title @a title {\"text\":\"" + int_random + " minutes!\",\"color\":\"dark_red\"}");
		setTimeout(() -> this.GameLoop(), 1000 * 60 * int_random);
	}
	
	public void Start () {
		if (this.CurrentGame != null) {
			return;
		}
		Bukkit.broadcastMessage("/title @a title {\"text\":\"Start!\",\"color\":\"dark_red\"}");
		Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]);
		CurrentGame = new Game(players);
	}
}
