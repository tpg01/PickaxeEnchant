package net.guildcraft.gcpickenchantz.listener;

import net.guildcraft.gcpickenchantz.GCPickEnchantz;
import net.guildcraft.gcpickenchantz.gui.PickaxeGUI;
import net.guildcraft.gcpickenchantz.gui.PickaxeGUITemplate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PickaxeGUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player)) return;


        Player player = (Player) e.getWhoClicked();

        UUID playerUUID = player.getUniqueId();
        UUID inventoryUUID = PickaxeGUI.openInventories.get(playerUUID);



        if (inventoryUUID != null) {

            e.setCancelled(true);
            PickaxeGUITemplate gui = PickaxeGUITemplate.getInventoriesByUUID().get(inventoryUUID);
            PickaxeGUI.PickaxeGUIAction action = gui.getActions().get(e.getSlot());

            if(e.getClickedInventory() != player.getOpenInventory().getTopInventory()) return;

            if (action != null) {
                action.click(player);

            }

        }

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        UUID playerUUID = player.getUniqueId();
        PickaxeGUI.openInventories.remove(playerUUID);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = (Player) e.getPlayer();
        UUID playerUUID = player.getUniqueId();
        PickaxeGUI.openInventories.remove(playerUUID);
    }
}
