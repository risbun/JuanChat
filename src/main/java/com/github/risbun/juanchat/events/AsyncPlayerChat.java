package com.github.risbun.juanchat.events;

import com.github.risbun.juanchat.utils.Formatting;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void event(AsyncPlayerChatEvent event) {
        event.setFormat(Formatting.chatFormat(event.getPlayer(), event.getMessage()));

        // ping feature
        if ((event.getMessage().indexOf('@') != -1) &&
                (event.getPlayer().hasPermission("juanchat.ping"))) {
            final int i = event.getMessage().indexOf('@');
            final String cut = event.getMessage().substring(i + 1).split(" ")[0];

            final Player p = Bukkit.getPlayerExact(cut);
            if (p != null)
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }

    }
}
