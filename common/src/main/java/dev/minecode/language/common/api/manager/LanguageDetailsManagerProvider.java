package dev.minecode.language.common.api.manager;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.Language;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.api.manager.LanguageDetailsManager;
import dev.minecode.language.api.object.LanguageDetails;
import dev.minecode.language.common.api.object.LanguageDetailsProvider;

import java.util.HashMap;

public class LanguageDetailsManagerProvider implements LanguageDetailsManager {

    private final HashMap<Language, LanguageDetails> languageDetails = new HashMap<>();

    public LanguageDetailsManagerProvider() {
        for (Language language : CoreAPI.getInstance().getLanguageManager().getAllLanguages(LanguageAPI.getInstance().getThisCorePlugin()))
            languageDetails.put(language, new LanguageDetailsProvider(language));
    }

    @Override
    public LanguageDetails getLanguageDetail(Language language) {
        if (languageDetails.containsKey(language))
            return languageDetails.get(language);
        return null;
    }
}
