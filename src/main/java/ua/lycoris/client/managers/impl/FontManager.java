package ua.lycoris.client.managers.impl;

import ua.lycoris.client.Lycoris;
import ua.lycoris.client.font.FontRenderer;
import ua.lycoris.client.managers.Manager;

import java.awt.*;

public class FontManager extends Manager {

    private FontRenderer currentRenderer;

    public FontManager setCurrent(FontRenderer renderer){
        this.currentRenderer = renderer;
        return this;
    }

    public FontManager drawString(FontRenderer.Mode mode, String str, double x, double y, Color color){
        this.currentRenderer.drawString(mode, str, x, y, color.getRGB());
        return this;
    }

    public float getWidth(String str){
        return this.currentRenderer.getStringWidth(str);
    }
}
