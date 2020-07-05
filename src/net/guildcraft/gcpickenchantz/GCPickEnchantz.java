package net.guildcraft.gcpickenchantz;

import net.guildcraft.gcpickenchantz.listener.PickaxeGUIListener;
import net.guildcraft.gcpickenchantz.listener.PickaxeListener;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GCPickEnchantz extends JavaPlugin {

    private static GCPickEnchantz instance;

    @Override
    public void onEnable() {
        instance = this;
        createLangFile();
        Bukkit.getServer().getPluginManager().registerEvents(new PickaxeListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PickaxeGUIListener(), this);
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {

    }

    public static TextComponent format(String input) {
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', input));

    }

    public static GCPickEnchantz getInstance() {
        return instance;
    }

    public File LangFile;
    public FileConfiguration LangConfig;

    public FileConfiguration getLang() {
        return this.LangConfig;
    }

    public void reloadLangFile() {
        LangConfig = YamlConfiguration.loadConfiguration(LangFile);
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[GCPickaxeEnchant] lang.yml was reloaded successfully");
    }

    public void saveLangFile() {
        try {
            LangConfig.save(LangFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createLangFile() {
        LangFile = new File(getDataFolder(), "lang.yml");
        if (!LangFile.exists()) {
            LangFile.getParentFile().mkdirs();
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[GCPickaxeEnchant] lang.yml was created successfully");
            saveResource("lang.yml", false);
        }

        LangConfig = new YamlConfiguration();
        try {
            LangConfig.load(LangFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
