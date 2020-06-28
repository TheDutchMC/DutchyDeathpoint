package nl.thedutchmc.dutchydeathpoint;

import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class CommandHandler implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equals("deathpoint") || command.getName().equals("dp")) {
			
			Player senderP = (Player) sender;
			
			if(!Deathpoint.deathpoints.containsKey(senderP.getUniqueId())) {
				sender.sendMessage(ChatColor.GOLD + "You haven't died yet, so there's no deathpoint!");
				return true;
			}
			
			Location deathpoint = Deathpoint.deathpoints.get(senderP.getUniqueId());
			
			System.out.println(deathpoint);
			
			String world = (deathpoint.getWorld().getEnvironment().equals(Environment.NORMAL)) ? "Overworld" : deathpoint.getWorld().getEnvironment().toString();
			
			ChatColor cr = ChatColor.RED;
			ChatColor cg = ChatColor.GOLD;
			
			sender.sendMessage(cg + "Your latest deathpoint is at X: " + cr + (int) deathpoint.getX() + cg + " Y: " + cr + (int) deathpoint.getY() + cg + " Z: " + cr + (int) deathpoint.getZ() + cg + " in the " + cr + world);
			
			return true;
		}
		
		return false;
	}

}
