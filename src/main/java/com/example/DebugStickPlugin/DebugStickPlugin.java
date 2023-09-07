package com.example.DebugStickPlugin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class DebugStickPlugin extends JavaPlugin {

    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Создаем папку и конфигурационный файл, если они не существуют
        saveDefaultConfig();

        // Загружаем конфигурацию из файла
        config = getConfig();

        // Проверяем значения в конфигурации и создаем крафты только если они активны
        boolean debugStickCraftEnabled = config.getBoolean("debug_stick", true);
        boolean lightCraftEnabled = config.getBoolean("light", true);

        if (debugStickCraftEnabled) {
            // Создаем рецепт для Debug Stick с использованием NamespacedKey
            NamespacedKey debugStickKey = new NamespacedKey(this, "debug_stick");

            ShapedRecipe debugStickRecipe = new ShapedRecipe(debugStickKey, new ItemStack(Material.DEBUG_STICK, 1));
            debugStickRecipe.shape("123", "456", "789");
            debugStickRecipe.setIngredient('1', Material.CRAFTING_TABLE);
            debugStickRecipe.setIngredient('2', Material.STRIPPED_OAK_LOG);
            debugStickRecipe.setIngredient('3', Material.STONE);
            debugStickRecipe.setIngredient('4', Material.DIRT);
            debugStickRecipe.setIngredient('5', Material.STICK);
            debugStickRecipe.setIngredient('6', Material.BAMBOO);
            debugStickRecipe.setIngredient('7', Material.IRON_INGOT);
            debugStickRecipe.setIngredient('8', Material.GLASS);
            debugStickRecipe.setIngredient('9', Material.LANTERN);

            // Добавляем рецепт в сервер
            getServer().addRecipe(debugStickRecipe);
        }

        if (lightCraftEnabled) {
            // Создаем рецепт для Light (Светильника)
            NamespacedKey lightKey = new NamespacedKey(this, "light");

            ShapedRecipe lightRecipe = new ShapedRecipe(lightKey, new ItemStack(Material.LIGHT, 1));
            lightRecipe.shape("123", "456", "789");
            lightRecipe.setIngredient('1', Material.JACK_O_LANTERN);
            lightRecipe.setIngredient('2', Material.AMETHYST_SHARD);
            lightRecipe.setIngredient('3', Material.SEA_LANTERN);
            lightRecipe.setIngredient('4', Material.LANTERN);
            lightRecipe.setIngredient('5', Material.GLASS_BOTTLE);
            lightRecipe.setIngredient('6', Material.CANDLE);
            lightRecipe.setIngredient('7', Material.GLOW_BERRIES);
            lightRecipe.setIngredient('8', Material.GLOW_INK_SAC);
            lightRecipe.setIngredient('9', Material.SHROOMLIGHT);

            // Добавляем рецепт в сервер
            getServer().addRecipe(lightRecipe);
        }
    }

    @Override
    public void onDisable() {
        // При отключении плагина можно удалить рецепты, чтобы они больше не работали
        NamespacedKey debugStickKey = new NamespacedKey(this, "debug_stick");
        getServer().removeRecipe(debugStickKey);

        NamespacedKey lightKey = new NamespacedKey(this, "light");
        getServer().removeRecipe(lightKey);
    }
}
