package nl.thedutchmc.dutchydeathpoint;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DeathpointStorage {

	private Deathpoint plugin;
	
	public DeathpointStorage(Deathpoint plugin) {
		this.plugin = plugin;
	}
	
	private static File storageFile;
	private static FileConfiguration storage;
	
	public static FileConfiguration getStorage() {
		return storage;
	}
	
	public void loadStorage() {
		storageFile = new File(plugin.getDataFolder(), "storage.yml");
		
		if(!storageFile.exists()) {
			storageFile.getParentFile().mkdirs();
			plugin.saveResource("storage.yml", false);
		}
		
		storage = new YamlConfiguration();
		
		try {
			storage.load(storageFile);
			readStorage();
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void readStorage() {
		List<String> uuidWithDeathpoints = storage.getStringList("deathpoints");
				
		for(String str : uuidWithDeathpoints) {
			String[] strParts = str.split(":");
			
			Deathpoint.deathpoints.put(UUID.fromString(strParts[0]), new Location(Bukkit.getWorld(strParts[4]), Double.valueOf(strParts[1]), Double.valueOf(strParts[2]), Double.valueOf(strParts[3])));
		}
	}
	
	public static void writeDeathpoint(UUID uuid, Location loc) {
		
		List<String> deathpoints = storage.getStringList("deathpoints");
		String deathpoint = uuid.toString() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ() + ":" + loc.getWorld().getName();
		
		boolean alreadyInList = false;
		int inListIndex = 0;
		for(int i = 0; i < deathpoints.size(); i++) {
			UUID deathpointUuid = UUID.fromString(deathpoints.get(i).split(":")[0]);
			
			if(deathpointUuid.equals(uuid)) {
				alreadyInList = true;
				inListIndex = i;
			}
		}
		
		if(alreadyInList) {
			deathpoints.set(inListIndex, deathpoint);
		} else {
			deathpoints.add(deathpoint);
		}
		
		storage.set("deathpoints", deathpoints);

		try {
			storage.save(storageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
