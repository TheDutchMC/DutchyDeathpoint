package nl.thedutchmc.dutchydeathpoint;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathEventHandler implements Listener {
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		
		Deathpoint.deathpoints.put(event.getEntity().getUniqueId(), event.getEntity().getLocation());
		DeathpointStorage.writeDeathpoint(event.getEntity().getUniqueId(), event.getEntity().getLocation());
	}
}
