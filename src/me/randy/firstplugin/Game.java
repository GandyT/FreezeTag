package me.randy.firstplugin;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class Game {
	private HashMap<String, Player> GamePlayers = new HashMap<String, Player>();
	public Game (Player[] players) {
		for (Player player : players) {
			GamePlayers.put(player.getName(), player);
		}
	}
	public void RemovePlayer (String Username) {
		GamePlayers.remove(Username);
	}
	public void Swap () {
		
	}
}
