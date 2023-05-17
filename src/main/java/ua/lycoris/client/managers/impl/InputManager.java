package ua.lycoris.client.managers.impl;

import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import ua.lycoris.client.Info;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.events.mc.InputEvent;
import ua.lycoris.client.managers.Manager;
import ua.lycoris.client.systems.modules.Module;
import ua.puncakecat.beet.Listener;
import ua.puncakecat.beet.Subscribe;

public class InputManager extends Manager {

    @Subscribe
    private Listener<InputEvent.KeyInputEvent> inputEvent = new Listener<>(event -> {
        for(Module module : Lycoris.moduleManager.modules){
            if(Keyboard.isKeyDown(module.getBind()))
                module.toggle();
        }
        if(Keyboard.getEventCharacter() == Info.COMMAND_PREFIX.toCharArray()[0])
            mc.displayGuiScreen(new GuiChat(Info.COMMAND_PREFIX));
    });
}
