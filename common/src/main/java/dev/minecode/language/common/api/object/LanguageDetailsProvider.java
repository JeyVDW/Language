package dev.minecode.language.common.api.object;

import dev.minecode.core.api.object.Language;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.api.object.LanguageDetails;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.ArrayList;
import java.util.List;

public class LanguageDetailsProvider implements LanguageDetails {

    private final Language language;
    private int slot;
    private List<String> lore;
    private String texture;

    public LanguageDetailsProvider(Language language) {
        this.language = language;
        makeInstances();
    }

    private void makeInstances() {
        ConfigurationNode configNode = LanguageAPI.getInstance().getFileManager().getConfig().getConf().node("languageDetails", this.language.getIsocode());

        slot = configNode.node("slot").getInt();
        try {
            lore = configNode.node("lore").getList(String.class);
        } catch (SerializationException e) {
            lore = new ArrayList<>();
        }
        this.texture = configNode.node("texture").getString();
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public List<String> getLore() {
        return lore;
    }

    @Override
    public String getTexture() {
        return texture;
    }
}
