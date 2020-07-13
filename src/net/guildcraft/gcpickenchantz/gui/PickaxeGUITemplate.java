package net.guildcraft.gcpickenchantz.gui;

import net.guildcraft.gcpickenchantz.GCPickEnchantz;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.bukkit.Material.*;

public abstract class PickaxeGUITemplate {

    public static Map<UUID, PickaxeGUITemplate> inventoriesByUUID = new HashMap<>();
    public static Map<UUID, UUID> openInventories = new HashMap<>();

    private final Inventory inventory;
    private Map<Integer, PickaxeGUIAction> actions;
    private UUID uuid;

    public PickaxeGUITemplate(int rows,String invname) {
        uuid = UUID.randomUUID();
        inventory = Bukkit.createInventory(null, 9 * rows, invname);

        actions = new HashMap<>();
        inventoriesByUUID.put(getUUID(), this);

    }

    public Inventory getInventory() {
        return inventory;
    }

    public interface PickaxeGUIAction {
        void click(Player player);
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    protected ItemStack createGuiItem(final Material material, byte subId, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1, subId);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public void setItem(int slot, ItemStack stack, PickaxeGUIAction action){
        inventory.setItem(slot, stack);
        if (action != null){
            actions.put(slot, action);
        }
    }

    public void setPickaxe(int slot, ItemStack stack, PickaxeGUIAction action){
        inventory.setItem(slot, stack);
        if (action != null){
            actions.put(slot, action);
        }
    }

    public void setItem(int slot, ItemStack stack){
        setItem(slot, stack, null);
    }

    public void setPickaxe(int slot){
        ItemStack pick = new ItemStack(DIAMOND_PICKAXE);
        ItemMeta pickmeta = pick.getItemMeta();
        pickmeta.addEnchant(Enchantment.DURABILITY, 1, true);
        pickmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        setItem(slot, pick, null);
    }

    public void open(Player player) {
        player.playNote(player.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.A));
        player.openInventory(inventory);
        openInventories.put(player.getUniqueId(), getUUID());
    }

    public UUID getUUID() {
        return uuid;
    }

    public static Map<UUID, PickaxeGUITemplate> getInventoriesByUUID() {
        return inventoriesByUUID;
    }

    public static Map<UUID, UUID> getOpenInventories() {
        return openInventories;
    }

    public Map<Integer, PickaxeGUIAction> getActions() {
        return actions;
    }

    public void delete() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            UUID u = openInventories.get(p.getUniqueId());
            if (u.equals(getUUID())) {
                p.closeInventory();
            }
        }
        inventoriesByUUID.remove(getUUID());
    }

    protected ItemStack createEnchantedGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        return item;
    }

    protected ItemStack createEnchantedGuiItem(final Material material, byte subId, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1, subId);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));
        meta.addEnchant(Enchantment.DURABILITY, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        return item;
    }


    public void RepairPickaxe(Player player) {
        ItemStack hand = player.getInventory().getItemInMainHand();

        if((!hand.getType().equals(DIAMOND_PICKAXE)) && (!hand.getType().equals(IRON_PICKAXE)) && (!hand.getType().equals(STONE_PICKAXE)) &&
        (!hand.getType().equals(WOOD_PICKAXE)) && (!hand.getType().equals(GOLD_PICKAXE))) {
            return;

        }
        Integer level = hand.getEnchantmentLevel(Enchantment.DIG_SPEED);

        AtomicInteger i = new AtomicInteger(0);

        Integer price = GCPickEnchantz.getInstance().getConfig().getInt("Repair-Prices." + "Pickaxe." + level);

        }


    }

