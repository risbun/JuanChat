package com.github.risbun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKill implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            if (sender.hasPermission("juanchat.command.kill.others")) {
                Player p = Bukkit.getPlayer(args[0]);
                if (p == null) sender.sendMessage(String.format("%sPlayer does not exist or is not online", ChatColor.RED));
                else p.setHealth(0);
            } else sender.sendMessage(String.format("%sYou don't have permission to kill others.", ChatColor.RED));
        } else {
            Player p = (Player) sender;
            p.setHealth(0);
        }
        return true;
    }
}
