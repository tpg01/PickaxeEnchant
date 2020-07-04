package net.guildcraft.gcpickenchantz.listener;

import net.guildcraft.gcpickenchantz.gui.PickaxeGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PickaxeListener implements Listener {

    private PickaxeGUI pickaxeGUI;

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {

        //Is right click event? If not return.
        if (!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
        //Is shift pressed? If not return.
        if (!e.getPlayer().isSneaking()) return;
        //Is holding pickaxe? If not return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE)) return;

        PickaxeGUI gui = new PickaxeGUI(e.getPlayer().getInventory().getItemInMainHand());
        gui.open(e.getPlayer());

    }

}
