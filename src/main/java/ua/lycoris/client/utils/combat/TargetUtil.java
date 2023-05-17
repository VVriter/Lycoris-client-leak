package ua.lycoris.client.utils.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import ua.lycoris.client.systems.modules.impl.client.TargetsModule;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class TargetUtil {
    public static Entity getClosest(double maxRange) {
        double lowestDistance = Integer.MAX_VALUE;
        Entity closest = null;
        Minecraft mc = Minecraft.getMinecraft();
        for (Entity entity : mc.world.loadedEntityList) {
            if (entity == mc.player) continue;
            if ((entity instanceof EntityMob && TargetsModule.Companion.getMobs())
                    || (entity instanceof EntityAnimal && TargetsModule.Companion.getAnimals())
                    || (entity instanceof EntityPlayer && TargetsModule.Companion.getPlayer())
                    || (entity instanceof EntityBoat && TargetsModule.Companion.getBoat())
                    || (entity instanceof EntityArmorStand && TargetsModule.Companion.getArmourStand())) {
                if (entity.getDistance(mc.player) < lowestDistance) {
                    if (entity.getPositionVector().distanceTo(mc.player.getPositionVector()) <= maxRange) {
                        lowestDistance = entity.getDistance(mc.player);
                        closest = entity;
                    }
                }
            }
        }
        return closest;
    }

    public static EntityPlayer getClosestPlayer(double maxRange) {
        Minecraft mc = Minecraft.getMinecraft();
        double lowestDistance = Integer.MAX_VALUE;
        EntityPlayer closest = null;

        for (EntityPlayer player : getAll()) {
            if (player.getDistance(mc.player) < lowestDistance) {
              //  if(!FriendManager.friends.contains(player.getDisplayNameString())) {
                    if(player.getPositionVector().distanceTo(mc.player.getPositionVector()) <= maxRange) {
                        lowestDistance = player.getDistance(mc.player);
                        closest = player;
                    }
               // }
            }
        }
        return closest;
    }

    public static EntityPlayer getClosestPlayer() {
        Minecraft mc = Minecraft.getMinecraft();
        double lowestDistance = Integer.MAX_VALUE;
        EntityPlayer closest = null;

        for (EntityPlayer player : getAll()) {
            if (player.getDistance(mc.player) < lowestDistance) {
                lowestDistance = player.getDistance(mc.player);
                closest = player;
            }
        }
        return closest;
    }

    public static LinkedList<EntityPlayer> getClosestAllTargets(double maxRange) {
        Minecraft mc = Minecraft.getMinecraft();
        LinkedList<EntityPlayer> targets = new LinkedList<>();

        for (EntityPlayer player : getAll()) {
            if (player.getDistance(mc.player) <= maxRange) {
             //   if(!FriendManager.friends.contains(player.getDisplayNameString())) {
                    targets.add(player);
             //   }
            }
        }
        targets.sort(Comparator.comparing(target -> target.getPositionVector().distanceTo(mc.player.getPositionVector())));

        return targets;
    }

    public static EntityPlayer getClosest(Vec3d vec3d) {
        double lowestDistance = Integer.MAX_VALUE;
        EntityPlayer closest = null;

        for (EntityPlayer player : getAll()) {
            if (player.getPositionVector().distanceTo(vec3d) < lowestDistance) {
              //  if(!FriendManager.friends.contains(player.getDisplayNameString())) {
                    lowestDistance = player.getPositionVector().distanceTo(vec3d);
                    closest = player;
              //  }
            }
        }
        return closest;
    }

    public static ArrayList<EntityPlayer> getAll() {
        Minecraft mc = Minecraft.getMinecraft();
        try {
            ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>();

            for (EntityPlayer player : mc.world.playerEntities)
                if (!player.isEntityEqual(mc.player))
                    players.add(player);

            return players;
        } catch (NullPointerException ignored) {
            return new ArrayList<>();
        }
    }
}