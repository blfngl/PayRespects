
package blfngl.pay_respects;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import blfngl.pay_respects.commands.f.PRCommandExecutor_F;
import blfngl.pay_respects.commands.f.PRCommandExecutor_FDebug;
import blfngl.pay_respects.commands.f.PRCommandExecutor_FHelp;
import blfngl.pay_respects.commands.f.PRCommandExecutor_FSet;
import blfngl.pay_respects.commands.f.PRCommandExecutor_FToggle;
import blfngl.pay_respects.commands.x.PRCommandExecutor_X;
import blfngl.pay_respects.commands.x.PRCommandExecutor_XToggle;
import blfngl.pay_respects.util.PRDeathListener;
import net.milkbowl.vault.economy.Economy;

public final class PayRespects extends JavaPlugin implements Listener
{
	// This map keeps track of whether or not a player has died recently.
	public HashMap<String, Boolean> mapRecentDeaths;

	// This map counts how many respects have been paid for a specific player.
	public HashMap<String, Integer> mapRespectCount;

	// Config file
	private FileConfiguration config = null;

	// Death event listener
	private final PRDeathListener deathListener = new PRDeathListener(this);

	// Vault economy stuff
	private static Economy econ = null;

	// Logger
	private static final Logger log = Logger.getLogger("Minecraft");

	// Debug flag
	private boolean debug = false;
	// Header flag
	private boolean displayHeader;
	// Doubt flag
	private boolean onlyDoubt;

	// Default header
	private String textHeader = Ref.text_header_off;

	/**
	 * Called on server close
	 */
	@Override
	public void onDisable()
	{ 
		log.info("PayRespects closing.");
	}

	/**
	 * Called on server boot
	 */
	@Override
	public void onEnable()
	{
		log.info("PayRespects loading...put an f in the chat.");

		// Check to ensure an economy system (via Vault) exists
		if (!checkVaultEconomy())
		{
			log.severe(String.format("Vault is a dependency of PayRespects.", getDescription().getName()));
			System.out.println("Vault is a dependency of PayRespects.");
		}

		else
			log.info("Vault hooked!");

		// Register commands
		getCommand(Ref.f_command).setExecutor(new PRCommandExecutor_F(this));
		getCommand(Ref.f_help).setExecutor(new PRCommandExecutor_FHelp(this));
		getCommand(Ref.f_debug).setExecutor(new PRCommandExecutor_FDebug(this));
		getCommand(Ref.f_set).setExecutor(new PRCommandExecutor_FSet(this));
		getCommand(Ref.f_toggle).setExecutor(new PRCommandExecutor_FToggle(this));

		getCommand(Ref.x_command).setExecutor(new PRCommandExecutor_X(this));
		getCommand(Ref.x_toggle).setExecutor(new PRCommandExecutor_XToggle(this));

		// Register events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(deathListener, this);

		// init map of recent player deaths
		mapRecentDeaths = new HashMap<String, Boolean>();

		// init map holding respect count
		mapRespectCount = new HashMap<String, Integer>();

		// Add autocomplete once finished
		//getCommand(Reference.command_f).setTabCompleter(new PayRespectsTabCompleter());

		// Sets up the config file
		initConfig();
		
		displayHeader = config.getBoolean(Ref.config_display_header);
		onlyDoubt = config.getBoolean(Ref.config_only_doubt);
		
		textHeader = displayHeader ? Ref.text_header_on : Ref.text_header_off;

		log.info("PayRespects loaded!");
	}

	/**
	 * Checks to ensure the Vault plugin is installed and an economy system exists.
	 * @return True if vault + economy exist, false otherwise.
	 */
	private boolean checkVaultEconomy()
	{
		if (getServer().getPluginManager().getPlugin("Vault") == null)
			return false;

		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);

		if (rsp == null)
			return false;

		econ = rsp.getProvider();
		return econ != null;
	}

	private void initConfig()
	{
		// Config file defaults
		config = getConfig();

		config.addDefault(Ref.config_respect_window, Ref.respect_window);
		config.addDefault(Ref.config_respect_payment, Ref.respect_payment);
		config.addDefault(Ref.config_death_msg, Ref.death_msg);
		config.addDefault(Ref.config_display_header, false);
		config.addDefault(Ref.config_only_doubt, true);
		config.options().copyDefaults(true);

		// Save config file
		saveDefaultConfig();
	}

	// Getters
	public int getRespectWindow()
	{
		return config.getInt(Ref.config_respect_window);
	}

	public int getRespectPayment()
	{
		return config.getInt(Ref.config_respect_payment);
	}

	public boolean isDebug()
	{
		return debug;
	}

	public boolean toggleDebug()
	{
		debug = !debug;
		return debug;
	}

	public String getHeader()
	{
		return textHeader;
	}

	public void toggleHeader(CommandSender sender)
	{
		// Toggle
		displayHeader = !displayHeader;

		// Set header
		textHeader = displayHeader ? Ref.text_header_on : Ref.text_header_off;
		config.set(Ref.config_display_header, displayHeader);
		saveConfig();

		// Print
		String word = displayHeader ? "on" : "off";
		sender.sendMessage(textHeader + "Headers toggled " + word);
	}

	public boolean onlyDoubt()
	{
		return onlyDoubt;
	}

	public boolean toggleDoubt(CommandSender sender)
	{
		onlyDoubt = !onlyDoubt;
		
		config.set(Ref.config_only_doubt, onlyDoubt);
		saveConfig();
		
		String word = onlyDoubt ? "on" : "off";
		sender.sendMessage(textHeader + "Doubt mode toggled " + word);
		
		return onlyDoubt;
	}
}
