
package blfngl.pay_respects.util;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import blfngl.pay_respects.PayRespects;
import blfngl.pay_respects.Ref;

public class PRToggleRecentDeath extends BukkitRunnable
{
	private final PayRespects plugin;
	private String playerName;

	public PRToggleRecentDeath(PayRespects plugin, String playerName)
	{
		this.plugin = plugin;
		this.playerName = playerName;
	}

	public void run()
	{
		// Remove player from map
		plugin.mapRecentDeaths.remove(playerName);
		// Announce window is over
		plugin.getServer().broadcastMessage(Ref.pr_header + "Respects for " + ChatColor.RED + playerName +
				ChatColor.GOLD + " have been paid.");
	}
}
