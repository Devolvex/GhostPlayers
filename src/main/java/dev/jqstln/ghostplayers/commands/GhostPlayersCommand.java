package dev.jqstln.ghostplayers.commands;

import cloud.commandframework.annotations.CommandDescription;
import cloud.commandframework.annotations.CommandMethod;
import dev.jqstln.ghostplayers.GameManager;
import dev.jqstln.ghostplayers.GhostPlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

public class GhostPlayersCommand {

    private final GameManager gameManager;

    public GhostPlayersCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @CommandMethod("ghostplayers")
    @CommandDescription("Base command for GhostPlayers")
    private void ghostPlayersCommand(final CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED +"Only players can use this command!");
            return;
        }
        sender.sendMessage(showPrefix() + ChatColor.GREEN + "Information about the plugin (not yet implemented)");
    }

    @CommandMethod("ghostplayers reload")
    @CommandDescription("Reloads GhostPlayers")
    private void ghostPlayersReloadCommand(final CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return;
        }
        sender.sendMessage(this.showPrefix() + ChatColor.YELLOW + "Configuration has been reloaded!");
        this.reloadConfiguration();
    }

    @CommandMethod("ghostplayers hide")
    @CommandDescription("Hides Players")
    private void ghostPlayersHideCommand(final CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return;
        }
        Player player = (Player) sender;

        HashSet<UUID> hiddenPlayers = this.gameManager.getHiddenPlayers();

        if (!hiddenPlayers.contains(player.getUniqueId())) {
            hiddenPlayers.add(player.getUniqueId());
            player.sendMessage("Other players are now hidden from your view.");
        }
        this.updatePlayerVisibility(hiddenPlayers, player);

        player.sendMessage(this.showPrefix() + ChatColor.YELLOW + "Players have been hidden!");
        player.sendMessage("hidden players:" + hiddenPlayers);
    }

//    @CommandMethod("ghostplayers show")
//    @CommandDescription("Hides Players")
//    private void ghostPlayersShowCommand(final CommandSender sender) {
//        if (!(sender instanceof Player)) {
//            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
//            return;
//        }
//        Player player = (Player) sender;
//
//        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
//            player.showPlayer(this.plugin, onlinePlayer);
//        }
//
//        player.sendMessage(this.showPrefix() + ChatColor.YELLOW + "Players have been shown!");
//    }

    private void reloadConfiguration() {
        this.gameManager.getPlugin().reloadConfig();
    }

    public String showPrefix() {
        return ChatColor.translateAlternateColorCodes('&', "&2&lGP&r &8| ");
    }

    private void updatePlayerVisibility(HashSet<UUID> hiddenPlayers, Player player) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (player.equals(onlinePlayer)) {
                continue;
            }

            if (hiddenPlayers.contains(onlinePlayer.getUniqueId())) {
                player.hidePlayer(this.gameManager.getPlugin(), onlinePlayer);
            } else {
                player.showPlayer(this.gameManager.getPlugin(), onlinePlayer);
            }
        }
    }
}
