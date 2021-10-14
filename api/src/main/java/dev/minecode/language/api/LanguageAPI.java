package dev.minecode.language.api;

import dev.minecode.core.api.object.CorePlugin;
import dev.minecode.language.api.manager.FileManager;
import dev.minecode.language.api.manager.LanguageDetailsManager;

public abstract class LanguageAPI {

    // Instance
    private static LanguageAPI instance;

    public static LanguageAPI getInstance() {
        return instance;
    }

    public static void setInstance(LanguageAPI instance) {
        LanguageAPI.instance = instance;
    }


    // Manager
    public abstract FileManager getFileManager();

    public abstract LanguageDetailsManager getLanguageDetailsManager();


    // Getter & Setter
    public abstract CorePlugin getThisCorePlugin();

    public abstract boolean isUsingGUI();

    public abstract boolean isForceOpenInventory();
}
