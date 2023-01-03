package dev.minecode.language.spigot.manager;

import dev.minecode.core.api.CoreAPI;
import dev.minecode.core.api.object.CorePlugin;
import dev.minecode.core.api.object.Language;
import dev.minecode.language.api.LanguageAPI;
import dev.minecode.language.api.object.LanguageDetails;
import dev.minecode.language.spigot.object.HeadUtil;
import dev.minecode.language.spigot.object.ItemBuilder;
import dev.minecode.language.spigot.object.LanguageLanguageSpigot;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class InventoryManager {

    private final CorePlugin corePlugin = LanguageAPI.getInstance().getThisCorePlugin();

    private final HashMap<Language, Inventory> languageInventory;
    private final int languageInventorySize;

    public InventoryManager() {
        languageInventory = new HashMap<>();
        languageInventorySize = getLanguageInventorySize();
        for (Language language : CoreAPI.getInstance().getLanguageManager().getLanguages(corePlugin)) {
            languageInventory.put(language, createLanguageInventory(language));

        }
    }

    private Inventory createLanguageInventory(Language language) {
        Inventory inventory = Bukkit.createInventory(null, languageInventorySize, CoreAPI.getInstance().getReplaceManager(language, LanguageLanguageSpigot.languageGuiTitle).chatcolorAll().getMessage());

//        for (int i = 0; i < languageInventorySize; i++) {
//            inventory.setItem(i, new ItemBuilder(Material.valueOf(CoreAPI.getInstance().getLanguageManager().getString(language, LanguageLanguageSpigot.languageGuiPlaceholderItemMaterial)),
//                    (short) CoreAPI.getInstance().getLanguageManager().getInt(language, LanguageLanguageSpigot.languageGuiPlaceholderItemSubID))
//                    .setLore(CoreAPI.getInstance().getLanguageManager().getStringList(language, LanguageLanguageSpigot.languageGuiPlaceholderItemLore))
//                    .setDisplayName(CoreAPI.getInstance().getReplaceManager(language, LanguageLanguageSpigot.languageGuiPlaceholderItemDisplayname).chatcolorAll().getMessage()).build());
//        }

        for (Language allLanguages : CoreAPI.getInstance().getLanguageManager().getLanguages(corePlugin)) {
            LanguageDetails languageDetails = LanguageAPI.getInstance().getLanguageDetailsManager().getLanguageDetail(allLanguages);
            String displayname = CoreAPI.getInstance().getReplaceManager(allLanguages.getDisplayname())
                    .language(allLanguages, "").chatcolorAll().getMessage();
            List<String> lore = new ArrayList<>();
            for (String s : languageDetails.getLore())
                lore.add(CoreAPI.getInstance().getReplaceManager(s).language(allLanguages, "").chatcolorAll().getMessage());

            String skullTexture = languageDetails.getTexture();
            ItemStack itemStack = new ItemBuilder(HeadUtil.getHead(skullTexture, UUID.randomUUID(), "Skull")).setDisplayName(displayname).setLore(lore).build();

            inventory.setItem(languageDetails.getSlot() - 1, itemStack);
        }
        return inventory;
    }

    private int getLanguageInventorySize() {
        int highest = 9;
        for (Language language : CoreAPI.getInstance().getLanguageManager().getLanguages(corePlugin)) {
            LanguageDetails languageDetails = LanguageAPI.getInstance().getLanguageDetailsManager().getLanguageDetail(language);
            int ici = languageDetails.getSlot();
            while (highest < ici) {
                highest = highest + 9;
            }
        }
        return highest;
    }

    public HashMap<Language, Inventory> getLanguageInventory() {
        return languageInventory;
    }
}