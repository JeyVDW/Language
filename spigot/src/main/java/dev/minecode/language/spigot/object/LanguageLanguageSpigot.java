package dev.minecode.language.spigot.object;

import dev.minecode.core.api.object.LanguageAbstract;

public enum LanguageLanguageSpigot implements LanguageAbstract {

    languageCommandNoPermission("language", "command", "noPermission"),
    languageCommandNoPlayer("language", "command", "noPlayer"),
    languageCommandSyntaxInfo("language", "command", "syntax", "info"),
    languageCommandSyntaxChoose("language", "command", "syntax", "choose"),
    languageCommandSyntaxSet("language", "command", "syntax", "set"),
    languageCommandNoValidIsocode("language", "command", "noValidIsocode"),
    languageCommandAlreadyChosen("language", "command", "alreadyChosen"),
    languageCommandChange("language", "command", "change"),
    languageCommandLanguageSelection("language", "command", "languageSelection"),
    languageCommandLanguageCollection("language", "command", "languageCollection"),

    languageEventLocaleChanged("language", "event", "localeChanged"),

    languageGuiTitle("language", "gui", "title"),
    languageGuiPlaceholderItemMaterial("language", "gui", "placeholderItem", "material"),
    languageGuiPlaceholderItemSubID("language", "gui", "placeholderItem", "subID"),
    languageGuiPlaceholderItemLore("language", "gui", "placeholderItem", "lore"),
    languageGuiPlaceholderItemDisplayname("language", "gui", "placeholderItem", "displayname"),

    languageHoverLanguageChoose("language", "hover", "languageChoose"),
    languageHoverAdaptLanguageFromClient("language", "hover", "adaptLanguageFromClient");

    private final String[] path;

    LanguageLanguageSpigot(String... path) {
        this.path = path;
    }

    public String[] getPath() {
        return path;
    }
}
