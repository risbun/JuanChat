package com.github.thacheesebun.juanchat;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCalc implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            final StringBuilder builder = new StringBuilder();
            for (final String s : args) {
                builder.append(s);
            }
            String str = builder.toString();
            if (!str.contains("[a-zA-Z]")) {
                String[] calc = null;
                Integer one = null;
                Integer two = null;
                Integer wMath = null;
                boolean minus = false;
                if (str.startsWith("-")) {
                    str = str.substring(1);
                    minus = true;
                }
                if (str.contains("*")) {
                    calc = str.split(Pattern.quote("*"));
                    wMath = 0;
                } else if (str.contains("x")) {
                    calc = str.split(Pattern.quote("*"));
                    wMath = 0;
                } else if (str.contains("+")) {
                    calc = str.split(Pattern.quote("+"));
                    wMath = 1;
                } else if (str.contains("/")) {
                    calc = str.split(Pattern.quote("/"));
                    wMath = 2;
                } else if (str.contains("-")) {
                    calc = str.split(Pattern.quote("-"));
                    wMath = 3;
                } else this.fail(player);
                if (wMath != null) {
                    try {
                        one = Integer.valueOf(calc[0]);
                        two = Integer.valueOf(calc[1]);
                    } catch (NumberFormatException e) {
                        this.fail2(player);
                    }
                }
                if (StringUtils.isNumeric(String.valueOf(one)) && StringUtils.isNumeric(String.valueOf(two))) {
                    float res = 0.0f;
                    if (minus) one = Math.negateExact(one);
                    switch (wMath) {
                        case 0: {
                            res = one * (float)two;
                            break;
                        }
                        case 1: {
                            res = (float)(one + two);
                            break;
                        }
                        case 2: {
                            res = one / (float)two;
                            break;
                        }
                        case 3: {
                            res = (float)(one - two);
                            break;
                        }
                    }
                    if (wMath != null) this.answer(player, res);
                }
            } else this.fail2(player);
        }
        return true;
    }

    public void fail(final CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "You need at least " + ChatColor.RED + "ONE" + ChatColor.GRAY + " of these:  " + ChatColor.YELLOW + "*" + ChatColor.GRAY + ", " + ChatColor.GREEN + "+" + ChatColor.GRAY + ", " + ChatColor.RED + "-" + ChatColor.GRAY + " or " + ChatColor.BLUE + "/" + ChatColor.GRAY + "." + ChatColor.YELLOW + "\nExample: /calc 5+5");
    }

    public void fail2(final CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "You can " + ChatColor.BOLD + ChatColor.RED + "only" + ChatColor.RESET + ChatColor.GRAY + " use numbers! " + ChatColor.UNDERLINE + ChatColor.RED + "No decimals" + ChatColor.RESET + ChatColor.GRAY + "!");
    }

    public void answer(final CommandSender sender, final float val) {
        sender.sendMessage(ChatColor.GRAY + "Answer: " + ChatColor.GREEN + val);
    }
}
