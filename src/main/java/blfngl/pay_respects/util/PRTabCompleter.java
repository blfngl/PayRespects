
package blfngl.pay_respects.util;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import blfngl.pay_respects.Ref;

public class PRTabCompleter implements TabCompleter
{
	//public static PayRespects plugin = PayRespects.plugin;

	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
	{
		if (command.getName().equalsIgnoreCase(Ref.f_command) && args.length > 0)
		{
			if (sender instanceof Player)
			{
				Player player = (Player) sender;

				//List<String> list = (List<String>) plugin.get
			}
		}

		return null;
	}

}
