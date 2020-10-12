package com.github.thacheesebun.juanchat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandJuanchat implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender.isOp()) {
            Main.config = Main.plugin.getConfig();
            sender.sendMessage(ChatColor.GREEN + "Reloaded.");
        } else sender.sendMessage(ChatColor.RED + "You don't have permission to reload JuanChat!");
        return true;
    }
}