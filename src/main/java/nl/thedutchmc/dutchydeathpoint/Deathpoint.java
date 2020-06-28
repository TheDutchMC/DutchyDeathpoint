package nl.thedutchmc.dutchydeathpoint;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Deathpoint extends JavaPlugin {

	public static HashMap<UUID, Location> deathpoints = new HashMap<>();
	
	@Override
	public void onEnable() {
		
		Deathpoint plugin = this;
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				DeathpointStorage dS = new DeathpointStorage(plugin);
				dS.loadStorage();
			}
		}.runTaskLater(this, 5);

		Bukkit.getServer().getPluginManager().registerEvents(new PlayerDeathEventHandler(), this);
		
		getCommand("deathpoint").setExecutor(new CommandHandler());
		getCommand("dp").setExecutor(new CommandHandler());
		
	}
}
