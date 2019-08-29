
package blfngl.pay_respects.commands.f;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import blfngl.pay_respects.PayRespects;
import blfngl.pay_respects.Ref;
import net.md_5.bungee.api.ChatColor;

public class PRCommandExecutor_FHelp implements CommandExecutor
{
	private final PayRespects plugin;

	public PRCommandExecutor_FHelp(PayRespects plugin)
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		//if (cmd.getName().equalsIgnoreCase(Ref.f_info))
		{

			sender.sendMessage(ChatColor.YELLOW + " ---- " + ChatColor.GREEN + "PayRespects" + ChatColor.YELLOW + " ----");
			sender.sendMessage(ChatColor.GOLD + "Paying respects will pay a player who has recently died.");
			sender.sendMessage("");
			sender.sendMessage(ChatColor.GOLD + "/" + Ref.f_command + " to pay respects.");
			sender.sendMessage(ChatColor.GOLD + "The respect window is currently " + ChatColor.RED +
					plugin.getRespectWindow() + ChatColor.GOLD + " seconds.");
			sender.sendMessage(ChatColor.GOLD + "The respect payment is currently " + ChatColor.RED + "$" +
					plugin.getRespectPayment() + ChatColor.GOLD + ".");

			return true;
		}
	}
}
