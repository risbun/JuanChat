package com.github.thacheesebun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAlert implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender.isOp()) {
            final StringBuilder builder = new StringBuilder();
            for (final String s : args) {
                builder.append(s).append(" ");
            }
            final String str = builder.toString();
            for (final Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(String.valueOf(ChatColor.BLUE) + ChatColor.BOLD + "Alert " + ChatColor.GOLD + ">> " + ChatColor.WHITE + str);
                p.sendTitle(ChatColor.BLUE + "Alert", str, str.length(), str.length() * 5, 5);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            }
        } else sender.sendMessage(ChatColor.RED + "You are not allowed to send an alert!");
        return true;
    }
}

