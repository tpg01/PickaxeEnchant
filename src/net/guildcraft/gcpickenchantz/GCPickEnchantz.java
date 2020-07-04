package net.guildcraft.gcpickenchantz;

import net.guildcraft.gcpickenchantz.listener.PickaxeGUIListener;
import net.guildcraft.gcpickenchantz.listener.PickaxeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class GCPickEnchantz extends JavaPlugin {

    private static GCPickEnchantz instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getServer().getPluginManager().registerEvents(new PickaxeListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PickaxeGUIListener(), this);
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {

    }

    public static GCPickEnchantz getInstance() {
        return instance;
    }
}
