package ua.lycoris.client.asm.mixins.accessor;

import net.minecraft.network.play.server.SPacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SPacketEntityVelocity.class)
public interface ISPacketEntityVelocity {

    @Accessor("motionX")
    void setMotionX(int newMotionX);

    @Accessor("motionY")
    void setMotionY(int newMotionY);

    @Accessor("motionZ")
    void setMotionZ(int newMotionZ);
}