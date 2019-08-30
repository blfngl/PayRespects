
package blfngl.pay_respects.commands.x;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import blfngl.pay_respects.PayRespects;
import blfngl.pay_respects.Ref;
import net.md_5.bungee.api.ChatColor;

public class PRCommandExecutor_X implements CommandExecutor
{
	private final PayRespects plugin;

	public PRCommandExecutor_X(PayRespects plugin)
	{
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		sender.sendMessage(ChatColor.GOLD + getResponse());
		return true;
	}

	private String getResponse()
	{
		Random r = new Random();
		String response;
		int i;

		if (plugin.onlyDoubt())
			i = 0;
		else
			i = r.nextInt(Ref.doubtResponseList.length - 1);

		response = plugin.getHeader() + Ref.doubtResponseList[i];

		return response;
	}
}
