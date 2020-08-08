package me.randy.freezetag.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.randy.freezetag.Main;

public class Start implements CommandExecutor {

	private Main plugin;
	
	public Start (Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("start").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		
		if (this.plugin.CurrentGame != null) return false;
		if (Sender.getName() != "Gandy_") return false;
		
		/* ACTION HERE */
		this.plugin.Start();
		
		return true;
	}
	
}
