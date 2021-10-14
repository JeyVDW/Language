package dev.minecode.language.spigot.listener;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlayer;
import dev.minecode.core.api.object.CorePlugin;
import dev.minecode.core.api.object.Language;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.api.object.LanguageDetails;
import dev.minecode.language.spigot.LanguageSpigot;
import dev.minecode.language.spigot.object.HeadUtil;
import dev.minecode.language.spigot.object.LanguageLanguageSpigot;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryListener implements Listener {

    private final CorePlugin corePlugin = LanguageAPI.getInstance().getThisCorePlugin();

    public InventoryListener() {
        Bukkit.getPluginManager().registerEvents(this, LanguageSpigot.getInstance());
    }

    @EventHandler
    public void handleInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        Player player = (Player) event.getWhoClicked();
        CorePlayer corePlayer = CoreAPI.getInstance().getPlayerManager().getPlayer(player.getUniqueId());
        if (!event.getView().getTitle().equals(CoreAPI.getInstance().getReplaceManager(corePlayer.getLanguage(corePlugin), LanguageLanguageSpigot.languageGuiTitle).chatcolorAll().getMessage()))
            return;
        event.setCancelled(true);
        ItemStack item = event.getCurrentItem();
        Language language = getLanguageByItem(item);
        if (language == null) return;
        Language oldLanguage = corePlayer.getLanguage(corePlugin);

        if (language == oldLanguage) {
            player.closeInventory();
            player.sendMessage(CoreAPI.getInstance().getReplaceManager(corePlayer.getLanguage(corePlugin), LanguageLanguageSpigot.languageCommandAlreadyChosen)
                    .language(language, "language").chatcolorAll().getMessage());
            return;
        }

        corePlayer.setLanguage(language.getIsocode());
        corePlayer.save();
        player.closeInventory();

        player.sendMessage(CoreAPI.getInstance().getReplaceManager(language, LanguageLanguageSpigot.languageCommandChange)
                .language(oldLanguage, "oldLanguage")
                .language(language, "language").chatcolorAll().getMessage());
    }

    private Language getLanguageByItem(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemStack.getType() != Material.PLAYER_HEAD)
            return null;
        for (Language language : CoreAPI.getInstance().getLanguageManager().getAllLanguages(corePlugin)) {
            LanguageDetails languageDetails = LanguageAPI.getInstance().getLanguageDetailsManager().getLanguageDetail(language);
            if (languageDetails.getTexture().equals(HeadUtil.getTexture(itemMeta)))
                return language;
        }
        return null;
    }
}