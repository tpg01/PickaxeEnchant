package net.guildcraft.gcpickenchantz.gui;

import net.guildcraft.gcpickenchantz.GCPickEnchantz;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.guildcraft.currency.Currency;
import org.guildcraft.currency.utilities.CurrencyManager;

public class PickaxeConfirmationGUI extends PickaxeGUITemplate {

    private int cost;

    public PickaxeConfirmationGUI (int cost, ConfirmedAction action) {
        super(1, ChatColor.translateAlternateColorCodes('&', "&f&nConfirmation Manager"));
        this.cost = cost;

        //Lime stained pane
        for (int column = 0; column < 4; column++) {

            setItem(column, createGuiItem(Material.STAINED_GLASS_PANE, (byte) 5, ChatColor.translateAlternateColorCodes('&', "&a&nYes"), ""), player -> {
                if (Currency.getCurrency().getCurrencyManager().getTokens(player) > cost) {
                    Currency.getCurrency().getCurrencyManager().removeTokens(player, cost);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            GCPickEnchantz.getInstance().getLang().getString("prefix") +
                            GCPickEnchantz.getInstance().getLang().getString("purchase-complete").replace("%cost%", String.valueOf(cost))));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    action.confirm();
                } else {

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            GCPickEnchantz.getInstance().getLang().getString("prefix") +
                                    GCPickEnchantz.getInstance().getLang().getString("purchase-failure-cost").replace("%cost%", String.valueOf(cost))));
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);

                }

                delete();
            });

        }
        setItem(4, createGuiItem(Material.DIAMOND_PICKAXE, (byte) 0,
                ChatColor.translateAlternateColorCodes('&', "&a&nAre you sure?"),
                "",
                ChatColor.translateAlternateColorCodes('&', "&b&l● &7Cost: &b" + cost + " &7tokens"),
                ChatColor.translateAlternateColorCodes('&', "&b&l● &7Upgrade: &b%Enchant% &8[&a+1&8]"),
                "",
                ChatColor.translateAlternateColorCodes('&', "&c&lWarning: &fAll upgrades are irreversible, once completing"),
                ChatColor.translateAlternateColorCodes('&', "&fthis action there is no way of getting a refund."),
                "",
                ChatColor.translateAlternateColorCodes('&', "&b&l→ &7Click yes to complete this purchase.")));

        //Red stained pane
        for (int column = 5; column < 9; column++) {

            setItem(column, createGuiItem(Material.STAINED_GLASS_PANE, (byte) 14, ChatColor.translateAlternateColorCodes('&', "&c&nNo"), ""), player -> {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        GCPickEnchantz.getInstance().getLang().getString("prefix") +
                                GCPickEnchantz.getInstance().getLang().getString("purchase-cancelled")));
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);
                delete();
            });

        }

    }

    public interface ConfirmedAction {
        void confirm();
    }

}
