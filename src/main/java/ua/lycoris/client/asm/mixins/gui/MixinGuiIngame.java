package ua.lycoris.client.asm.mixins.gui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.events.norender.RenderExpBarEvent;
import ua.lycoris.client.events.norender.RenderPotionEffectsEvent;
import ua.lycoris.client.events.norender.RenderPumpkinOverlayEvent;
import ua.lycoris.client.events.norender.RenderScoreBoardEvent;

@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame extends Gui {

    @Inject(method={"renderPotionEffects"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderPotionEffects(CallbackInfo ci) {
        RenderPotionEffectsEvent event = new RenderPotionEffectsEvent();
        Lycoris.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(method={"renderPumpkinOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderPumpkinOverlay(CallbackInfo ci) {
        RenderPumpkinOverlayEvent event = new RenderPumpkinOverlayEvent();
        Lycoris.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(method={"renderExpBar"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderExpBar(CallbackInfo ci) {
        RenderExpBarEvent event = new RenderExpBarEvent();
        Lycoris.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(method={"renderScoreboard"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderScoreboard(CallbackInfo ci) {
        RenderScoreBoardEvent event = new RenderScoreBoardEvent();
        Lycoris.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}