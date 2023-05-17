package ua.lycoris.client;

import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import ua.lycoris.client.font.FontRenderer;
import ua.lycoris.client.managers.Manager;
import ua.lycoris.client.managers.impl.*;
import ua.lycoris.client.utils.interfaces.Globals;
import ua.puncakecat.beet.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Mod(modid = Info.MODID, name = Info.NAME, version = Info.VERSION)
public class Lycoris implements Globals {

    public static EventBus EVENT_BUS;
    protected List<Manager> managers = new ArrayList<>();
    public static SettingManager settingManager;
    public static ModuleManager moduleManager;
    public static CommandManager commandManager;

    public static FontRenderer regularFontRenderer =
            new FontRenderer("Ubuntu-Regular", 19, true, false);
    public static FontManager fontManager;
    public static FriendManager friendManager;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new TitleManager());
        EVENT_BUS = new EventBus();
        addManager(
                settingManager = new SettingManager(),
                moduleManager = new ModuleManager(),
                commandManager = new CommandManager(),
                fontManager = new FontManager(),
                friendManager = new FriendManager(),
                new EventManager(),
                new InputManager()
        );
        managers.forEach(Manager::load);
    }

    protected void addManager(Manager... managers) {
        Collections.addAll(this.managers, managers);
    }
}