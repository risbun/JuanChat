package com.github.risbun.juanchat.utils;

import com.github.risbun.juanchat.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class PlayerColor {

    // get color for player
    public static String get(Player p) {
        final FileConfiguration config = Main.getPlugin().getConfig();
        if (config.getBoolean("team-mode", false)) {
            final ScoreboardManager manager = Main.getPlugin().getServer().getScoreboardManager();
            if (manager != null) {
                final Team team = manager.getMainScoreboard().getEntryTeam(p.getName());
                if (team != null)
                    return team.getColor().toString();
            }
        }
        try {
            return ChatColor.valueOf(config.getString("color", "").toUpperCase()).toString();
        } catch (IllegalArgumentException e) {
            return "";
        }
    }

}
