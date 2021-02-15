package dev.minecode.language.bungeecord.listener;

import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.bungeecord.LanguageBungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerListener implements Listener {

    public PlayerListener() {
        LanguageBungeeCord.getInstance().getProxy().getPluginManager().registerListener(LanguageBungeeCord.getInstance(), this);
    }

    @EventHandler
    public void handlePlayerJoin(PostLoginEvent event) {
        ProxiedPlayer proxiedPlayer = event.getPlayer();

        if (LanguageAPI.getInstance().isUsingGUI()) {
            if (LanguageAPI.getInstance().isForceOpenInventory()) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

                try {
                    dataOutputStream.writeUTF("Language");
                    dataOutputStream.writeUTF("OpenLanguageChangeGUI");
                } catch (IOException ignored) {
                }

                proxiedPlayer.sendData("MineCode", byteArrayOutputStream.toByteArray());
            }
        }
    }

}
