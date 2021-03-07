package dev.minecode.language.bungeecord;

import dev.minecode.core.bungeecord.CoreBungeeCord;
import dev.minecode.language.bungeecord.command.LanguageCommand;
import dev.minecode.language.bungeecord.listener.PlayerListener;
import dev.minecode.language.common.LanguageCommon;
import net.md_5.bungee.api.plugin.Plugin;

public class LanguageBungeeCord extends Plugin {
    private static LanguageBungeeCord instance;

    public static LanguageBungeeCord getInstance() {
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
        CoreBungeeCord.getInstance().onDisable();
    }

    private void makeInstances() {
        instance = this;
        new CoreBungeeCord(this);
        new LanguageCommon();
    }

    private void registerCommands() {
        getProxy().getPluginManager().registerCommand(this, new LanguageCommand("language", null, "lang"));
    }

    private void registerListeners() {
        getProxy().getPluginManager().registerListener(this, new PlayerListener());
    }

    private void registerChannels() {
        getProxy().registerChannel("MineCode");
    }
}
