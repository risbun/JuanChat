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

        Player player = event.getPlayer();
        ChatColor color = playerColor(player);

        player.sendMessage(welcome.replaceFirst("<user>", event.getPlayer().getDisplayName()));

        player.setPlayerListName(color + event.getPlayer().getDisplayName());
        event.setJoinMessage(null);

        for (Player p : Bukkit.getOnlinePlayers()){
            if (p == player) continue;
            p.sendMessage(String.format("%s%s %s",color, player.getDisplayName(), joinMessage));
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        String leaveMessage = config.getString("messages.leave");
        Player player = event.getPlayer();
        ChatColor color = playerColor(player);

        event.setQuitMessage(String.format("%s%s %s",color, player.getDisplayName(), leaveMessage));
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        ChatColor color = playerColor(event.getPlayer());
        event.setFormat(String.format("%s%s%s: %s", color, event.getPlayer().getDisplayName(), ChatColor.WHITE, event.getMessage()));
    }

    public ChatColor playerColor(Player p){
        if (config.getBoolean("team-mode")) {
            Team playerTeam = plugin.getServer().getScoreboardManager().getMainScoreboard().getEntryTeam(p.getName());
            if (playerTeam != null) return playerTeam.getColor();
        }
        return ChatColor.valueOf(config.getString("color"));
    }

}

