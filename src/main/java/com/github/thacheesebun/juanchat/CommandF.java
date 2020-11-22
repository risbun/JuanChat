package com.github.thacheesebun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.thacheesebun.juanchat.Utils.*;

public class CommandF implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ChatColor color = playerColor((Player) sender);
        for (Player p : Bukkit.getOnlinePlayers()) {
            Bukkit.broadcastMessage(String.format("%s%s%s: F", color, p.getDisplayName(), ChatColor.WHITE));
        }
        return true;
    }
}
