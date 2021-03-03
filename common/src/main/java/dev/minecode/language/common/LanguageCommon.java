package dev.minecode.language.common;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.manager.UpdateManager;
import dev.minecode.language.common.api.LanguageAPIProvider;

public class LanguageCommon {

    private static LanguageCommon instance;

    public LanguageCommon() {
        makeInstances();

        UpdateManager updateManager = CoreAPI.getInstance().getUpdateManager();
        if (updateManager.updateAvailable()) {
            System.out.println("[" + CoreAPI.getInstance().getPluginManager().getPluginName() + "] There is a newer Version available! You can download it at " + updateManager.getReleaseURL(updateManager.getMatchingRelease()));
        }
    }

    public static LanguageCommon getInstance() {
        return instance;
    }

    private void makeInstances() {
        instance = this;
        new LanguageAPIProvider();
    }
}
