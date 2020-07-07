package net.guildcraft.gcpickenchantz.gui;

import net.guildcraft.gcpickenchantz.GCPickEnchantz;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PickaxeGUI extends PickaxeGUITemplate {

    private ItemStack pickaxe;

    public PickaxeGUI(ItemStack pickaxe) {
        super(6, ChatColor.translateAlternateColorCodes('&', "&f&nUpgrade Manager"));
        this.pickaxe = pickaxe;
        initializeItems();

    }

    public void initializeItems() {
        setItem(13, pickaxe);

        ItemStack wpane = createGuiItem(Material.STAINED_GLASS_PANE, "");
        ItemStack bpane = createGuiItem(Material.STAINED_GLASS_PANE, (byte) 15, "");

        int effnewlevel = pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1;
        int effcost = GCPickEnchantz.getInstance().getConfig().getInt("costs.efficiency." + (pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1));
        int fornewlevel = pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1;
        int forcost = GCPickEnchantz.getInstance().getConfig().getInt("costs.fortune." + (pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1));
        int unbnewlevel = pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) + 1;
        int unbcost = GCPickEnchantz.getInstance().getConfig().getInt("costs.unbreaking." + (pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) + 1));

        ItemStack efficiency = createGuiItem(Material.ENCHANTED_BOOK,
                ChatColor.translateAlternateColorCodes('&', "&f&nUpgrade&8 → &bEfficiency"),
                ChatColor.translateAlternateColorCodes('&', "&7All upgrades require confirmation."),
                ChatColor.translateAlternateColorCodes('&', ""),
                ChatColor.translateAlternateColorCodes('&', "&7Current Level: &b"+pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED)),
                ChatColor.translateAlternateColorCodes('&', "&7New Level: &b"+effnewlevel+" "+ChatColor.DARK_GRAY+"["+ChatColor.GREEN+"&a+1&8"+ChatColor.DARK_GRAY+"]"),
                ChatColor.translateAlternateColorCodes('&', "&7Cost: &b"+effcost+ ChatColor.GRAY+" Tokens"),
                ChatColor.translateAlternateColorCodes('&', ""),
                ChatColor.translateAlternateColorCodes('&', "&b&l→ &7Click to purchase this upgrade"));

        if (pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) >= 15) {
            efficiency = createEnchantedGuiItem(Material.BARRIER,
                    ChatColor.translateAlternateColorCodes('&', "&f&nUpgrade&8 → &bEfficiency"),
                    ChatColor.GRAY+"Oops! It appears you have reached the",
                    ChatColor.GRAY+"maximum level for this enchantment.",
                    ChatColor.translateAlternateColorCodes('&', ""),
                    ChatColor.translateAlternateColorCodes('&', "&7Current Level: &b"+pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED)),
                    ChatColor.translateAlternateColorCodes('&', "&7New Level: &bN/A"),
                    ChatColor.translateAlternateColorCodes('&', "&7Cost: &bN/A &7Tokens"),
                    ChatColor.translateAlternateColorCodes('&', ""),
                    ChatColor.translateAlternateColorCodes('&', "&b&l→ &cYou cannot upgrade this enchant"));
        }

        ItemStack unbreaking = createGuiItem(Material.ENCHANTED_BOOK,
                ChatColor.translateAlternateColorCodes('&', "&f&nUpgrade&8 → &bUnbreaking"),
                ChatColor.translateAlternateColorCodes('&', "&7All upgrades require confirmation."),
                ChatColor.translateAlternateColorCodes('&', ""),
                ChatColor.translateAlternateColorCodes('&', "&7Current Level: &b"+pickaxe.getEnchantmentLevel(Enchantment.DURABILITY)),
                ChatColor.translateAlternateColorCodes('&', "&7New Level: &b"+unbnewlevel+" "+ChatColor.DARK_GRAY+"["+ChatColor.GREEN+"&a+1&8"+ChatColor.DARK_GRAY+"]"),
                ChatColor.translateAlternateColorCodes('&', "&7Cost: &b"+unbcost+ ChatColor.GRAY+" Tokens"),
                ChatColor.translateAlternateColorCodes('&', ""),
                ChatColor.translateAlternateColorCodes('&', "&b&l→ &7Click to purchase this upgrade"));

        if (pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) >= 15) {
            unbreaking = createEnchantedGuiItem(Material.BARRIER,
                    ChatColor.translateAlternateColorCodes('&', "&f&nUpgrade&8 → &bUnbreaking"),
                    ChatColor.GRAY+"Oops! It appears you have reached the",
                    ChatColor.GRAY+"maximum level for this enchantment.",
                    ChatColor.translateAlternateColorCodes('&', ""),
                    ChatColor.translateAlternateColorCodes('&', "&7Current Level: &b"+pickaxe.getEnchantmentLevel(Enchantment.DURABILITY)),
                    ChatColor.translateAlternateColorCodes('&', "&7New Level: &bN/A"),
                    ChatColor.translateAlternateColorCodes('&', "&7Cost: &bN/A &7Tokens"),
                    ChatColor.translateAlternateColorCodes('&', ""),
                    ChatColor.translateAlternateColorCodes('&', "&b&l→ &cYou cannot upgrade this enchant"));
        }

        ItemStack fortune = createGuiItem(Material.ENCHANTED_BOOK,
                ChatColor.translateAlternateColorCodes('&', "&f&nUpgrade&8 → &bFortune"),
                ChatColor.translateAlternateColorCodes('&', "&7All upgrades require confirmation."),
                ChatColor.translateAlternateColorCodes('&', ""),
                ChatColor.translateAlternateColorCodes('&', "&7Current Level: &b"+pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)),
                ChatColor.translateAlternateColorCodes('&', "&7New Level: &b"+fornewlevel+" "+ChatColor.DARK_GRAY+"["+ChatColor.GREEN+"&a+1&8"+ChatColor.DARK_GRAY+"]"),
                ChatColor.translateAlternateColorCodes('&', "&7Cost: &b"+forcost+ ChatColor.GRAY+" Tokens"),
                ChatColor.translateAlternateColorCodes('&', ""),
                ChatColor.translateAlternateColorCodes('&', "&b&l→ &7Click to purchase this upgrade"));

        if (pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 15) {
            fortune = createEnchantedGuiItem(Material.BARRIER,
                    ChatColor.translateAlternateColorCodes('&', "&f&nUpgrade&8 → &bFortune"),
                    ChatColor.GRAY+"Oops! It appears you have reached the",
                    ChatColor.GRAY+"maximum level for this enchantment.",
                    ChatColor.translateAlternateColorCodes('&', ""),
                    ChatColor.translateAlternateColorCodes('&', "&7Current Level: &b"+pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS)),
                    ChatColor.translateAlternateColorCodes('&', "&7New Level: &bN/A"),
                    ChatColor.translateAlternateColorCodes('&', "&7Cost: &bN/A &7Tokens"),
                    ChatColor.translateAlternateColorCodes('&', ""),
                    ChatColor.translateAlternateColorCodes('&', "&b&l→ &cYou cannot upgrade this enchant"));
        }

        setItem(29, efficiency, player -> {

            if (pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) >= 15) return;

            int cost = GCPickEnchantz.getInstance().getConfig().getInt("costs.efficiency." + (pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1));

            PickaxeConfirmationGUI confirm = new PickaxeConfirmationGUI(cost, "Efficiency", String.valueOf(pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1), () -> {

                pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, pickaxe.getEnchantmentLevel(Enchantment.DIG_SPEED) + 1);
                delete();

            });
            confirm.open(player);
        });

        setItem(30, unbreaking, player -> {

            if (pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) >= 15) return;

            int cost = GCPickEnchantz.getInstance().getConfig().getInt("costs.unbreaking." + (pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) + 1));

            PickaxeConfirmationGUI confirm = new PickaxeConfirmationGUI(cost, "Unbreaking", String.valueOf(pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) + 1), () -> {

                pickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, pickaxe.getEnchantmentLevel(Enchantment.DURABILITY) + 1);
                delete();

            });
            confirm.open(player);
        });

        setItem(31, fortune, player -> {

            if (pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) >= 15) return;

            int cost = GCPickEnchantz.getInstance().getConfig().getInt("costs.fortune." + (pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1));

            PickaxeConfirmationGUI confirm = new PickaxeConfirmationGUI(cost, "Fortune", String.valueOf(pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1), () -> {

                pickaxe.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, pickaxe.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1);
                delete();

            });
            confirm.open(player);
        });


        // Below this is all glass pane management


        AtomicInteger i = new AtomicInteger(-1);
        while (i.get() < 8) {
            setItem(i.incrementAndGet(), wpane, player -> {
                i.incrementAndGet();

            });

        }
        AtomicInteger i2 = new AtomicInteger(17);
        while (i2.get() < 26) {
            setItem(i2.incrementAndGet(), wpane, player -> {
                i2.incrementAndGet();

            });

        }

        AtomicInteger i3 = new AtomicInteger(44);
        while (i3.get() < 53) {
            setItem(i3.incrementAndGet(), bpane, player -> {
                i3.incrementAndGet();

            });

        }

        List<Integer> white = new ArrayList<Integer>();
        white.add(9);
        white.add(11);
        white.add(12);
        white.add(14);
        white.add(15);
        white.add(17);
        for (Integer num : white) {
            setItem(num, wpane, player -> {

            });

        }
        List<Integer> black = new ArrayList<Integer>();
        black.add(27);
        black.add(28);
        black.add(34);
        black.add(35);
        black.add(36);
        black.add(37);
        black.add(43);
        black.add(44);
        for (Integer num : black) {
            setItem(num, bpane, player -> {

            });

        }
    }
}
