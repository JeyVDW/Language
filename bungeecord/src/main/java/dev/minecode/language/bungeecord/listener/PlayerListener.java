package dev.minecode.language.bungeecord.listener;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlayer;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.bungeecord.LanguageBungeeCord;
import dev.minecode.language.bungeecord.helper.PluginMessageHelper;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.spongepowered.configurate.ConfigurationNode;

import java.util.concurrent.TimeUnit;

public class PlayerListener implements Listener {
    @EventHandler
    public void handlePlayerJoin(PostLoginEvent event) {
        ProxiedPlayer proxiedPlayer = event.getPlayer();

        if (LanguageAPI.getInstance().isForceOpenInventory()) {
            if (CoreAPI.getInstance().getPlayerManager().getCorePlayer(proxiedPlayer.getUniqueId()).isLanguageEmpty()) {
                ProxyServer.getInstance().getScheduler().schedule(LanguageBungeeCord.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        PluginMessageHelper.openLanguageChangeGUI(proxiedPlayer);
                    }
                }, 1, TimeUnit.SECONDS);
            }
        }
    }
}
