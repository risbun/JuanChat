package com.github.risbun.juanchat.utils;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.entity.Player;

public class PermissionsHelper {
    public static String getPrefix(Player p) {
        if (notLucky())
            return "";
        final CachedMetaData metaData = LuckPermsProvider.get().getPlayerAdapter(Player.class).getMetaData(p);
        if (metaData.getPrefix() == null)
            return "";
        return metaData.getPrefix().replaceAll("&(?=[0-9a-fk-or])", "\u00A7");
    }
    public static String getSuffix(Player p) {
        if (notLucky())
            return "";
        final CachedMetaData metaData = LuckPermsProvider.get().getPlayerAdapter(Player.class).getMetaData(p);
        if (metaData.getSuffix() == null)
            return "";
        return metaData.getSuffix().replaceAll("&(?=[0-9a-fk-or])", "\u00A7");
    }
    private static boolean notLucky() {
        try {
            LuckPermsProvider.get();
            return false;
        } catch (IllegalStateException e) {
            return true;
        }
    }
}
