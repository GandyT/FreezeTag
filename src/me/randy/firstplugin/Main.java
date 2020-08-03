package me.randy.firstplugin;

import org.bukkit.plugin.java.JavaPlugin;

import me.randy.firstplugin.listeners.JoinListener;

public final class Main extends JavaPlugin {
	
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
}
