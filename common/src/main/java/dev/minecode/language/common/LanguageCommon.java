package dev.minecode.language.common;

import dev.minecode.language.common.api.LanguageAPIProvider;

public class LanguageCommon {

    private static LanguageCommon instance;

    private LanguageAPIProvider languageAPIProvider;

    public LanguageCommon() {
        makeInstances();
    }

    public static LanguageCommon getInstance() {
        return instance;
    }

    private void makeInstances() {
        instance = this;
        languageAPIProvider = new LanguageAPIProvider();
    }
}
