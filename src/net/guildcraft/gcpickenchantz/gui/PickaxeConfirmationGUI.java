package net.guildcraft.gcpickenchantz.gui;

import net.guildcraft.gcpickenchantz.GCPickEnchantz;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.guildcraft.currency.Currency;
import org.guildcraft.currency.utilities.CurrencyManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PickaxeConfirmationGUI extends PickaxeGUITemplate {

    private int cost;
    private String enchanttype;
    private String upgrade;

    //Dates
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    Date date = new Date(System.currentTimeMillis());

    SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
    Date time = new Date(System.currentTimeMillis());

    public PickaxeConfirmationGUI (int cost, String enchanttype, String upgrade, ConfirmedAction action) {
        super(1, ChatColor.translateAlternateColorCodes('&', "&f&nConfirmation Manager"));
        this.cost = cost;
        this.enchanttype = enchanttype;
        this.upgrade = upgrade;

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
                    GCPickEnchantz.getInstance().getLogFile().set(formatter.format(date) + "." +player.getName() + "." +formatter2.format(time)
                            + ".Upgrade" +".Enchantment", enchanttype);
                    GCPickEnchantz.getInstance().getLogFile().set(formatter.format(date) + "." +player.getName() + "." +formatter2.format(time)
                            + ".Upgrade" +".New-Level", upgrade);
                    GCPickEnchantz.getInstance().getLogFile().set(formatter.format(date) + "." +player.getName() + "." +formatter2.format(time)
                            + ".Upgrade" +".Cost", cost +" Tokens");

                    GCPickEnchantz.getInstance().saveLogFile();
                } else {

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            GCPickEnchantz.getInstance().getLang().getString("prefix") +
                                    GCPickEnchantz.getInstance().getLang().getString("purchase-failure-cost").replace("%cost%", String.valueOf(cost))));
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);

                }

                delete();
            });

        }
        final ItemStack ref1 = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta metaref1 = ref1.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        metaref1.addEnchant(Enchantment.DURABILITY, 1, true);
        metaref1.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&b&l● &7Cost: &b" + cost + " &7tokens"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&b&l● &7Upgrade: &b"+enchanttype+" &8[&a+1&8]"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&b&l● &7New Level: &b"+upgrade));
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&c&lWarning: &fAll upgrades are irreversible, once completing"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&fthis action there is no way of getting a refund."));
        lore.add(ChatColor.translateAlternateColorCodes('&', ""));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&b&l→ &7Click yes to complete this purchase."));


        metaref1.setLore(lore);
        metaref1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f&nAre you sure?"));


        ref1.setItemMeta(metaref1);
        getInventory().setItem(4, ref1);

        //Red stained pane
        for (int column = 5; column < 9; column++) {

            setItem(column, createGuiItem(Material.STAINED_GLASS_PANE, (byte) 14, ChatColor.translateAlternateColorCodes('&', "&c&nNo"), ""), player -> {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        GCPickEnchantz.getInstance().getLang().getString("prefix") +
                                GCPickEnchantz.getInstance().getLang().getString("purchase-cancelled")));
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                delete();
            });

        }

    }

    public interface ConfirmedAction {
        void confirm();
    }

}
