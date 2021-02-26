package github.chriscn.listener;

import github.chriscn.VillagerShop;
import github.chriscn.util.InventoryGUI;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ShopRightClick implements Listener {
    HashMap<Material, Integer> food = new HashMap<>();

    public ShopRightClick() {
        food.put(Material.CARROT, 3);
        food.put(Material.BAKED_POTATO, 5);
        food.put(Material.COOKED_PORKCHOP, 7);
        food.put(Material.COOKED_BEEF, 9);
    }

    @EventHandler
    public void onPlayerRightClickEvent(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.VILLAGER) {
            Player player = event.getPlayer();
            //player.sendMessage(ChatColor.GREEN + "You right clicked a villager");
            InventoryGUI gui = new InventoryGUI("Villager Shop", 3, (clicker, menu, row, slot, item) -> {
                if (item != null) {
                    if (food.containsKey(item.getType())) {
                        int price = food.get(item.getType());
                        EconomyResponse r = VillagerShop.getEconomy().withdrawPlayer(player, price);
                        if (r.transactionSuccess()) {
                            player.getInventory().addItem(new ItemStack(item.getType()));
                            player.sendMessage(ChatColor.GREEN + "You bought " + toDisplayCase(item.getType().name().replaceAll("_", " ")) + " for" + price);
                        } else {
                            player.sendMessage(ChatColor.RED + "You don't have enough money.");
                        }
                    }
                    return false;
                } else return true; // keep inventory open if clicked elsewhere
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
