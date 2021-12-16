package com.github.risbun.juanchat.events;

import com.github.risbun.juanchat.Main;
import com.github.risbun.juanchat.utils.Formatting;
import com.github.risbun.juanchat.utils.TeamUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void event(PlayerJoinEvent event) {
        final Player p = event.getPlayer();
        final FileConfiguration config = Main.getPlugin().getConfig();

        // WelcomeMessage
        final String welcomeFormat = config.getString("format.welcome", "");
        if (!welcomeFormat.equals("")) {
            // arguments: {prefix} {suffix} {player}
            p.sendMessage(
                    ChatColor.translateAlternateColorCodes('&', welcomeFormat)
                    .replaceAll("\\{prefix}", Formatting.getPrefix(p))
                    .replaceAll("\\{suffix}", Formatting.getSuffix(p))
                    .replaceAll("\\{player}", TeamUtils.getColor(p) + p.getDisplayName())
            );
        }

        // JoinMessage
        final String joinFormat = config.getString("format.join", "&e{player}&e joined the game");
        // arguments: {prefix} {suffix} {player}
        event.setJoinMessage(
                ChatColor.translateAlternateColorCodes('&', joinFormat)
                .replaceAll("\\{prefix}", Formatting.getPrefix(p))
                .replaceAll("\\{suffix}", Formatting.getSuffix(p))
                .replaceAll("\\{player}", TeamUtils.getColor(p) + p.getDisplayName())
        );
    }
}
