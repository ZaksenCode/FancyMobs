package org.zaksen.fancymobs.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.zaksen.fancymobs.FancyMobs;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ItemGenerator {

    public static ItemGenerator instance = new ItemGenerator();

    private double maxHelmetChance = 0;
    private final Map<ItemStack, Double> helmets = new HashMap<>();
    private double maxChestplateChance = 0;
    private final Map<ItemStack, Double> chestplates = new HashMap<>();
    private double maxLeggingsChance = 0;
    private final Map<ItemStack, Double> leggings = new HashMap<>();
    private double maxBootsChance = 0;
    private final Map<ItemStack, Double> boots = new HashMap<>();
    private double maxMainHandChance = 0;
    private final Map<ItemStack, Double> mainItems = new HashMap<>();
    private double maxOffHandChance = 0;
    private final Map<ItemStack, Double> offItems = new HashMap<>();

    private ItemGenerator() {

    }

    public void initialize() {
        // Helmets
        Map<String, Object> helmetItems = FancyMobs.getMainConfig().getConfigurationSection("mobs.items.helmet").getValues(false);
        for (Map.Entry<String, Object> helmetConfigValue : helmetItems.entrySet()) {
            double spawnChance = (Double) helmetConfigValue.getValue();
            helmets.put(new ItemStack(Material.valueOf(helmetConfigValue.getKey().toUpperCase())), maxHelmetChance + spawnChance);
            maxHelmetChance += spawnChance;
        }
        // Chestplates
        Map<String, Object> chestplateItems = FancyMobs.getMainConfig().getConfigurationSection("mobs.items.chestplate").getValues(false);
        for (Map.Entry<String, Object> helmetConfigValue : chestplateItems.entrySet()) {
            double spawnChance = (Double) helmetConfigValue.getValue();
            chestplates.put(new ItemStack(Material.valueOf(helmetConfigValue.getKey().toUpperCase())), maxChestplateChance + spawnChance);
            maxChestplateChance += spawnChance;
        }
        // Leggings
        Map<String, Object> legginsItems = FancyMobs.getMainConfig().getConfigurationSection("mobs.items.leggins").getValues(false);
        for (Map.Entry<String, Object> helmetConfigValue : legginsItems.entrySet()) {
            double spawnChance = (Double) helmetConfigValue.getValue();
            leggings.put(new ItemStack(Material.valueOf(helmetConfigValue.getKey().toUpperCase())), maxLeggingsChance + spawnChance);
            maxLeggingsChance += spawnChance;
        }
        // Boots
        Map<String, Object> bootsItems = FancyMobs.getMainConfig().getConfigurationSection("mobs.items.boots").getValues(false);
        for (Map.Entry<String, Object> helmetConfigValue : bootsItems.entrySet()) {
            double spawnChance = (Double) helmetConfigValue.getValue();
            boots.put(new ItemStack(Material.valueOf(helmetConfigValue.getKey().toUpperCase())), maxBootsChance + spawnChance);
            maxBootsChance += spawnChance;
        }
        // Leggings
        Map<String, Object> mainhandItems = FancyMobs.getMainConfig().getConfigurationSection("mobs.items.mainhand").getValues(false);
        for (Map.Entry<String, Object> helmetConfigValue : mainhandItems.entrySet()) {
            double spawnChance = (Double) helmetConfigValue.getValue();
            mainItems.put(new ItemStack(Material.valueOf(helmetConfigValue.getKey().toUpperCase())), maxMainHandChance + spawnChance);
            maxMainHandChance += spawnChance;
        }
        // Boots
        Map<String, Object> offHandItems = FancyMobs.getMainConfig().getConfigurationSection("mobs.items.offhand").getValues(false);
        for (Map.Entry<String, Object> helmetConfigValue : offHandItems.entrySet()) {
            double spawnChance = (Double) helmetConfigValue.getValue();
            offItems.put(new ItemStack(Material.valueOf(helmetConfigValue.getKey().toUpperCase())), maxOffHandChance + spawnChance);
            maxOffHandChance += spawnChance;
        }
    }

    public ItemStack getHelmet() {
        double random = Math.random() * maxHelmetChance;
        for (Map.Entry<ItemStack, Double> allHelmetItems : helmets.entrySet()) {
            if (random <= allHelmetItems.getValue()) {
                return allHelmetItems.getKey();
            }
        }
        return new ItemStack(Material.AIR);
    }

    public ItemStack getChestplate() {
        double random = Math.random() * maxChestplateChance;
        for (Map.Entry<ItemStack, Double> allChestplateItems : chestplates.entrySet()) {
            if (random <= allChestplateItems.getValue()) {
                return allChestplateItems.getKey();
            }
        }
        return new ItemStack(Material.AIR);
    }

    public ItemStack getLeggings() {
        double random = Math.random() * maxLeggingsChance;
        for (Map.Entry<ItemStack, Double> allLegginsItems : this.leggings.entrySet()) {
            if (random <= allLegginsItems.getValue()) {
                return allLegginsItems.getKey();
            }
        }
        return new ItemStack(Material.AIR);
    }

    public ItemStack getBoots() {
        double random = Math.random() * maxBootsChance;
        for (Map.Entry<ItemStack, Double> allBootsItems : this.boots.entrySet()) {
            if (random <= allBootsItems.getValue()) {
                return allBootsItems.getKey();
            }
        }
        return new ItemStack(Material.AIR);
    }

    public ItemStack getMainHand() {
        double random = Math.random() * maxMainHandChance;
        for (Map.Entry<ItemStack, Double> allMainHandItems : this.mainItems.entrySet()) {
            if (random <= allMainHandItems.getValue()) {
                return allMainHandItems.getKey();
            }
        }
        return new ItemStack(Material.AIR);
    }

    public ItemStack getOffHand() {
        double random = Math.random() * maxOffHandChance;
        for (Map.Entry<ItemStack, Double> allOffHandItems : this.offItems.entrySet()) {
            if (random <= allOffHandItems.getValue()) {
                return allOffHandItems.getKey();
            }
        }
        return new ItemStack(Material.AIR);
    }
}