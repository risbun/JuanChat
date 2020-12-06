package com.github.risbun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandF implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            ChatColor color = Utils.playerColor(p);
            Bukkit.broadcastMessage(String.format("%s%s%s: F", color, p.getDisplayName(), ChatColor.WHITE));
        }
        return true;
    }
}
