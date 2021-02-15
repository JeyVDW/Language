package dev.minecode.language.spigot.listener;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlayer;
import dev.minecode.language.spigot.LanguageSpigot;
import org.bukkit.entity.Player;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class PluginMessageListener implements org.bukkit.plugin.messaging.PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (!channel.equals("MineCode")) return;

        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);

            String subChannel = dataInputStream.readUTF();

            if (!subChannel.equals("Language")) return;

            if (dataInputStream.readUTF().equals("OpenLanguageChangeGUI")) {
                CorePlayer corePlayer = CoreAPI.getInstance().getCorePlayer(player.getUniqueId());
                player.openInventory(LanguageSpigot.getInstance().getInventoryManager().getLanguageInventory().get(corePlayer.getLanguage()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
