package com.github.risbun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMsg implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            Player receiver = Bukkit.getPlayer(args[0]);
            if (receiver == null) sender.sendMessage(String.format("%sReceiving player was not found online on the server", ChatColor.RED));
            else {
                String[] args2 = Utils.removeFromArray(args, 0);
                String msg = String.join(" ", args2);
                sender.sendMessage(String.format("%s%sYou whisper to %s: %s", ChatColor.GRAY, ChatColor.ITALIC, receiver.getName(), msg));
                receiver.sendMessage(String.format("%s%s%s whispers to you: %s", ChatColor.GRAY, ChatColor.ITALIC, sender.getName(), msg));

                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("juanchat.command.msg.spy") && p.getMetadata("juanchat_hidden").size() == 0) {
                        p.sendMessage(String.format("%s%s > %s: %s", ChatColor.GRAY, sender.getName(), receiver.getName(), msg));
                    }
                }
            }
        } else {
            sender.sendMessage(String.format("%sUnknown or incomplete command, see below for usage\n%s/msg <player> <message>", ChatColor.RED, ChatColor.GRAY));
        }
        return true;
    }
}

