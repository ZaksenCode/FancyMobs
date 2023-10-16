package org.zaksen.fancymobs.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.zaksen.fancymobs.mobs.BossManager;
import org.zaksen.fancymobs.mobs.BossMob;
import org.zaksen.fancymobs.util.MobUtil;

public class MobEvents implements Listener {

    // Entity events
    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        if(event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof Player)) {
            LivingEntity entity = (LivingEntity) event.getEntity();
            if(MobUtil.instance.inBlackList(entity)) { return; }
            MobUtil.instance.setupEntity(entity);
        }
    }

    @EventHandler
    public void onHit(EntityDamageEvent event) {
        if(event.getEntity() instanceof LivingEntity) {
            BossMob boss = BossManager.getBossBy((LivingEntity) event.getEntity());
            if(boss != null) {
                boss.onHit();
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        event.getEntity();
        BossMob boss = BossManager.getBossBy(event.getEntity());
        if(boss != null) {
            boss.onDeath();
        }
    }
}