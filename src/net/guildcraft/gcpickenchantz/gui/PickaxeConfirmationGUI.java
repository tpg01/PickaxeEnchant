package net.guildcraft.gcpickenchantz.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.guildcraft.currency.Currency;
import org.guildcraft.currency.utilities.CurrencyManager;

public class PickaxeConfirmationGUI extends PickaxeGUITemplate {

    private int cost;

    public PickaxeConfirmationGUI (int cost, ConfirmedAction action) {
        super(1);
        this.cost = cost;

        //Lime stained pane
        for (int column = 0; column < 4; column++) {

            setItem(column, createGuiItem(Material.STAINED_GLASS_PANE, (byte) 5,ChatColor.GREEN + "Yes", ""), player -> {
                if (Currency.getCurrency().getCurrencyManager().getTokens(player) > cost) {
                    Currency.getCurrency().getCurrencyManager().removeTokens(player, cost);
                    player.sendRawMessage(ChatColor.GREEN + "Purchased Successfully with " + String.valueOf(cost) + " tokens!");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    action.confirm();
                } else {

                    player.sendRawMessage(ChatColor.RED + "You do not have the required " + String.valueOf(cost) + " tokens!");
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);

                }

                delete();
            });

        }

        setItem(4, createGuiItem(Material.DIAMOND_PICKAXE, (byte) 0,ChatColor.GREEN + "Are you sure you want to upgrade?", ChatColor.WHITE + "Cost: " + cost + " tokens."));

        //Red stained pane
        for (int column = 5; column < 9; column++) {

            setItem(column, createGuiItem(Material.STAINED_GLASS_PANE, (byte) 14,ChatColor.RED + "No", ""), player -> {
                player.sendRawMessage(ChatColor.RED + "Purchase cancelled");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);
                delete();
            });

        }

    }

    public interface ConfirmedAction {
        void confirm();
    }

}
