package dev.minecode.language.api;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlugin;
import dev.minecode.language.api.manager.FileManager;

public abstract class LanguageAPI {

    // Instance
    private static LanguageAPI instance;

    public static LanguageAPI getInstance() {
        return instance;
    }

    public static void setInstance(LanguageAPI instance) {
        LanguageAPI.instance = instance;
    }


    // CoreAPI
    private static CoreAPI getCoreAPI() {
        return CoreAPI.getInstance();
    }


    // Manager
    public abstract FileManager getFileManager();


    // Getter & Setter
    public abstract CorePlugin getThisCorePlugin();

    public abstract boolean isUsingGUI();

    public abstract boolean isForceOpenInventory();
}
