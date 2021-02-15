package dev.minecode.language.bungeecord;

import dev.minecode.core.bungeecord.CoreBungeeCord;
import dev.minecode.language.bungeecord.command.LanguageCommand;
import dev.minecode.language.bungeecord.listener.PlayerListener;
import dev.minecode.language.common.LanguageCommon;
import net.md_5.bungee.api.plugin.Plugin;

public class LanguageBungeeCord extends Plugin {
    private static LanguageBungeeCord instance;

    private CoreBungeeCord coreBungeeCord;
    private LanguageCommon languageCommon;

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

    private void makeInstances() {
        instance = this;
        coreBungeeCord = new CoreBungeeCord(this);
        languageCommon = new LanguageCommon();
    }

    public void onDisable() {
        CoreBungeeCord.getInstance().onDisable();
    }

    private void registerCommands() {
        getProxy().getPluginManager().registerCommand(this, new LanguageCommand("language"));
    }

    private void registerListeners() {
        new PlayerListener();
    }

    private void registerChannels() {
        getProxy().registerChannel("MineCode");
    }
}
