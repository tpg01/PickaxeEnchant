package net.guildcraft.gcpickenchantz.gui;

import net.guildcraft.gcpickenchantz.GCPickEnchantz;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PickaxeGUI extends PickaxeGUITemplate {

    private ItemStack pickaxe;

    public PickaxeGUI(ItemStack pickaxe) {
        super(3);
        this.pickaxe = pickaxe;
        initializeItems();

    }

    public void initializeItems() {
        setItem(4, pickaxe);

        ItemStack efficiency = createGuiItem(Material.ENCHANTED_BOOK,
                ChatColor.GREEN + "Upgrade Efficiency",
                ChatColor.WHITE + "Level " + String.valueOf(pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED)) + " to " + String.valueOf(pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1),
                ChatColor.YELLOW + "Cost: " + GCPickEnchantz.getInstance().getConfig().getInt("costs.efficiency." + (pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1)) + " tokens");

        if (pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) >= 15) {
            efficiency = createGuiItem(Material.BOOK,
                    ChatColor.RED + "Efficiency reached max level!", "");
        }

        ItemStack unbreaking = createGuiItem(Material.ENCHANTED_BOOK,
                ChatColor.GREEN + "Upgrade Unbreaking",
                ChatColor.WHITE + "Level " + String.valueOf(pickaxe.getEnchantmentLevel(Enchantment.DURABILITY)) + " to " + String.valueOf(pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) + 1),
                ChatColor.YELLOW + "Cost: " + GCPickEnchantz.getInstance().getConfig().getInt("costs.unbreaking." + (pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) + 1)) + " tokens");

        if (pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) >= 15) {
            unbreaking = createGuiItem(Material.BOOK,
                    ChatColor.RED + "Unbreaking reached max level!", "");
        }

        ItemStack fortune = createGuiItem(Material.ENCHANTED_BOOK,
                ChatColor.GREEN + "Upgrade Fortune",
                ChatColor.WHITE + "Level " + String.valueOf(pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)) + " to " + String.valueOf(pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1),
                ChatColor.YELLOW + "Cost: " + GCPickEnchantz.getInstance().getConfig().getInt("costs.fortune." + (pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1)) + " tokens");

        if (pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 15) {
            fortune = createGuiItem(Material.BOOK,
                    ChatColor.RED + "Fortune reached max level!", "");
        }

        setItem(12, efficiency, player -> {

            if (pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) >= 15) return;

            int cost = GCPickEnchantz.getInstance().getConfig().getInt("costs.efficiency." + (pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1));

            PickaxeConfirmationGUI confirm = new PickaxeConfirmationGUI(cost, () -> {

                pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1);
                delete();

            });
            confirm.open(player);
        });

        setItem(13, unbreaking, player -> {

            if (pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) >= 15) return;

            int cost = GCPickEnchantz.getInstance().getConfig().getInt("costs.unbreaking." + (pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) + 1));

            PickaxeConfirmationGUI confirm = new PickaxeConfirmationGUI(cost, () -> {

                pickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) + 1);
                delete();

            });
            confirm.open(player);
        });

        setItem(14, fortune, player -> {

            if (pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 15) return;

            int cost = GCPickEnchantz.getInstance().getConfig().getInt("costs.fortune." + (pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1));

            PickaxeConfirmationGUI confirm = new PickaxeConfirmationGUI(cost, () -> {

                pickaxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1);
                delete();

            });
            confirm.open(player);
        });

    }

}
