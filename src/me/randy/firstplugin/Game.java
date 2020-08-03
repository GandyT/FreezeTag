package me.randy.firstplugin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Location;
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
		Iterator PlayerIterator = GamePlayers.entrySet().iterator();
		Location PlayerLocation = null;
		Player firstPlayer = null;
		while (PlayerIterator.hasNext()) {
			Map.Entry Element = (Map.Entry) PlayerIterator.next();
			Player IteratedPlayer = (Player) Element.getValue();
			if (PlayerLocation != null) {
				IteratedPlayer.teleport(PlayerLocation);
			} else {
				firstPlayer = IteratedPlayer;
			}
			PlayerLocation = (Location) IteratedPlayer.getLocation();
		}
		firstPlayer.teleport(PlayerLocation);
	}
}
