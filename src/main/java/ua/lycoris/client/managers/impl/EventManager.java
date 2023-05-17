package ua.lycoris.client.managers.impl;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.events.mc.ChatSendEvent;
import ua.lycoris.client.events.mc.InputEvent;
import ua.lycoris.client.events.player.ClientTickEvent;
import ua.lycoris.client.events.render.Render2dEvent;
import ua.lycoris.client.events.render.Render3dEvent;
import ua.lycoris.client.events.render.RenderGameOverlayEvent;
import ua.lycoris.client.managers.Manager;

public class EventManager extends Manager {
    @Override
    public void load() {
        super.load();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void destroy() {
        super.destroy();
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event){
        Lycoris.EVENT_BUS.post(new ClientTickEvent());
    }

    @SubscribeEvent
    public void renderGameOverlay(net.minecraftforge.client.event.RenderGameOverlayEvent e) {
        RenderGameOverlayEvent event = new RenderGameOverlayEvent(e.getType(), e.getPartialTicks(), e.getResolution());
        if (event.isCancelled())
            e.setCanceled(true);
        Lycoris.EVENT_BUS.post(event);
    }

    @SubscribeEvent
    public void renderLast(RenderWorldLastEvent e) {
        Lycoris.EVENT_BUS.post(new Render3dEvent(e.getPartialTicks()));
    }

    @SubscribeEvent
    public void render2dEvent(TickEvent.RenderTickEvent e) {
        Lycoris.EVENT_BUS.post(new Render2dEvent(e.renderTickTime));
    }

    @SubscribeEvent
    public void onKeyInout(net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent event){
        Lycoris.EVENT_BUS.post(new InputEvent.KeyInputEvent());
    }

    @SubscribeEvent
    public void onKeyInout(net.minecraftforge.fml.common.gameevent.InputEvent.MouseInputEvent event){
        Lycoris.EVENT_BUS.post(new InputEvent.MouseInputEvent());
    }

    @SubscribeEvent
    public void onChatSend(ClientChatEvent e) {
        ChatSendEvent event = new ChatSendEvent(e.getMessage());
        Lycoris.EVENT_BUS.post(event);
        if (event.isCancelled())
            e.setCanceled(true);
    }

    @SubscribeEvent
    public void onMovementInput(InputUpdateEvent event) {
        Lycoris.EVENT_BUS.post(new ua.lycoris.client.events.mc.InputUpdateEvent(event.getMovementInput()));
    }

}
