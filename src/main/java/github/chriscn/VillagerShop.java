package github.chriscn;

import github.chriscn.listener.ShopRightClick;
import github.chriscn.listener.VillagerDamageEvent;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.Configuration;
import java.util.logging.Logger;

public class VillagerShop extends JavaPlugin implements CommandExecutor {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static FileConfiguration config;
    private static Economy econ = null;

    @Override
    public void onEnable() {
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Bukkit.getPluginManager().registerEvents(new ShopRightClick(), this);
        Bukkit.getPluginManager().registerEvents(new VillagerDamageEvent(), this);
        config = this.getConfig();

        config.options().copyDefaults(true);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static FileConfiguration getFileConfig() {
        return config;
    }

    public static Logger getLog() {
        return log;
    }
}
