package com.github.risbun.juanchat;

import com.github.risbun.juanchat.commands.*;
import com.github.risbun.juanchat.events.AsyncPlayerChat;
import com.github.risbun.juanchat.events.PlayerJoin;
import com.github.risbun.juanchat.events.PlayerQuit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static JavaPlugin plugin = null;

    @Override
    public void onEnable() {
        plugin = this;

        // setup and verify config
        try {
            setupConfig();
        } catch (InvalidConfigurationException e) {
            getLogger().severe(e.getMessage());
            getPluginLoader().disablePlugin(this);
            return;
        }

        // register events
        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);

        // register commands
        getCommand("alert").setExecutor(new Alert());
        getCommand("f").setExecutor(new F());
    }

    @Override
    public void onDisable() {
    }

    // setup and verify config
    // don't remove might get used in future
    private void setupConfig() throws InvalidConfigurationException {
        saveDefaultConfig();
        //final FileConfiguration config = getConfig();
        // verify config
        //throw new InvalidConfigurationException("yo");
    }
}
