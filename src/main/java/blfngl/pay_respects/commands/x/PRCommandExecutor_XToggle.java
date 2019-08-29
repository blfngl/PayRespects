
package blfngl.pay_respects.commands.x;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import blfngl.pay_respects.PayRespects;
import net.md_5.bungee.api.ChatColor;

public class PRCommandExecutor_XToggle implements CommandExecutor
{
	private final PayRespects plugin;

	public PRCommandExecutor_XToggle(PayRespects plugin)
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		plugin.toggleDoubt();
		return true;
	}
}
