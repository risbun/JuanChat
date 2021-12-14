package com.github.risbun.juanchat.events;

import com.github.risbun.juanchat.Main;
import com.github.risbun.juanchat.utils.PermissionsHelper;
import com.github.risbun.juanchat.utils.PlayerColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void event(PlayerQuitEvent event) {
        final String format = Main.plugin.getConfig().getString("format.quit", "${player}§e left the game");

        // arguments: ${} prefix,suffix,player,message
        final String out = format.replaceAll("\\$\\{prefix}", PermissionsHelper.getPrefix(event.getPlayer()))
                .replaceAll("\\$\\{suffix}", PermissionsHelper.getSuffix(event.getPlayer()))
                .replaceAll("\\$\\{player}",
                        PlayerColor.get(event.getPlayer()) +
                                event.getPlayer().getDisplayName()
                );

        event.setQuitMessage(out);
    }
}
