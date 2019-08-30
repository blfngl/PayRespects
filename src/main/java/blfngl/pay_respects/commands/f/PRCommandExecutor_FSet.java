
package blfngl.pay_respects.commands.f;

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
		if (args.length >= 2)
		{
			if (args[0].toLowerCase().equals("death_msg"))
			{
				String message = "";
				
				// Start the loop at i = 1, the first index of the desired death message
				for (int i = 1; i < args.length; i++)
					message += args[i] + " ";
				
				plugin.getConfig().set(Ref.config_death_msg, message);
				
				if (plugin.isDebug())
					sender.sendMessage(plugin.getHeader() + "Changed death message to: " + message);
				
				return true;
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
					sender.sendMessage(plugin.getHeader() + "Invalid argument, try 'window' or 'payment'.");
					return false;
				}
				
				sender.sendMessage(plugin.getHeader() + "Setting respect_" + args[0] + " to " +
						ChatColor.RED + amount + ChatColor.GREEN + " (was " + amtOld + ").");

				return true;

			} catch (NumberFormatException e) {
				plugin.getLogger().warning("Invalid argument, try an integer, not a string!");
				sender.sendMessage(plugin.getHeader() + "Invalid argument, try an integer, not a string!");
			}

			plugin.saveConfig();
		}
		
		else
			sender.sendMessage(plugin.getHeader() + "This command requires at least 2 arguments.");

		return false;
	}
}
