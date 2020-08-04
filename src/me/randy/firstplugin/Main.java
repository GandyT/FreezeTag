package me.randy.firstplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.randy.firstplugin.commands.End;
import me.randy.firstplugin.commands.Start;
import me.randy.firstplugin.listeners.DeathListener;
import me.randy.firstplugin.listeners.JoinListener;

public final class Main extends JavaPlugin {
	
	public Game CurrentGame = null;
	
	@Override
	public void onEnable () {
		/* COMMANDS */
		new Start(this);
		new End(this);
		
		/* EVENTS */
		new JoinListener(this);
		new DeathListener(this);
		
		System.out.println("Plugin is now enabled");
	}
	
	@Override
	public void onDisable () {
		// when plugin ends
		System.out.println("Plugin is now disabled");
	}
	
	public void Start () {
		if (this.CurrentGame != null) {
			return;
		}
		Bukkit.broadcastMessage(ChatColor.GREEN + "Game has begun!");
		Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]);
		CurrentGame = new Game(players, this);
		CurrentGame.Start();
		Bukkit.broadcastMessage(ChatColor.AQUA + "First Swap starting in 5 Minutes!");
	}
	public void End () {
		this.CurrentGame = null;
	} 
}
