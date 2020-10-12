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

public class CommandShow implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            ChatColor color = ChatColor.valueOf(Main.config.getString("color"));
            final Player player = (Player)sender;
            final StringBuilder builder = new StringBuilder();
            String str = "";
            StringBuilder Info = new StringBuilder();
            if (args.length > 0) {
                for (final String s : args) {
                    builder.append(s).append(" ");
                }
                str = builder.toString();
            }
            final PlayerInventory inventory = player.getInventory();
            final ItemStack itemStack = inventory.getItemInMainHand();
            final Material itemType = itemStack.getType();
            final Map<Enchantment, Integer> map = itemStack.getEnchantments();
            for (final Map.Entry<Enchantment, Integer> entry : map.entrySet()) {
                Info.append(entry.getKey().getKey().getKey().replace("_", " ")).append(" ").append(entry.getValue()).append("\n");
            }
            final String text = color + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.ITALIC + str + ChatColor.RESET + ChatColor.GRAY + "[" + ChatColor.WHITE + itemType.name().replace("_", " ") + ChatColor.GRAY + "] ";
            final TextComponent message = new TextComponent(text);
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(Info.toString())));
            for (final Player p : Bukkit.getOnlinePlayers()) {
                p.spigot().sendMessage(message);
            }
        } else sender.sendMessage("You can't run this as console!");
        return true;
    }
}
