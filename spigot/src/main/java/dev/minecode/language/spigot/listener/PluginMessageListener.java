package dev.minecode.language.spigot.listener;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlayer;
import dev.minecode.core.api.object.Language;
import dev.minecode.language.spigot.LanguageSpigot;
import org.bukkit.entity.Player;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class PluginMessageListener implements org.bukkit.plugin.messaging.PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

            String subChannel = dataInputStream.readUTF();
            String identifier = dataInputStream.readUTF();

            if (subChannel.equals("Language")) {
                if (identifier.equals("OpenLanguageChangeGUI")) {
                    CorePlayer corePlayer = CoreAPI.getInstance().getCorePlayer(player.getUniqueId());
                    Language language = corePlayer.getLanguage();
                    if (language == null)
                        language = CoreAPI.getInstance().getDefaultLanguage();

                    player.openInventory(LanguageSpigot.getInstance().getInventoryManager().getLanguageInventory().get(language));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
