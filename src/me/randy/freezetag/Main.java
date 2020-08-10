package me.randy.freezetag;

import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.randy.freezetag.commands.End;
import me.randy.freezetag.commands.Start;
import me.randy.freezetag.listeners.OnHit;
import me.randy.freezetag.listeners.OnMove;

public class Main extends JavaPlugin {
	
	public Game CurrentGame = null;
	
	@Override
	public void onEnable() {
		/* COMMANDS */
		new Start(this);
		new End(this);
		/* EVENTS */
		new OnMove(this);
		new OnHit(this);
	}
	
	
	/* ACTIONS */
	public void Start () {
		this.CurrentGame = new Game(
				this, 
				Bukkit.getServer().getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]) // Converting Collection to Array
			);
		if (this.CurrentGame.tagger == null) {
			this.CurrentGame = null;
			return;
		};
		
		this.CurrentGame.loop();
		
		Bukkit.broadcastMessage(ChatColor.AQUA + this.CurrentGame.tagger + " is IT! RUN RUN RUN!");
	}
	
	public void End () {
		this.CurrentGame = null;
		Bukkit.broadcastMessage(ChatColor.AQUA + "Forcibly Ended Game.");
	}
	
}
