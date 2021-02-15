package dev.minecode.language.spigot.object;

import dev.minecode.core.api.object.LanguageAbstract;

public enum LanguageLanguageSpigot implements LanguageAbstract {

    noPermission("noPermission"),
    noPlayer("noPlayer"),
    playerNotOnline("playerNotOnline"),
    playerNotExists("playerNotExists"),
    noValidIsocode("noValidIsocode"),
    syntax("syntax"),

    languageCommandSyntaxChoose("language", "command", "syntax", "choose"),
    languageCommandSyntaxSet("language", "command", "syntax", "set"),
    languageCommandChange("language", "command", "change"),
    languageCommandLanguageCollection("language", "command", "languageCollection"),
    languageCommandLanguageSelection("language", "command", "languageSelection"),

    languageGuiTitle("language", "gui", "title"),
    languageGuiPlaceholderItemMaterial("language", "gui", "placeholderItem", "material"),
    languageGuiPlaceholderItemSubID("language", "gui", "placeholderItem", "subID"),
    languageGuiPlaceholderItemLore("language", "gui", "placeholderItem", "lore"),
    languageGuiPlaceholderItemDisplayname("language", "gui", "placeholderItem", "displayname"),

    languageHoverText("language", "hover", "text");

    private String[] path;

    LanguageLanguageSpigot(String... path) {
        this.path = path;
    }

    public String[] getPath() {
        return path;
    }
}
