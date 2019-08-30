
package blfngl.pay_respects;

import net.md_5.bungee.api.ChatColor;

public class Ref
{
	public static final String text_header_on = ChatColor.GREEN + "[PayRespects] " + ChatColor.GOLD;
	public static final String text_header_off = "" + ChatColor.GOLD;

	public static final String f_command = "f";
	public static final String f_help = "fhelp";
	public static final String f_debug = "fdebug";
	public static final String f_set = "fset";
	public static final String f_toggle_header = "ftoggleheader";

	public static final String x_command = "x";
	public static final String x_toggle = "xtoggle";

	public static final int respect_window = 15;
	public static final int respect_payment = 5;
	public static final String death_msg = "Press F to pay respects.";

	public static final String config_respect_window = "respect_window";
	public static final String config_respect_payment = "respect_payment";
	public static final String config_death_msg = "death_message";
	public static final String config_display_header = "header_on";
	public static final String config_only_doubt = "only_doubt";

	public static final String[] doubtResponseList = {
			"Doubt",
			"Suspicion",
			"Be skeptical",
			"Hard to believe",
			"It seems the story you tell may be false",
			"the story you're currently speaking of seems illegitimate",
			"given the lack of credible evidence you are presenting i find it highly"
					+ "difficult to place any trust in the more than likely fabricated"
					+ "alibi you have given me",
			"You're lying Morgan!"
	};
}
