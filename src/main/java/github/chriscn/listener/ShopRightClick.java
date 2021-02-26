package github.chriscn.listener;

import github.chriscn.VillagerShop;
import github.chriscn.util.InventoryGUI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;

public class ShopRightClick implements Listener {
    HashMap<Material, BigDecimal> food = new HashMap<>();

    VillagerShop plugin;
    public ShopRightClick(VillagerShop instance) {
        this.plugin = instance;

        food.put(Material.CARROT, new BigDecimal(3.00));
        food.put(Material.BAKED_POTATO, new BigDecimal(5.00));
        food.put(Material.PORKCHOP, new BigDecimal(7.00));
        food.put(Material.COOKED_BEEF, new BigDecimal(9.00));
    }

    @EventHandler
    public void onPlayerRightClickEvent(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.VILLAGER) {
            Player player = event.getPlayer();
            //player.sendMessage(ChatColor.GREEN + "You right clicked a villager");
            InventoryGUI gui = new InventoryGUI("Villager Shop", 3, (clicker, menu, row, slot, item) -> {
                return true;
            });

            int i = 1;
            for (Material m : food.keySet()) {
                ItemStack im = new ItemStack(m);

                gui.setSlot(gui.getRow(1), i, im, toDisplayCase(m.name().replaceAll("_", " ")), food.get(m).toString());
                i += 2;
            }
            gui.open(player);
        }
    }

    public static String toDisplayCase(String s) {

        final String ACTIONABLE_DELIMITERS = " '-/"; // these cause the character following
        // to be capitalized

        StringBuilder sb = new StringBuilder();
        boolean capNext = true;

        for (char c : s.toCharArray()) {
            c = (capNext)
                    ? Character.toUpperCase(c)
                    : Character.toLowerCase(c);
            sb.append(c);
            capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit cast not needed
        }
        return sb.toString();
    }
}
