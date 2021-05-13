package dev.minecode.language.spigot.listener;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlayer;
import dev.minecode.core.api.object.Language;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.spigot.LanguageSpigot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;

public class PluginMessageListener implements org.bukkit.plugin.messaging.PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player messagePlayer, byte[] bytes) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

            String identifier = dataInputStream.readUTF();

            if (identifier.equals("OpenLanguageChangeGUI")) {
                String playerUUID = dataInputStream.readUTF();
                Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));
                CorePlayer corePlayer = CoreAPI.getInstance().getPlayerManager().getPlayer(player.getUniqueId());

                Language language = corePlayer.getLanguage(LanguageAPI.getInstance().getThisCorePlugin());

                player.openInventory(LanguageSpigot.getInstance().getInventoryManager().getLanguageInventory().get(language));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
