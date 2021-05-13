package dev.minecode.language.spigot.object;

import com.google.common.collect.Multimap;
import com.mojang.authlib.GameProfile;
import dev.minecode.language.spigot.LanguageSpigot;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;

public class HeadUtil {
    private static Method GET_PROPERTIES;
    private static Method INSERT_PROPERTY;
    private static Constructor<?> GAME_PROFILE_CONSTRUCTOR;
    private static Constructor<?> PROPERTY_CONSTRUCTOR;

    static {
        try {
            Class<?> gameProfile = Class.forName("com.mojang.authlib.GameProfile");
            Class<?> property = Class.forName("com.mojang.authlib.properties.Property");
            Class<?> propertyMap = Class.forName("com.mojang.authlib.properties.PropertyMap");
            GAME_PROFILE_CONSTRUCTOR = Reflector.getConstructor(gameProfile, 2);
            PROPERTY_CONSTRUCTOR = Reflector.getConstructor(property, 2);
            GET_PROPERTIES = Reflector.getMethod(gameProfile, "getProperties");
            INSERT_PROPERTY = Reflector.getMethod(propertyMap, "put");
        } catch (Exception var3) {
            LanguageSpigot.getInstance().getLogger().log(Level.SEVERE, "Failed to load CustomHead:", var3);
            Bukkit.shutdown();
        }

    }

    public HeadUtil() {
    }

    public static GameProfile getGameProfile(String texture, String id, String name) {
        try {

            Object profile = GAME_PROFILE_CONSTRUCTOR.newInstance(UUID.fromString(id), name);
            Object properties = GET_PROPERTIES.invoke(profile);
            INSERT_PROPERTY.invoke(properties, "textures", PROPERTY_CONSTRUCTOR.newInstance("textures", texture));
            return (GameProfile) profile;
        } catch (Exception var8) {
            System.err.println("Failed to create fake GameProfile for custom player head:");
            var8.printStackTrace();
        }
        return null;
    }

    public static ItemStack getHead(String texture, UUID id, String name) {
        Objects.requireNonNull(texture);
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        try {
            Object profile = GAME_PROFILE_CONSTRUCTOR.newInstance(id, name);
            Object properties = GET_PROPERTIES.invoke(profile);
            INSERT_PROPERTY.invoke(properties, "textures", PROPERTY_CONSTRUCTOR.newInstance("textures", texture));
            Reflector.setFieldValue(meta, "profile", profile);
        } catch (Exception var8) {
            System.err.println("Failed to create fake GameProfile for custom player head:");
            var8.printStackTrace();
        }

        item.setItemMeta(meta);
        return item;
    }

    public static String getTexture(ItemMeta meta) {
        try {
            Object profile = Reflector.getFieldValue(meta, "profile");
            Multimap<String, Object> properties = (Multimap) Reflector.getFieldValue(profile, "properties");
            List<Object> textures = new ArrayList(properties.get("textures"));
            if (textures.size() < 1) {
                return null;
            } else {
                Object property = textures.get(0);
                return (String) Reflector.getFieldValue(property, "value");
            }
        } catch (IllegalAccessException | NoSuchFieldException var5) {
            var5.printStackTrace();
            return null;
        }
    }
}
