package org.zaksen.fancymobs.mobs;

import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class BossManager {
    private static final List<BossMob> bosses = new ArrayList<>();

    public static BossMob getBossBy(LivingEntity entity) {
        for(BossMob bossMob : bosses) {
            if(bossMob.getBossEntity() == entity) {
                return bossMob;
            }
        }
        return null;
    }

    private BossManager() {

    }

    public static void addBoss(BossMob bossMob) {
        bosses.add(bossMob);
    }

    public static void stopBoss(BossMob bossMob) {
        bosses.remove(bossMob);
    }
}