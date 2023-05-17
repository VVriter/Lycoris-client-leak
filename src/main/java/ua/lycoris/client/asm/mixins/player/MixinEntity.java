package ua.lycoris.client.asm.mixins.player;

import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.events.player.EntityEvent;

@Mixin({ Entity.class })
public class MixinEntity
{
    @Shadow
    public double motionX;
    @Shadow
    public double motionY;
    @Shadow
    public double motionZ;

    @Redirect(method = { "applyEntityCollision" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void velocity(final Entity entity, final double x, final double y, final double z) {
        EntityEvent event = new EntityEvent(entity);
        Lycoris.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            return;
        }
        entity.motionX += x;
        entity.motionY += y;
        entity.motionZ += z;
        entity.isAirBorne = true;
    }

    @Shadow
    public void move(final MoverType type, final double x, final double y, final double z) {
    }
}