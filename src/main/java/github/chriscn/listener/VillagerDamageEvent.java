package github.chriscn.listener;

import github.chriscn.VillagerShop;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.w3c.dom.Entity;

public class VillagerDamageEvent implements Listener {

    @EventHandler
    public void onVillageDamageEvent(EntityDamageEvent event) {
        if (VillagerShop.getFileConfig().getBoolean("prevent-damage-to-villagers")) {
            if (event.getEntity().getType().equals(EntityType.VILLAGER)) {
                event.setCancelled(true);
            }
        }
    }
}
