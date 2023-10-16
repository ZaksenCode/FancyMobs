package org.zaksen.fancymobs.mobs;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.zaksen.fancymobs.FancyMobs;

import java.util.concurrent.ThreadLocalRandom;

public class BossMob {
    private LivingEntity bossEntity;
    private EntityType type;

    public BossMob(LivingEntity entity) {
        this.bossEntity = entity;
        this.type = entity.getType();
        setupBoss();
        BossManager.addBoss(this);
    }

    private void setupBoss() {
        // Name
        bossEntity.setCustomName(getBossName());
        bossEntity.setCustomNameVisible(true);
        // Health
        double health = getBossHealth();
        bossEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
        bossEntity.setHealth(health);
        // Effects
        FancyMobs.getMainConfig().getConfigurationSection("boss_effects").getKeys(false).forEach((potionName) -> {
            PotionEffectType potionType = PotionEffectType.getByName(potionName.toUpperCase());
            if(potionType != null) {
                int min = FancyMobs.getMainConfig().getInt("boss_effects." + potionName + ".min");
                int max = FancyMobs.getMainConfig().getInt("boss_effects." + potionName + ".max");
                bossEntity.addPotionEffect(new PotionEffect(potionType, 999999, ThreadLocalRandom.current().nextInt(min, max), false, false));
            }
        });
    }

    private String getBossName() {
        return "Boss";
    }

    private double getBossHealth() {
        for(String setBoss : FancyMobs.getMainConfig().getConfigurationSection("mini_bosses").getKeys(false)) {
            if(this.type == EntityType.valueOf(setBoss.toUpperCase())) {
                int min = FancyMobs.getMainConfig().getInt("mini_bosses." + setBoss + ".health.min");
                int max = FancyMobs.getMainConfig().getInt("mini_bosses." + setBoss + ".health.max");
                return ThreadLocalRandom.current().nextDouble(min, max);
            }
        }
        return 50.0;
    }

    public LivingEntity getBossEntity() {
        return bossEntity;
    }

    public void onHit() {
        bossEntity.getLocation().getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, bossEntity.getLocation().clone().add(0, 1, 0), 25, 0.1, 1, 0.1, 0.01);
    }

    public void onDeath() {
        bossEntity.getLocation().getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, bossEntity.getLocation().clone().add(0, 1, 0), 100, 0.5, 0.5, 0.5, 0.1);
        bossEntity.getLocation().getWorld().spawnParticle(Particle.REVERSE_PORTAL, bossEntity.getLocation().clone().add(0, 1, 0), 50, 0.2, 0.2, 0.2, 0.1);
        // End
        BossManager.stopBoss(this);
    }
}