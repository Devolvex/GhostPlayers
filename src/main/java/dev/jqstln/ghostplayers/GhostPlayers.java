package dev.jqstln.ghostplayers;

import cloud.commandframework.annotations.AnnotationParser;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class GhostPlayers extends JavaPlugin {

    private AnnotationParser<CommandSender> annotationParser = null;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        new GameManager(this);

        getLogger().info("GhostPlayers has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("GhostPlayers has been disabled!");
    }
}
