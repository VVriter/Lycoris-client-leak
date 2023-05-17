package ua.lycoris.client.utils.interfaces;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import ua.lycoris.client.Logger;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.font.FontRenderer;
import ua.lycoris.client.managers.impl.FontManager;
import ua.lycoris.client.managers.impl.ModuleManager;
import ua.lycoris.client.managers.impl.SettingManager;
import ua.lycoris.client.systems.modules.impl.client.GuiModule;
import ua.lycoris.client.ui.clickgui.GuiUtil;
import ua.lycoris.client.utils.render2d.animation.Easing;

import java.awt.*;

public interface Globals {
    Minecraft mc = Minecraft.getMinecraft();
    //Managers
    ModuleManager moduleManager = Lycoris.moduleManager;
    SettingManager settingManager = Lycoris.settingManager;
    FontManager fontManager = Lycoris.fontManager;

    //Fonts
    FontRenderer regularFR = Lycoris.regularFontRenderer;

    //Render stuff
    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferbuilder = tessellator.getBuffer();

    //Help stuff
    Color Romeo = GuiUtil.INSTANCE.getRomeo();
    Color Juliet = GuiUtil.INSTANCE.getJuliet();

    Color white = GuiUtil.INSTANCE.getWhite();
    Color lightGray = GuiUtil.INSTANCE.getLightGray();
    Color hover = GuiUtil.INSTANCE.getHover();

    String LYCORIS_DIRECTORY = mc.gameDir+"/Lycoris/";

    int animationSpeed = GuiModule.Companion.getAnimationSpeed();
    Easing easing = (Easing) GuiModule.Companion.getEasing();
}
