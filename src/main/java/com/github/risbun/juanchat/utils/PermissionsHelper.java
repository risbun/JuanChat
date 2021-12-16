package com.github.risbun.juanchat.utils;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

public class PermissionsHelper {

    // prefix helper for LuckPerms
    public static String getPrefix(Player p) {
        if (notLucky(p.getServer().getPluginManager()))
            return "";
        final CachedMetaData metaData = LuckPermsProvider.get().getPlayerAdapter(Player.class).getMetaData(p);
        if (metaData.getPrefix() == null)
            return "";
        return ChatColor.translateAlternateColorCodes('&', metaData.getPrefix());
    }

    // suffix helper for LuckPerms
    public static String getSuffix(Player p) {
        if (notLucky(p.getServer().getPluginManager()))
            return "";
        final CachedMetaData metaData = LuckPermsProvider.get().getPlayerAdapter(Player.class).getMetaData(p);
        if (metaData.getSuffix() == null)
            return "";
        return ChatColor.translateAlternateColorCodes('&', metaData.getSuffix());
    }

    // determine if LuckPerms is loaded
    private static boolean notLucky(PluginManager manager) {
        if (manager.isPluginEnabled("LuckPerms")) {
            try {
                LuckPermsProvider.get();
                return false;
            } catch (IllegalStateException e) {
                return true;
            }
        }
        return true;
    }

}
