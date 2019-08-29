
package blfngl.pay_respects.commands.x;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import blfngl.pay_respects.PayRespects;
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
		String[] responseList = {
				"Doubt",
				"Suspicion",
				"Be skeptical",
				"Hard to believe",
				"It seems the story you tell may be false",
				"the story you're currently speaking of seems illegitimate",
				"given the lack of credible evidence you are presenting i find it highly"
						+ "difficult to place any trust in the more than likely fabricated"
						+ "alibi you have given me"
		};

		Random r = new Random();
		String response;

		int i = r.nextInt(responseList.length - 1);
		response = responseList[i];

		return response;
	}
}
