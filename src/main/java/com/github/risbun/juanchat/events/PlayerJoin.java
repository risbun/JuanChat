package com.github.risbun.juanchat.events;

import com.github.risbun.juanchat.Main;
import com.github.risbun.juanchat.utils.PermissionsHelper;
import com.github.risbun.juanchat.utils.PlayerColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void event(PlayerJoinEvent event) {
        final FileConfiguration config = Main.getPlugin().getConfig();

        // WelcomeMessage
        final String welcomeFormat = config.getString("format.welcome", "");
        if (!welcomeFormat.equals("")) {
            // arguments: ${} prefix,suffix,player,message
            final String joinStr = welcomeFormat
                    .replaceAll("\\$\\{prefix}", PermissionsHelper.getPrefix(event.getPlayer()))
                    .replaceAll("\\$\\{suffix}", PermissionsHelper.getSuffix(event.getPlayer()))
                    .replaceAll("\\$\\{player}",
                            PlayerColor.get(event.getPlayer()) +
                                    event.getPlayer().getDisplayName()
                    );
            event.getPlayer().sendMessage(joinStr);
        }

        // JoinMessage
        final String joinFormat = config.getString("format.join", "${player}§e joined the game");

        // arguments: ${} prefix,suffix,player,message
        final String joinStr = joinFormat
                .replaceAll("\\$\\{prefix}", PermissionsHelper.getPrefix(event.getPlayer()))
                .replaceAll("\\$\\{suffix}", PermissionsHelper.getSuffix(event.getPlayer()))
                .replaceAll("\\$\\{player}",
                        PlayerColor.get(event.getPlayer()) +
                                event.getPlayer().getDisplayName()
                );
        event.setJoinMessage(joinStr);
    }
}
