package com.github.risbun.juanchat.utils;

import com.github.risbun.juanchat.Main;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.entity.Player;

public class PermissionsHelper {

    // prefix helper for LuckPerms
    public static String getPrefix(Player p) {
        if (notLucky())
            return "";
        final CachedMetaData metaData = LuckPermsProvider.get().getPlayerAdapter(Player.class).getMetaData(p);
        if (metaData.getPrefix() == null)
            return "";
        return metaData.getPrefix().replaceAll("&(?=[0-9a-fk-or])", "\u00A7");
    }

    // suffix helper for LuckPerms
    public static String getSuffix(Player p) {
        if (notLucky())
            return "";
        final CachedMetaData metaData = LuckPermsProvider.get().getPlayerAdapter(Player.class).getMetaData(p);
        if (metaData.getSuffix() == null)
            return "";
        return metaData.getSuffix().replaceAll("&(?=[0-9a-fk-or])", "\u00A7");
    }

    // determine if LuckPerms is loaded
    private static boolean notLucky() {
        if (Main.getPlugin().getServer().getPluginManager().isPluginEnabled("LuckPerms")) {
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
