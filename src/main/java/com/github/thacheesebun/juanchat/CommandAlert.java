package com.github.thacheesebun.juanchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;
import static org.bukkit.Sound.*;

public class CommandAlert implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String s : args) {
            builder.append(s).append(" ");
        }
        String str = builder.toString();
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(String.format("%s%sAlert %s>> %s%s", BLUE, BOLD, GOLD, WHITE, str));
            p.sendTitle(String.format("%sAlert", BLUE), str, str.length(), str.length() * 5, 5);
            p.playSound(p.getLocation(), ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }
        return true;
    }
}

