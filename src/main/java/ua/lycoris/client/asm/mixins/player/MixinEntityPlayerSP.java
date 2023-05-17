package ua.lycoris.client.asm.mixins.player;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.events.player.PlayerMoveEvent;
import ua.lycoris.client.events.player.PlayerUpdateEvent;
import ua.lycoris.client.events.player.UpdateWalkingPlayerEvent;
import ua.puncakecat.beet.Event;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends EntityPlayer {

    public MixinEntityPlayerSP(World worldIn, GameProfile gameProfileIn) {
        super(worldIn, gameProfileIn);
    }

    @Inject(method = "onUpdateWalkingPlayer", at = @At("HEAD"), cancellable = true)
    public void OnPreUpdateWalkingPlayer(CallbackInfo callbackInfo) {
        UpdateWalkingPlayerEvent eventPlayerMotionUpdate = new UpdateWalkingPlayerEvent(Event.Stage.PRE);
        Lycoris.EVENT_BUS.post(eventPlayerMotionUpdate);
        if (eventPlayerMotionUpdate.isCancelled())
            callbackInfo.cancel();
    }

    @Inject(method = "onUpdate", at = @At("HEAD"))
    public void onPlayerUpdate(CallbackInfo info) {
        PlayerUpdateEvent playerUpdateEvent = new PlayerUpdateEvent();
        Lycoris.EVENT_BUS.post(playerUpdateEvent);
        if (playerUpdateEvent.isCancelled())
            info.cancel();
    }

    @Inject(method = "move", at = @At("HEAD"), cancellable = true)
    public void move(MoverType type, double x, double y, double z, CallbackInfo callbackInfo) {
        PlayerMoveEvent event = new PlayerMoveEvent(type, x, y, z);
        Lycoris.EVENT_BUS.post(event);

        if (event.isCancelled()) {
            super.move(type, event.getX(), event.getY(), event.getZ());
            callbackInfo.cancel();
        }
    }
}
