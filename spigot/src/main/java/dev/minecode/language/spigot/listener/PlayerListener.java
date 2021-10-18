package dev.minecode.language.spigot.listener;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlayer;
import dev.minecode.core.api.object.CorePlugin;
import dev.minecode.core.api.object.Language;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.spigot.LanguageSpigot;
import dev.minecode.language.spigot.object.LanguageLanguageSpigot;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

public class PlayerListener implements Listener {

    public PlayerListener() {
        Bukkit.getPluginManager().registerEvents(this, LanguageSpigot.getInstance());
    }

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (LanguageAPI.getInstance().isForceOpenInventory())
            if (CoreAPI.getInstance().getPlayerManager().getPlayer(player.getUniqueId()).isLanguageEmpty())
                Bukkit.getScheduler().runTaskLater(LanguageSpigot.getInstance(), () ->
                        player.openInventory(LanguageSpigot.getInstance().getInventoryManager().getLanguageInventory().get(CoreAPI.getInstance().getLanguageManager().getDefaultLanguage(LanguageAPI.getInstance().getThisCorePlugin()))), 10);
    }

    @EventHandler
    public void handlePlayerLocaleChange(PlayerLocaleChangeEvent event) {
        CorePlugin corePlugin = LanguageAPI.getInstance().getThisCorePlugin();
        Language clientLanguage = CoreAPI.getInstance().getLanguageManager().getLanguage(corePlugin, event.getLocale());

        if (clientLanguage == null) return;

        Player player = event.getPlayer();
        CorePlayer corePlayer = CoreAPI.getInstance().getPlayerManager().getPlayer(player.getUniqueId());

        Language language = corePlayer.getLanguage(corePlugin);

        if (language == clientLanguage) return;

        BaseComponent[] baseMessage = CoreAPI.getInstance().getReplaceManager(clientLanguage, LanguageLanguageSpigot.languageEventLocaleChanged)
                .language(language, "language")
                .language(clientLanguage, "clientLanguage").chatcolorAll().getBaseMessage();

        for (BaseComponent tempBaseComponent : baseMessage) {
            tempBaseComponent.setClickEvent(
                    new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/language " + clientLanguage.getIsocode()));

            tempBaseComponent.setHoverEvent(new HoverEvent(
                    HoverEvent.Action.SHOW_TEXT,
                    new Text(CoreAPI.getInstance().getReplaceManager(clientLanguage, LanguageLanguageSpigot.languageHoverAdaptLanguageFromClient)
                            .language(language, "language")
                            .language(clientLanguage, "clientLanguage").chatcolorAll().getBaseMessage())));
        }

        player.spigot().sendMessage(baseMessage);
    }

}
