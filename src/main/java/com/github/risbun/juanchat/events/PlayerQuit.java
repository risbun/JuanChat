package com.github.risbun.juanchat.events;

import com.github.risbun.juanchat.Main;
import com.github.risbun.juanchat.utils.Formatting;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void event(PlayerQuitEvent event) {
        final Player p = event.getPlayer();
        final String format = Main.getPlugin().getConfig().getString("format.quit", "&e{player}&e left the game");

        // arguments: {prefix} {suffix} {player}
        event.setQuitMessage(
                ChatColor.translateAlternateColorCodes('&', format)
                .replaceAll("\\{prefix}", Formatting.getPrefix(p))
                .replaceAll("\\{suffix}", Formatting.getSuffix(p))
                .replaceAll("\\{player}", p.getDisplayName())
        );
    }
}
