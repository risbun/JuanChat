package com.github.risbun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.MetadataValue;

import static com.github.risbun.juanchat.Utils.*;
import static com.github.risbun.juanchat.Main.*;
import static org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP;

public class EventManager implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        boolean skip = false;

        for (MetadataValue meta : event.getPlayer().getMetadata("vanished")) {
            if (meta.asBoolean()) skip = true;
        }

        if (!skip) {
            String welcome = config.getString("messages.welcome");
            String joinMessage = config.getString("messages.join");

            Player player = event.getPlayer();
            ChatColor color = playerColor(player);

            assert welcome != null;
            player.sendMessage(String.format(welcome, player.getDisplayName()));

            player.setPlayerListName(color + event.getPlayer().getDisplayName());
            event.setJoinMessage(null);


            String playerWithColor = color + player.getDisplayName();

            for (Player p : Bukkit.getOnlinePlayers()){
                if (p == player) continue;
                assert joinMessage != null;
                p.sendMessage(String.format(joinMessage, playerWithColor));
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        String leaveMessage = config.getString("messages.leave");
        Player player = event.getPlayer();
        ChatColor color = playerColor(player);

        String playerWithColor = color + player.getDisplayName();

        assert leaveMessage != null;
        event.setQuitMessage(String.format(leaveMessage, playerWithColor));
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        ChatColor color = playerColor(event.getPlayer());
        String msg = event.getMessage();
        if (msg.endsWith("/")) msg = msg.substring(0, msg.length() - 1);

        event.setFormat(String.format("%s%s%s: %s", color, event.getPlayer().getDisplayName(), ChatColor.WHITE, msg));

        if (event.getMessage().indexOf('@') != -1) {
            int i = event.getMessage().indexOf('@');
            String cutted = event.getMessage().substring(i + 1);
            cutted = cutted.split(" ")[0];

            Player p = Bukkit.getPlayer(cutted);
            if (p != null) p.playSound(p.getLocation(), ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }
    }

}

