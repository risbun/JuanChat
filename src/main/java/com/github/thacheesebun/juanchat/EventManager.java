package com.github.thacheesebun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String welcome = Main.config.getString("messages.welcome");
        String joinMessage = Main.config.getString("messages.join");
        ChatColor color = ChatColor.valueOf(Main.config.getString("color"));

        event.setJoinMessage(null);

        Player player = event.getPlayer();
        player.setPlayerListName(color + event.getPlayer().getDisplayName());
        player.sendMessage(welcome.replaceFirst("<user>", event.getPlayer().getDisplayName()));

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p != player) {
                p.sendMessage(String.format("%s%s %s",color, player.getDisplayName(), joinMessage));
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        String leaveMessage = Main.config.getString("messages.leave");
        ChatColor color = ChatColor.valueOf(Main.config.getString("color"));
        event.setQuitMessage(null);

        Player player = event.getPlayer();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p != player) {
                p.sendMessage(String.format("%s%s %s",color, player.getDisplayName(), leaveMessage));
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        ChatColor color = ChatColor.valueOf(Main.config.getString("color"));
        event.setFormat(String.format("%s%s%s: %s", color, event.getPlayer().getDisplayName(), ChatColor.WHITE, event.getMessage()));
    }
}

