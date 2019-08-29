
package blfngl.pay_respects;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import blfngl.pay_respects.commands.PRCommandExecutor_F;
import blfngl.pay_respects.commands.PRCommandExecutor_FDebug;
import blfngl.pay_respects.commands.PRCommandExecutor_FInfo;
import blfngl.pay_respects.commands.PRCommandExecutor_FSet;
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
		getCommand(Ref.f_info).setExecutor(new PRCommandExecutor_FInfo(this));
		getCommand(Ref.f_debug).setExecutor(new PRCommandExecutor_FDebug(this));
		getCommand(Ref.f_set).setExecutor(new PRCommandExecutor_FSet(this));

		// test
		
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
}
