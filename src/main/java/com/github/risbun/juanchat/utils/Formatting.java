package com.github.risbun.juanchat.utils;

import com.github.risbun.juanchat.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Formatting {
    public static String chatFormat(Player p, String msg) {
        final String format = Main.getPlugin().getConfig().getString("format.chat", "<${player}§r> ${message}");

        // arguments: ${} prefix,suffix,player,message
        return format.replaceAll("\\$\\{prefix}", PermissionsHelper.getPrefix(p))
                .replaceAll("\\$\\{suffix}", PermissionsHelper.getSuffix(p))
                .replaceAll("\\$\\{message}", ChatColor.RESET + msg)
                .replaceAll("\\$\\{player}",
                        PlayerColor.get(p) +
                                p.getDisplayName()
                );
    }
}
