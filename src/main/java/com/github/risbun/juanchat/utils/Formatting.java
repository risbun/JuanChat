package com.github.risbun.juanchat.utils;

import com.github.risbun.juanchat.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Formatting {

    // Get player prefix, either "team" or "permission" mode
    public static String getPrefix(Player p) {
        final String config = Main.getPlugin().getConfig().getString("mode", "team");
        if (config.equals("team"))
            return TeamUtils.getColor(p) + TeamUtils.getPrefix(p);
        else if (config.equals("permission"))
            return PermissionUtils.getPrefix(p);
        return "";
    }

    // Get player suffix, either "team" or "permission" mode
    public static String getSuffix(Player p) {
        final String config = Main.getPlugin().getConfig().getString("mode", "team");
        if (config.equals("team"))
            return TeamUtils.getColor(p) + TeamUtils.getSuffix(p);
        else if (config.equals("permission"))
            return PermissionUtils.getSuffix(p);
        return "";
    }

    // Format chat messages
    public static String chatFormat(Player p, String msg) {
        final String format = Main.getPlugin().getConfig().getString("format.chat", "<{player}&r> {message}");
        // arguments: {prefix} {suffix} {player} {message}
        return ChatColor.translateAlternateColorCodes('&', format)
                .replaceAll("\\{prefix}", getPrefix(p))
                .replaceAll("\\{suffix}", getSuffix(p))
                .replaceAll("\\{player}", p.getDisplayName())
                .replaceAll("\\{message}", msg);
    }

}
