package com.github.risbun.juanchat.commands;

import com.github.risbun.juanchat.utils.Formatting;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CmdShow implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            final ItemStack item = player.getInventory().getItemInMainHand();
            final Material type = item.getType();

            // TODO: fix enchantments again spigot bad

            final TextComponent start = new TextComponent(String.format("%s%s[%s", Formatting.chatFormat(player, ""), ChatColor.GRAY, ChatColor.WHITE));
            final TranslatableComponent message = new TranslatableComponent(String.format("%s.%s.%s", type.isBlock() ? "block" : "item", type.getKey().getNamespace(), type.getKey().getKey()));
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new Item(type.getKey().toString(), item.getAmount(), ItemTag.ofNbt("{}"))));
            final TextComponent end = new TextComponent(ChatColor.GRAY + "]");

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.spigot().sendMessage(start, message, end);
            }
        } else {
            sender.sendMessage("You can't run this command as console.");
        }
        return true;
    }
}
