package com.github.thacheesebun.juanchat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static JavaPlugin plugin = null;
    public static FileConfiguration config = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        this.saveDefaultConfig();
        config = getConfig();

        this.getServer().getPluginManager().registerEvents(new EventManager(), this);
        this.getCommand("juanchat").setExecutor(new CommandJuanchat());
        this.getCommand("calc").setExecutor(new CommandCalc());
        this.getCommand("alert").setExecutor(new CommandAlert());
        this.getCommand("show").setExecutor(new CommandShow());
        this.getCommand("f").setExecutor(new CommandF());

        System.out.println("[JuanChat] Enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("[JuanChat] Disabled.");
    }
}
