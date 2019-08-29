
package blfngl.pay_respects.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import blfngl.pay_respects.PayRespects;
import blfngl.pay_respects.Ref;
import net.md_5.bungee.api.ChatColor;

public class PRCommandExecutor_FSet implements CommandExecutor
{
	private final PayRespects plugin;

	public PRCommandExecutor_FSet(PayRespects plugin)
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (args[1] != null)
		{
			if (args[0].toLowerCase().equals("death_msg"))
			{
				plugin.getConfig().set(Ref.config_death_msg, args[1]);
			}

			try {
				int amount = Integer.parseInt(args[1]);
				int amtOld = 0;

				if (args[0].toLowerCase().equals("window"))
				{
					amtOld = plugin.getConfig().getInt(Ref.config_respect_window);
					plugin.getConfig().set(Ref.config_respect_window, amount);
				}

				else if (args[0].toLowerCase().equals("payment"))
				{
					amtOld = plugin.getConfig().getInt(Ref.config_respect_payment);
					plugin.getConfig().set(Ref.config_respect_payment, amount);
				}
				
				else
				{
					sender.sendMessage(Ref.pr_header + "Invalid argument, try 'window' or 'payment'.");
					return false;
				}
				
				sender.sendMessage(Ref.pr_header + "Setting respect_" + args[0] + " to " +
						ChatColor.RED + amount + ChatColor.GREEN + " (was " + amtOld + ").");

				return true;

			} catch (NumberFormatException e) {
				sender.sendMessage(Ref.pr_header + "Invalid argument, try an integer, not a string!");
			}

			plugin.saveConfig();
		}
		
		else
		{
			sender.sendMessage(Ref.pr_header + "This command requires 2 arguments.");
		}

		return false;
	}
}
