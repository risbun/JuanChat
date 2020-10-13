package com.github.thacheesebun.juanchat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.ChatColor.*;
import static com.github.thacheesebun.juanchat.Main.plugin;

public class CommandJuanchat implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            plugin.reloadConfig();
            sender.sendMessage(String.format("%sReloaded.", GREEN));
        } else sender.sendMessage(String.format("%sYou don't have permission to reload JuanChat!", RED));
        return true;
    }
}