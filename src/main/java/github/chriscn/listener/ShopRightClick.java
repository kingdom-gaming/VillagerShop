package github.chriscn.listener;

import github.chriscn.VillagerShop;
import github.chriscn.util.InventoryGUI;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ShopRightClick implements Listener {
    VillagerShop plugin;
    public ShopRightClick(VillagerShop instance) {
        this.plugin = instance;
    }

    @EventHandler
    public void onPlayerRightClickEvent(PlayerInteractAtEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (entity instanceof Villager) {
            Player player = event.getPlayer();
            InventoryGUI shop = new InventoryGUI("&aShop", 6, ((clicker, menu, row, slot, item) -> {
                if (item.getType().equals(Material.CARROT)) {
                    player.getInventory().addItem(new ItemStack(Material.CARROT));
                }
                return true;
            }));
            shop.setSlot(shop.getRow(1), 4, new ItemStack(Material.CARROT), "&aThe tastiest carrot", "&7$9.00");
        }
    }
}
