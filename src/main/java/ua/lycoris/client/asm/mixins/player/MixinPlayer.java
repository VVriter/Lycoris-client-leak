package ua.lycoris.client.asm.mixins.player;

import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.events.player.PlayerTravelEvent;

@Mixin({ EntityPlayer.class })
public class MixinPlayer extends MixinEntity {
    @Inject(method = { "travel" }, at = { @At("HEAD") }, cancellable = true)
    public void travel(final float strafe, final float vertical, final float forward, final CallbackInfo info) {
        PlayerTravelEvent event = new PlayerTravelEvent();
        Lycoris.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            move(MoverType.SELF, motionX, motionY, motionZ);
            info.cancel();
        }
    }
}
