package com.github.thacheesebun.juanchat;

import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Map;

import static com.github.thacheesebun.juanchat.Main.*;
import static org.bukkit.ChatColor.*;

public class CommandShow implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            ChatColor color = valueOf(config.getString("color"));
            Player player = (Player)sender;
            StringBuilder builder = new StringBuilder();
            String str = "";
            StringBuilder Info = new StringBuilder();
            if (args.length > 0) {
                for (String s : args) {
                    builder.append(s).append(" ");
                }
                str = builder.toString();
            }
            PlayerInventory inventory = player.getInventory();
            ItemStack itemStack = inventory.getItemInMainHand();
            Material itemType = itemStack.getType();
            Map<Enchantment, Integer> map = itemStack.getEnchantments();
            for (Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
                Info.append(entry.getKey().getKey().getKey().replace("_", " ")).append(" ").append(entry.getValue()).append("\n");
            }
            TextComponent message = new TextComponent(String.format("%s%s%s: %s%s%s%s[%s%s%s] ", color, player.getDisplayName(), GRAY, ITALIC, str, RESET, GRAY, WHITE, itemType.name().replace("_", " "), GRAY));
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(Info.toString())));
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.spigot().sendMessage(message);
            }
        } else sender.sendMessage("You can't run this as console!");
        return true;
    }
}
