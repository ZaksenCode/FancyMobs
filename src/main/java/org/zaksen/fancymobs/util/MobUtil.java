package org.zaksen.fancymobs.util;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.zaksen.fancymobs.FancyMobs;
import org.zaksen.fancymobs.mobs.BossMob;

import java.util.concurrent.ThreadLocalRandom;

public class MobUtil {

    public static final MobUtil instance = new MobUtil();

    // Entity settings
    public void setupEntity(LivingEntity entity) {
        // Health
        setupHealth(entity);
        // Entity inventory
        try {
            setupItems(entity);
        } catch (Exception ignored) {

        }
        if(entity.getType() != EntityType.CREEPER) {
            // Potions
            boolean isBoss = FancyMobs.getMainConfig().getDouble("mini_boss_chance") >= ThreadLocalRandom.current().nextDouble(0, 1);
            if (isBoss) {
                new BossMob(entity);
                return;
            }
            // Make not boss effects
            setupEffect(entity);
            return;
        }
        entity.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 120, 5, false, false));
    }

    public void setupHealth(LivingEntity entity) {
        int min = FancyMobs.getMainConfig().getInt("mobs.health.min");
        int max = FancyMobs.getMainConfig().getInt("mobs.health.max");
        double health = ThreadLocalRandom.current().nextDouble(min, max);
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        entity.setHealth(health);
    }

    public void setupItems(LivingEntity entity) {
        EntityEquipment entityItems = entity.getEquipment();
        if(entityItems != null) {
            // Steal items
            entity.setCanPickupItems(true);

            // ~~~ armor ~~~

            // Helmet
            entityItems.setHelmet(ItemGenerator.instance.getHelmet());
            // Chestplate
            entityItems.setChestplate(ItemGenerator.instance.getChestplate());
            // Leggins
            entityItems.setLeggings(ItemGenerator.instance.getLeggings());
            // Boots
            entityItems.setBoots(ItemGenerator.instance.getBoots());

            // ~~~ weapon ~~~
            entityItems.setItemInMainHand(ItemGenerator.instance.getMainHand());
            entityItems.setItemInOffHand(ItemGenerator.instance.getOffHand());
        }
    }

    public void setupEffect(LivingEntity entity) {
        FancyMobs.getMainConfig().getConfigurationSection("mob_effects").getKeys(false).forEach((potionName) -> {
            PotionEffectType potionType = PotionEffectType.getByName(potionName.toUpperCase());
            if(potionType != null) {
                int min = FancyMobs.getMainConfig().getInt("mob_effects." + potionName + ".min");
                int max = FancyMobs.getMainConfig().getInt("mob_effects." + potionName + ".max");
                entity.addPotionEffect(new PotionEffect(potionType, 999999, ThreadLocalRandom.current().nextInt(min, max), false, false));
            }
        });
    }

    public boolean inBlackList(LivingEntity entity) {
        return FancyMobs.getMainConfig().getStringList("blacklist").contains(entity.getType().toString());
    }
}