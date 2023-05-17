package ua.lycoris.client.font;

import net.minecraft.util.ResourceLocation;
import ua.lycoris.client.utils.interfaces.Globals;

import java.awt.*;
import java.io.IOException;

public class FontUtil implements Globals {

    public static Font getFont(String name, int size) {
        Font font;

        try {
            font = getFontByName(name);
            font = font.deriveFont(Font.PLAIN, size);
        } catch (Exception e) {
            throw new FontUtilException("An error occurred during from name font getting");
        }

        return font;
    }

    private static Font getFontByName(String name) throws IOException, FontFormatException {
        return getFontFromInput("/assets/minecraft/lycoris/fonts/" + name + ".ttf");
    }

    private static Font getFontFromInput(String path) throws IOException, FontFormatException {
        return Font.createFont(Font.TRUETYPE_FONT, FontUtil.class.getResourceAsStream(path));
    }
}