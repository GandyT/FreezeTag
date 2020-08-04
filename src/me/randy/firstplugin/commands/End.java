package me.randy.firstplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.randy.firstplugin.Main;

public class End implements CommandExecutor {
	private Main plugin;
	public End (Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("end").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd,String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("end")) {
			this.plugin.End();
			return true;
		}
		return false;
	}
	
	
}
