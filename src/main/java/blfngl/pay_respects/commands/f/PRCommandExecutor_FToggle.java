
package blfngl.pay_respects.commands.f;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blfngl.pay_respects.PayRespects;
import blfngl.pay_respects.Ref;
import net.md_5.bungee.api.ChatColor;

public class PRCommandExecutor_FToggle implements CommandExecutor
{
	private final PayRespects plugin;

	public PRCommandExecutor_FToggle(PayRespects plugin)
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (plugin.isDebug())
		{
			String msg = "" + plugin.getHeader() + "Toggle headers";
			plugin.getLogger().info(msg);
			sender.sendMessage(msg);
		}

		plugin.toggleHeader(sender);		
		return true;
	}
}
