package dev.minecode.language.api.object;

import dev.minecode.core.api.object.Language;

import java.util.List;

public interface LanguageDetails {

    Language getLanguage();

    int getSlot();

    List<String> getLore();

    String getTexture();

}
