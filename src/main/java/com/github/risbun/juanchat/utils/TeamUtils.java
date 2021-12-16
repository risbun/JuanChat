package com.github.risbun.juanchat.utils;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TeamUtils {

    // Get team color
    public static String getColor(Player p) {
        final Team team = getTeam(p);
        if (team != null)
            return team.getColor().toString();
        return "";
    }

    // Get team prefix
    public static String getPrefix(Player p) {
        final Team team = getTeam(p);
        if (team != null)
            return team.getPrefix();
        return "";
    }

    // Get team suffix
    public static String getSuffix(Player p) {
        final Team team = getTeam(p);
        if (team != null)
            return team.getSuffix();
        return "";
    }

    // Get team of player internal
    private static Team getTeam(Player p) {
        final ScoreboardManager manager = p.getServer().getScoreboardManager();
        if (manager != null) {
            return manager.getMainScoreboard().getEntryTeam(p.getName());
        }
        return null;
    }

}
