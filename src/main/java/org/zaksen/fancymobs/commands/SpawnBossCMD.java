package org.zaksen.fancymobs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.zaksen.fancymobs.mobs.BossMob;

public class SpawnBossCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            LivingEntity newBoss = ((Player) sender).getLocation().getWorld().spawn(((Player) sender).getLocation(), Zombie.class);
            new BossMob(newBoss);
        }
        return true;
    }

}