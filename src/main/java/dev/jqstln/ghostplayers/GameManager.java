package dev.jqstln.ghostplayers;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GameManager {

    private final GhostPlayers plugin;
    private final CommandManager commandManager;

    private final HashSet<UUID> hiddenPlayers = new HashSet<>();

    public GameManager(GhostPlayers plugin) {
        this.plugin = plugin;

        this.commandManager = new CommandManager(this);
    }

    public GhostPlayers getPlugin() {
        return this.plugin;
    }

    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    public HashSet<UUID> getHiddenPlayers() {
        return hiddenPlayers;
    }
}
