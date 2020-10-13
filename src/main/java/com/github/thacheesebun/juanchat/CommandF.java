package com.github.thacheesebun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

public class CommandF implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            ChatColor color = valueOf(Main.config.getString("color"));
            for (Player p : Bukkit.getOnlinePlayers()) {
                Bukkit.broadcastMessage(String.format("%s%s%s: F", color, p.getDisplayName(), WHITE));
            }
        } else sender.sendMessage(String.format("%sYou don't have access to pay respect.", RED));
        return true;
    }
}
