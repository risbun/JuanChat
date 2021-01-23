package com.github.risbun.juanchat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

import static com.github.risbun.juanchat.Main.config;
import static com.github.risbun.juanchat.Main.plugin;

public class Utils {

    //sacred method, dont look
    public static ChatColor playerColor(Player p) {
        if (config.getBoolean("team-mode")) {
            Team playerTeam = plugin.getServer().getScoreboardManager().getMainScoreboard().getEntryTeam(p.getName());
            if (playerTeam != null) return playerTeam.getColor();
        } else if (config.getBoolean(("permission-mode"))) {
            if (p.hasPermission("juanchat.color.gold")) return ChatColor.GOLD;
            else if (p.hasPermission("juanchat.color.aqua")) return ChatColor.AQUA;
            else if (p.hasPermission("juanchat.color.black")) return ChatColor.BLACK;
            else if (p.hasPermission("juanchat.color.blue")) return ChatColor.BLUE;
            else if (p.hasPermission("juanchat.color.dark_aqua")) return ChatColor.DARK_AQUA;
            else if (p.hasPermission("juanchat.color.dark_blue")) return ChatColor.DARK_BLUE;
            else if (p.hasPermission("juanchat.color.dark_gray")) return ChatColor.DARK_GRAY;
            else if (p.hasPermission("juanchat.color.dark_green")) return ChatColor.DARK_GREEN;
            else if (p.hasPermission("juanchat.color.dark_red")) return ChatColor.DARK_RED;
            else if (p.hasPermission("juanchat.color.dark_purple")) return ChatColor.DARK_PURPLE;
            else if (p.hasPermission("juanchat.color.gray")) return ChatColor.GRAY;
            else if (p.hasPermission("juanchat.color.green")) return ChatColor.GREEN;
            else if (p.hasPermission("juanchat.color.light_purple")) return ChatColor.LIGHT_PURPLE;
            else if (p.hasPermission("juanchat.color.red")) return ChatColor.RED;
            else if (p.hasPermission("juanchat.color.yellow")) return ChatColor.YELLOW;
            else if (p.hasPermission("juanchat.color.white")) return ChatColor.WHITE;
        }
        return ChatColor.valueOf(config.getString("color"));
    }

    public static String[] removeFromArray(String[] arr, int index) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i != index) result.add(arr[i]);
        }
        return result.toArray(new String[result.size()]);
    }
}
