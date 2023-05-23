package dev.jqstln.ghostplayers;

import org.bukkit.plugin.java.JavaPlugin;

public final class GhostPlayers extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("GhostPlayers has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("GhostPlayers has been disabled!");
    }
}