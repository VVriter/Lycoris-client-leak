package ua.lycoris.client.ui.clickgui.components.colorpicker;

import net.minecraft.client.Minecraft;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.font.FontRenderer;
import ua.lycoris.client.settings.impl.primitive.ColorSetting;
import ua.lycoris.client.ui.clickgui.Component;
import ua.lycoris.client.ui.clickgui.GuiUtil;
import ua.lycoris.client.ui.clickgui.listen.mouse.impl.MouseComponentExecutor;
import ua.lycoris.client.utils.client.MathUtil;
import ua.lycoris.client.utils.render2d.Rectangle;
import ua.lycoris.client.utils.render2d.RenderUtils2D;

import java.awt.*;
import java.util.ArrayList;

public class ColorComponent extends Component {
    ArrayList<Component> components = new ArrayList<>();
    ColorSetting colorSetting;
    public boolean isOpened;
    AlphaSlider alphaSlider;
    public ColorComponent(ColorSetting colorSetting, int width, int height) {
        super(width,height);
        this.colorSetting = colorSetting;
        components.add(alphaSlider = new AlphaSlider(colorSetting,110,15));

       new MouseComponentExecutor(this)
                .setOnClick(onClick -> {
                    x = onClick.getX();
                    y = onClick.getY();
                    setDragging();
                    if (onClick.getState() == 1)
                        isOpened = !isOpened;
                });
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        super.draw(mouseX, mouseY);
        GuiUtil.INSTANCE.drawComponentBase(this);
        fontManager.setCurrent(Lycoris.regularFontRenderer)
                .drawString(
                        FontRenderer.Mode.Default,
                        colorSetting.getName(),
                        (x + 2),
                        (y + 5),
                        Color.white
                );
        RenderUtils2D.INSTANCE.drawRectangle(
                new Rectangle(
                        x+width-12,
                        y+2.5,
                        10,
                        10),
                colorSetting.getValue()
        );

        alphaSlider.y = 15;
        components.forEach(component -> component.draw(mouseX, mouseY));

   /*     if (isOpened) {
            this.height = 30;
            alphaSlider.y = 15;
            components.forEach(component -> component.draw(mouseX, mouseY));
        } else {
            this.height = 15;
        } */
    }

    @Override
    public int getOffset() {
        return height+getAllCompHeight()* MathUtil.INSTANCE.booleanToNumber(isOpened).intValue();
    }

    int getAllCompHeight() {
        return components.stream().mapToInt(Component::getOffset).sum();
    }
}
