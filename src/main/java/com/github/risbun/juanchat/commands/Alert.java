package com.github.risbun.juanchat.commands;

import com.github.risbun.juanchat.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Alert implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final String str = String.join(" ", args);

        ChatColor color;
        try {
            color = ChatColor.valueOf(Main.plugin.getConfig().getString("color", "red").toUpperCase());
        } catch (IllegalArgumentException e) {
            color = ChatColor.RED;
        }

        for (Player p : Bukkit.getOnlinePlayers())
        {
            p.sendMessage(String.format("%s[%s%sAlert%s] %s%s", ChatColor.DARK_GRAY, color, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.RESET, str));
            p.sendTitle(String.format("%s%sAlert", color, ChatColor.BOLD), str, str.length(), str.length() * 5, 5);
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }

        return true;
    }
}
