package dev.minecode.language.common.api;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlugin;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.api.manager.FileManager;
import dev.minecode.language.common.api.manager.FileManagerProvider;
import org.spongepowered.configurate.ConfigurationNode;

public class LanguageAPIProvider extends LanguageAPI {

    private FileManager fileManager;

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

        ConfigurationNode configNode = fileManager.getConfig().getConf();
        usingGUI = configNode.node("usingGUI").getBoolean();
        forceOpenInventory = configNode.node("forceOpenInventory").getBoolean();
    }

    @Override
    public FileManager getFileManager() {
        return fileManager;
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
