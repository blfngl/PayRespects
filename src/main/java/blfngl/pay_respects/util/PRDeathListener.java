
package blfngl.pay_respects.util;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitTask;

import blfngl.pay_respects.PayRespects;
import blfngl.pay_respects.Ref;
import net.md_5.bungee.api.ChatColor;

public class PRDeathListener implements Listener
{
	private final PayRespects plugin;

	public PRDeathListener(PayRespects instance)
	{
		plugin = instance;
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		String deathMsg = event.getDeathMessage();
		String playerName = getNameFromDeathMessage(deathMsg);

		plugin.getServer().broadcastMessage(plugin.getHeader() + plugin.getConfig().getString(Ref.config_death_msg));

		if (plugin.isDebug())
		{
			plugin.getServer().broadcastMessage(plugin.getHeader() + "Debugging " + ChatColor.RED +
					"[Player]: " + playerName + ChatColor.GOLD + " [msg]: " + deathMsg);
		}

		// Update the map with the recent player death
		plugin.mapRecentDeaths.put(playerName, true);

		// Schedule removal from the map after x amount of time
		// 20 ticks per sec * X = X seconds irl
		BukkitTask task = new PRToggleRecentDeath(plugin, playerName).runTaskLater(plugin, 20 * plugin.getRespectWindow());
	}

	private String getNameFromDeathMessage(String msg)
	{
		String name = msg.substring(0, msg.indexOf(" "));
		return name;
	}
}
