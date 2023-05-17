package ua.lycoris.client.asm.mixins.accessor;

import net.minecraft.network.play.server.SPacketExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SPacketExplosion.class)
public interface ISPacketExplosion {

    @Accessor("motionX")
    void setMotionX(float newMotionX);

    @Accessor("motionY")
    void setMotionY(float newMotionY);

    @Accessor("motionZ")
    void setMotionZ(float newMotionZ);
}