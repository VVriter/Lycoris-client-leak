package ua.lycoris.client.asm.mixins.render;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.events.render.RenderArmEvent;
import ua.puncakecat.beet.Event;

@Mixin(RenderPlayer.class)
public class MixinRenderPlayer {

    @Inject(method = "renderLeftArm", at = @At(value =  "FIELD", target = "Lnet/minecraft/client/model/ModelPlayer;swingProgress:F", opcode=181))
    public void renderLeftArmPre(AbstractClientPlayer clientPlayer, CallbackInfo ci) {
        RenderArmEvent renderArmEvent = new RenderArmEvent(Event.Stage.PRE, clientPlayer, RenderArmEvent.arm.Left);
        Lycoris.EVENT_BUS.post(renderArmEvent);
    }

    @Inject(method = "renderLeftArm", at = @At(value = "RETURN"))
    public void renderLeftArmPost(AbstractClientPlayer clientPlayer, CallbackInfo ci) {
        RenderArmEvent renderArmEvent = new RenderArmEvent(Event.Stage.POST, clientPlayer, RenderArmEvent.arm.Left);
        Lycoris.EVENT_BUS.post(renderArmEvent);
    }

    @Inject(method = "renderRightArm", at = @At(value =  "FIELD", target = "Lnet/minecraft/client/model/ModelPlayer;swingProgress:F", opcode=181))
    public void renderRightArmPre(AbstractClientPlayer clientPlayer, CallbackInfo ci) {
        RenderArmEvent renderArmEvent = new RenderArmEvent(Event.Stage.PRE, clientPlayer, RenderArmEvent.arm.Right);
        Lycoris.EVENT_BUS.post(renderArmEvent);
    }

    @Inject(method = "renderRightArm", at = @At(value = "RETURN"))
    public void renderRightArmPost(AbstractClientPlayer clientPlayer, CallbackInfo ci) {
        RenderArmEvent renderArmEvent = new RenderArmEvent(Event.Stage.POST, clientPlayer, RenderArmEvent.arm.Right);
        Lycoris.EVENT_BUS.post(renderArmEvent);
    }
}
