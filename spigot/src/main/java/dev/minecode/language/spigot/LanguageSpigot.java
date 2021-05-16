package dev.minecode.language.spigot;

import dev.minecode.core.spigot.CoreSpigot;
import dev.minecode.language.common.LanguageCommon;
import dev.minecode.language.spigot.command.LanguageCommand;
import dev.minecode.language.spigot.listener.InventoryListener;
import dev.minecode.language.spigot.listener.PluginMessageListener;
import dev.minecode.language.spigot.manager.InventoryManager;
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
        new LanguageCommand();
    }

    private void registerListeners() {
        new InventoryListener();
        new InventoryListener();
    }

    private void registerChannels() {
        new PluginMessageListener();
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}
