package dev.minecode.language.spigot;

import dev.minecode.core.api.CoreAPI;
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

    private CoreSpigot coreSpigot;
    private LanguageCommon languageCommon;
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

    private void makeInstances() {
        instance = this;
        coreSpigot = new CoreSpigot(this);
        languageCommon = new LanguageCommon();
        inventoryManager = new InventoryManager();
    }

    public void onDisable() {
        if (CoreAPI.getInstance().isUsingSQL())
            CoreAPI.getInstance().getDatabaseManager().disconnect();
    }

    private void registerCommands() {
        new LanguageCommand();
    }

    private void registerListeners() {
        new InventoryListener();
        new PlayerListener();
    }

    private void registerChannels() {
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "MineCode", new PluginMessageListener());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "MineCode");
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}
