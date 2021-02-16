package dev.minecode.language.bungeecord.command;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlayer;
import dev.minecode.core.api.object.Language;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.bungeecord.helper.PluginMessageHelper;
import dev.minecode.language.bungeecord.object.LanguageLanguageBungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LanguageCommand extends Command implements TabExecutor {
    public LanguageCommand(String name) {
        super(name);
    }

    public LanguageCommand(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        CorePlayer coreExecuter = CoreAPI.getInstance().getCorePlayer(commandSender.getName());

        if (!commandSender.hasPermission("language.use")) {
            commandSender.sendMessage(CoreAPI.getInstance().getReplaceManager(coreExecuter.getLanguage(), LanguageLanguageBungeeCord.noPermission)
                    .args("language", args, "arg").chatcolorAll().getBaseMessage());
            return;
        }

        if (args.length == 0) {
            if (!(commandSender instanceof ProxiedPlayer)) {
                commandSender.sendMessage(CoreAPI.getInstance().getReplaceManager(coreExecuter.getLanguage(), LanguageLanguageBungeeCord.noPlayer)
                        .args("language", args, "arg").chatcolorAll().getBaseMessage());
                return;
            }

            if (LanguageAPI.getInstance().isUsingGUI()) {
                PluginMessageHelper.openLanguageChangeGUI((ProxiedPlayer) commandSender);
                return;
            }

            commandSender.sendMessage(CoreAPI.getInstance().getReplaceManager(coreExecuter.getLanguage(), LanguageLanguageBungeeCord.languageCommandLanguageSelection).chatcolorAll().getBaseMessage());

            String repeat = CoreAPI.getInstance().getReplaceManager(coreExecuter.getLanguage(), LanguageLanguageBungeeCord.languageCommandLanguageCollection).chatcolorAll().getMessage();
            String isocode;
            for (Language language : CoreAPI.getInstance().getLanguages()) {
                isocode = language.getIsocode();
                BaseComponent[] b = CoreAPI.getInstance().getReplaceManager(repeat).language(CoreAPI.getInstance().getLanguage(isocode), "language").chatcolorAll().getBaseMessage();
                for (BaseComponent baseComponent : b) {
                    baseComponent.setClickEvent(
                            new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/language " + isocode));
                    baseComponent.setHoverEvent(
                            new HoverEvent(
                                    HoverEvent.Action.SHOW_TEXT,
                                    CoreAPI.getInstance().getReplaceManager(coreExecuter.getLanguage(), LanguageLanguageBungeeCord.languageHoverText)
                                            .language(CoreAPI.getInstance().getLanguage(isocode), "language")
                                            .chatcolorAll().getBaseMessage()));
                }
                commandSender.sendMessage(b);
            }
            return;
        }

        if (args.length == 1) {
            String isocode = args[0];
            Language language;
            if ((language = CoreAPI.getInstance().getLanguage(isocode)) == null) {
                commandSender.sendMessage(CoreAPI.getInstance().getReplaceManager(coreExecuter.getLanguage(), LanguageLanguageBungeeCord.noValidIsocode)
                        .args("language", args, "arg").chatcolorAll().getBaseMessage());
                return;
            }

            Language oldLanguage = coreExecuter.getLanguage();
            if (oldLanguage == null)
                oldLanguage = CoreAPI.getInstance().getDefaultLanguage();
            coreExecuter.setLanguage(language);
            coreExecuter.save();

            commandSender.sendMessage(CoreAPI.getInstance().getReplaceManager(coreExecuter.getLanguage(), LanguageLanguageBungeeCord.languageCommandChange)
                    .language(coreExecuter.getLanguage(), "language")
                    .language(oldLanguage, "oldLanguage")
                    .args("language", args, "arg").chatcolorAll().getBaseMessage());
            return;
        }

        commandSender.sendMessage(CoreAPI.getInstance().getReplaceManager(coreExecuter.getLanguage(), LanguageLanguageBungeeCord.syntax).chatcolorAll().getBaseMessage());
        commandSender.sendMessage(CoreAPI.getInstance().getReplaceManager(coreExecuter.getLanguage(), LanguageLanguageBungeeCord.languageCommandSyntaxChoose).chatcolorAll().getBaseMessage());
        commandSender.sendMessage(CoreAPI.getInstance().getReplaceManager(coreExecuter.getLanguage(), LanguageLanguageBungeeCord.languageCommandSyntaxSet).chatcolorAll().getBaseMessage());
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {
        Set<String> tab = new HashSet();
        List<String> list = new ArrayList<>();
        String search = null;

        if (!commandSender.hasPermission("language.use")) {
            return tab;
        }

        if (args.length == 1) {
            for (Language language : CoreAPI.getInstance().getLanguages()) {
                list.add(language.getIsocode());
            }
            search = args[0].toLowerCase();
        }

        for (String start : list) {
            if (start.toLowerCase().startsWith(search))
                tab.add(start);
        }

        return tab;
    }
}
