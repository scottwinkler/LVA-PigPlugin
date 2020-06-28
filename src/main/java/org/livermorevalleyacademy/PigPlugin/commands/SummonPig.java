package org.livermorevalleyacademy.PigPlugin.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SummonPig implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel,
                             String[] args) {

        //Make pig at player location
        Player player = (Player) sender;
        Location location = player.getLocation();
        Entity entity = player.getWorld().spawnEntity(location, EntityType.PIG);
        Pig pig = (Pig) entity;
        pig.setSaddle(true);
        pig.setCustomName("Bacon");
        //pig.setVelocity(new Vector(12.0, 12.0, 12.0));
        pig.setHealth(10.0);
        pig.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 8));

        //put player on pig
        if (player.isInsideVehicle()) {
            player.leaveVehicle();
        }
        pig.addPassenger(player);

        //give player carrot on a stick if it is not in mainhand
        PlayerInventory playerInventory = player.getInventory();
        ItemStack itemInMainHand = playerInventory.getItemInMainHand();
        if (itemInMainHand.getType() != Material.CARROT_ON_A_STICK) {
            playerInventory.addItem(itemInMainHand);
            playerInventory.setItemInMainHand(new ItemStack(Material.CARROT_ON_A_STICK));
        }

        //create cool effects
        player.sendMessage(ChatColor.GREEN + "Bacon appears!");
        World world = player.getWorld();
        world.playSound(location, Sound.ENTITY_PIG_HURT, 3, 1);
        world.spawnParticle(Particle.EXPLOSION_LARGE, player.getLocation(), 1);
        return true;
    }
}
