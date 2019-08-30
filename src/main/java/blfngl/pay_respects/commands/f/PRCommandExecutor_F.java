
package blfngl.pay_respects.commands.f;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blfngl.pay_respects.PayRespects;
import blfngl.pay_respects.Ref;
import net.md_5.bungee.api.ChatColor;

public class PRCommandExecutor_F implements CommandExecutor
{
	private final PayRespects plugin;

	public PRCommandExecutor_F(PayRespects plugin)
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String respectPayer;

		if (sender instanceof Player)
			respectPayer = ChatColor.RED + sender.getName();
		else
			respectPayer = ChatColor.RED + "The Server";

		if (args.length == 0)
		{			
			plugin.getServer().broadcastMessage(plugin.getHeader() + respectPayer +
					ChatColor.GOLD + " paid their respects.");
			payDeceased();
		}

		else if (args.length == 1)
		{
			plugin.getServer().broadcastMessage(plugin.getHeader() + respectPayer + ChatColor.GOLD +
					" paid their respects to " + ChatColor.RED + args[0] + ChatColor.GOLD + ".");
			payDeceased(args[0]);
		}

		return true;
	}
	
	private void payDeceased()
	{
		
	}
	
	private void payDeceased(String player)
	{
		
	}
}
