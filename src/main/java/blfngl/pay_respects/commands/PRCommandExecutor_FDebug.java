
package blfngl.pay_respects.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blfngl.pay_respects.PayRespects;
import blfngl.pay_respects.Ref;
import net.md_5.bungee.api.ChatColor;

public class PRCommandExecutor_FDebug implements CommandExecutor
{
	private final PayRespects plugin;

	public PRCommandExecutor_FDebug(PayRespects plugin)
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String msg = plugin.getHeader() + ChatColor.RED + "Debug mode " + (plugin.toggleDebug() ? "on." : "off.");
		sender.sendMessage(msg);
		return true;
	}
}
