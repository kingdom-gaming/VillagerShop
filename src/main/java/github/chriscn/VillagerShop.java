package github.chriscn;

import github.chriscn.listener.ShopRightClick;
import github.chriscn.util.InventoryGUI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class VillagerShop extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new ShopRightClick(this), this);
    }

    @Override
    public void onDisable() {

    }
}
