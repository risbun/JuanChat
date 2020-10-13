package com.github.thacheesebun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;

import static com.github.thacheesebun.juanchat.Main.*;

public class EventManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String welcome = config.getString("messages.welcome");
        String joinMessage = config.getString("messages.join");
        ChatColor color = ChatColor.valueOf(config.getString("color"));

        event.setJoinMessage(null);
        Player player = event.getPlayer();
        player.sendMessage(welcome.replaceFirst("<user>", event.getPlayer().getDisplayName()));

        if (config.getBoolean("team-mode")) {
            Team playerTeam = plugin.getServer().getScoreboardManager().getMainScoreboard().getEntryTeam(player.getName());
            if (playerTeam != null) color = playerTeam.getColor();
        }

        player.setPlayerListName(color + event.getPlayer().getDisplayName());
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p != player) {
                p.sendMessage(String.format("%s%s %s",color, player.getDisplayName(), joinMessage));
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        String leaveMessage = config.getString("messages.leave");
        ChatColor color = ChatColor.valueOf(config.getString("color"));

        event.setQuitMessage(null);
        Player player = event.getPlayer();

        if (config.getBoolean("team-mode")) {
            Team playerTeam = plugin.getServer().getScoreboardManager().getMainScoreboard().getEntryTeam(player.getName());
            if (playerTeam != null) color = playerTeam.getColor();
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p != player) {
                p.sendMessage(String.format("%s%s %s",color, player.getDisplayName(), leaveMessage));
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        ChatColor color = ChatColor.valueOf(config.getString("color"));
        if (config.getBoolean("team-mode")) {
            Team playerTeam = plugin.getServer().getScoreboardManager().getMainScoreboard().getEntryTeam(event.getPlayer().getName());
            if (playerTeam != null) color = playerTeam.getColor();
        }
        event.setFormat(String.format("%s%s%s: %s", color, event.getPlayer().getDisplayName(), ChatColor.WHITE, event.getMessage()));
    }
}

