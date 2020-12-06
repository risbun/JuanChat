package com.github.risbun.juanchat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class CommandTogglemod implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p.getMetadata("juanchat_hidden").size() != 0) {
            p.removeMetadata("juanchat_hidden", Main.plugin);
            sender.sendMessage("Mod mode is now turned on");
        } else {
            p.setMetadata("juanchat_hidden", new FixedMetadataValue(Main.plugin, true));
            sender.sendMessage("Mod mode is now turned off");
        }
        return true;
    }
}
