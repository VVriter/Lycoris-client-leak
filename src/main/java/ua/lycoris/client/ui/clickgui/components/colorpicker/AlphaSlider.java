package ua.lycoris.client.ui.clickgui.components.colorpicker;

import ua.lycoris.client.Lycoris;
import ua.lycoris.client.font.FontRenderer;
import ua.lycoris.client.settings.impl.primitive.ColorSetting;
import ua.lycoris.client.ui.clickgui.Component;
import ua.lycoris.client.ui.clickgui.GuiUtil;
import ua.lycoris.client.ui.clickgui.listen.mouse.impl.MouseComponentExecutor;

import java.awt.*;

public class AlphaSlider extends Component {

    ColorSetting colorSetting;
    public AlphaSlider(ColorSetting colorSetting, int width, int height) {
        super(width,height);
        this.colorSetting = colorSetting;
        new MouseComponentExecutor(this)
                .setOnClick(onClick -> {
                    x = onClick.getX();
                    y = onClick.getY();
                    setDragging();
                });
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        super.draw(mouseX, mouseY);
        GuiUtil.INSTANCE.drawComponentBase(this);
        fontManager.setCurrent(Lycoris.regularFontRenderer)
                .drawString(
                        FontRenderer.Mode.Default,
                        String.valueOf(colorSetting.getAlpha()),
                        (x + 2),
                        (y + 5),
                        Color.white
                );
    }
}
