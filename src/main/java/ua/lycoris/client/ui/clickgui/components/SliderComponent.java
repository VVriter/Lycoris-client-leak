package ua.lycoris.client.ui.clickgui.components;

import ua.lycoris.client.Lycoris;
import ua.lycoris.client.font.FontRenderer;
import ua.lycoris.client.settings.impl.numerical.NumericalSetting;
import ua.lycoris.client.ui.clickgui.Component;
import ua.lycoris.client.ui.clickgui.GuiUtil;
import ua.lycoris.client.ui.clickgui.listen.mouse.impl.MouseComponentExecutor;
import ua.lycoris.client.utils.render2d.Rectangle;
import ua.lycoris.client.utils.render2d.RenderUtils2D;

import java.awt.*;

/**
 * @author PunCakeCat/Kristofer
 */
public class SliderComponent extends Component {

    NumericalSetting setting;
    float difference;

    public SliderComponent(NumericalSetting setting, int width, int height) {
        super(width, height);
        this.setting = setting;
        difference = setting.getMax().longValue() - setting.getMin().longValue();

        new MouseComponentExecutor(this)
                .setOnClick(onClick -> {
                    setSettingFromX(onClick.getX());
                    setDragging();
                });
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        super.draw(mouseX, mouseY);
        GuiUtil.INSTANCE.drawComponentBase(this);
        RenderUtils2D.INSTANCE.drawRectangle(new Rectangle(x, y, width*(setting.getValue().longValue()-setting.getMin().longValue())/difference, height), Juliet);
        fontManager.setCurrent(Lycoris.regularFontRenderer)
                .drawString(FontRenderer.Mode.Default, GuiUtil.INSTANCE.stringValue(setting.getName()), x + 2, y + 5, Color.white)
                .drawString(FontRenderer.Mode.Default, String.valueOf(setting.getValue()), x + width - 2 - fontManager.getWidth(String.valueOf(setting.getValue())), y + 5, white);
    }

    private void setSettingFromX(int mouseX) {
        float percent = ((float) mouseX - this.x) / ((float) this.width);
        if (this.setting.getValue() instanceof Double) {
            double result = (Double) this.setting.getMin() + (double) (this.difference * percent);
            this.setting.setValue((double) Math.round(10.0 * result) / 10.0);
        } else if (this.setting.getValue() instanceof Float) {
            float result = this.setting.getMin().floatValue() + this.difference * percent;
            this.setting.setValue((float) Math.round(10.0f * result) / 10.0f);
        } else if (this.setting.getValue() instanceof Integer) {
            this.setting.setValue((Integer) this.setting.getMin() + (int) (this.difference * percent));
        }
    }
}
