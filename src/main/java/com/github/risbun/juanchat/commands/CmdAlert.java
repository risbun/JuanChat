package com.github.risbun.juanchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdAlert implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final String str = String.join(" ", args);

        for (Player p : Bukkit.getOnlinePlayers())
        {
            p.sendMessage(String.format("%s[%s%sAlert%s] %s%s", ChatColor.DARK_GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.DARK_GRAY, ChatColor.RESET, str));
            p.sendTitle(String.format("%s%sAlert", ChatColor.RED, ChatColor.BOLD), str, str.length(), str.length() * 5, 5);
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }

        return true;
    }
}
