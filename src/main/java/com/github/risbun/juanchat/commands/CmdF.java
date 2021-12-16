package com.github.risbun.juanchat.commands;

import com.github.risbun.juanchat.utils.Formatting;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdF implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Bukkit.broadcastMessage(Formatting.chatFormat(p, "F"));
        }
        return true;
    }
}
