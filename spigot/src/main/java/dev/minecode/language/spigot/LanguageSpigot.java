package dev.minecode.language.spigot;

import dev.minecode.core.spigot.CoreSpigot;
import dev.minecode.language.common.LanguageCommon;
import dev.minecode.language.spigot.command.LanguageCommand;
import dev.minecode.language.spigot.listener.InventoryListener;
import dev.minecode.language.spigot.listener.PlayerListener;
import dev.minecode.language.spigot.listener.PluginMessageListener;
import dev.minecode.language.spigot.manager.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LanguageSpigot extends JavaPlugin {

    private static LanguageSpigot instance;

    private InventoryManager inventoryManager;

    public static LanguageSpigot getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        makeInstances();
        registerCommands();
        registerListeners();
        registerChannels();
    }

    @Override
    public void onDisable() {
        CoreSpigot.getInstance().onDisable();
    }

    private void makeInstances() {
        instance = this;
        CoreSpigot.getInstance().registerPlugin(this, true);
        new LanguageCommon();
        inventoryManager = new InventoryManager();
    }

    private void registerCommands() {
        new LanguageCommand(getCommand("language"));
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    private void registerChannels() {
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "minecode:language", new PluginMessageListener());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "minecode:language");
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}
