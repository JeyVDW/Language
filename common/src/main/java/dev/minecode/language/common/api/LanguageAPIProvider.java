package dev.minecode.language.common.api;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlugin;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.api.manager.FileManager;
import dev.minecode.language.api.manager.LanguageDetailsManager;
import dev.minecode.language.common.api.manager.FileManagerProvider;
import dev.minecode.language.common.api.manager.LanguageDetailsManagerProvider;
import org.spongepowered.configurate.ConfigurationNode;

public class LanguageAPIProvider extends LanguageAPI {

    private FileManagerProvider fileManager;
    private LanguageDetailsManagerProvider languageDetailsManager;

    private CorePlugin thisCorePlugin;
    private boolean usingGUI;
    private boolean forceOpenInventory;

    public LanguageAPIProvider() {
        makeInstances();
    }

    private void makeInstances() {
        LanguageAPI.setInstance(this);

        thisCorePlugin = CoreAPI.getInstance().getPluginManager().getPlugin("Language");
        fileManager = new FileManagerProvider();
        languageDetailsManager = new LanguageDetailsManagerProvider();

        ConfigurationNode configNode = fileManager.getConfig().getConf();
        usingGUI = configNode.node("usingGUI").getBoolean();
        forceOpenInventory = configNode.node("forceOpenInventory").getBoolean();
    }

    @Override
    public FileManager getFileManager() {
        return fileManager;
    }

    @Override
    public LanguageDetailsManager getLanguageDetailsManager() {
        return languageDetailsManager;
    }

    @Override
    public CorePlugin getThisCorePlugin() {
        return thisCorePlugin;
    }

    @Override
    public boolean isUsingGUI() {
        return usingGUI;
    }

    @Override
    public boolean isForceOpenInventory() {
        return forceOpenInventory;
    }
}
