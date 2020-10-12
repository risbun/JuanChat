package com.github.thacheesebun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandF implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender.isOp()) {
            ChatColor color = ChatColor.valueOf(Main.config.getString("color"));
            for (final Player p : Bukkit.getOnlinePlayers()) {
                Bukkit.broadcastMessage(color + p.getDisplayName() + ChatColor.WHITE + ": F");
            }
        } else sender.sendMessage(ChatColor.RED + "You don't have access to pay respect.");
        return true;
    }
}
