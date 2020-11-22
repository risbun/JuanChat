package com.github.thacheesebun.juanchat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static JavaPlugin plugin = null;
    public static FileConfiguration config = null;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        config = getConfig();

        if (config.getBoolean("team-mode") && config.getBoolean("permission-mode")) throw new IllegalStateException("You can't have Team mode and Permission mode at the same time");

        getServer().getPluginManager().registerEvents(new EventManager(), this);
        getCommand("calc").setExecutor(new CommandCalc());
        getCommand("alert").setExecutor(new CommandAlert());
        getCommand("show").setExecutor(new CommandShow());
        getCommand("f").setExecutor(new CommandF());

        System.out.println("[JuanChat] Enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("[JuanChat] Disabled.");
    }
}
