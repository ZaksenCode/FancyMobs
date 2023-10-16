package org.zaksen.fancymobs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.zaksen.fancymobs.commands.SpawnBossCMD;
import org.zaksen.fancymobs.events.MobEvents;
import org.zaksen.fancymobs.util.ItemGenerator;

public final class FancyMobs extends JavaPlugin {

    private static FancyMobs instance;
    private static FileConfiguration config;

    public static FancyMobs getInstance() {
        return instance;
    }

    public static FileConfiguration getMainConfig() {
        return config;
    }

    @Override
    public void onEnable() {
        instance = this;
        // Create config
        saveDefaultConfig();
        config = getConfig();
        // Initialize items
        ItemGenerator.instance.initialize();
        // Register events
        Bukkit.getPluginManager().registerEvents(new MobEvents(), this);
        getCommand("spawn_boss").setExecutor(new SpawnBossCMD());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
