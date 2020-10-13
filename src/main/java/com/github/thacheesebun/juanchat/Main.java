package com.github.thacheesebun.juanchat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static JavaPlugin plugin = null;
    public static FileConfiguration config = null;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        config = getConfig();

        getServer().getPluginManager().registerEvents(new EventManager(), this);
        getCommand("juanchat").setExecutor(new CommandJuanchat());
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

    public void doReload(){
        reloadConfig();
    }
}
