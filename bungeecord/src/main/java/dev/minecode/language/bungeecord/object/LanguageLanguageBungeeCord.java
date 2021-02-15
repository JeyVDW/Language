package dev.minecode.language.bungeecord.object;

import dev.minecode.core.api.object.LanguageAbstract;

public enum LanguageLanguageBungeeCord implements LanguageAbstract {

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
    languageHoverText("language", "hover", "text");

    private String[] path;

    LanguageLanguageBungeeCord(String... path) {
        this.path = path;
    }

    public String[] getPath() {
        return path;
    }
}