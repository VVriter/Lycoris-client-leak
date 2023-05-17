package ua.lycoris.client.ui.clickgui.components

import ua.lycoris.client.Lycoris
import ua.lycoris.client.font.FontRenderer
import ua.lycoris.client.settings.impl.primitive.EnumSetting
import ua.lycoris.client.ui.clickgui.Component
import ua.lycoris.client.ui.clickgui.Gui
import ua.lycoris.client.ui.clickgui.GuiUtil.drawComponentBase
import ua.lycoris.client.ui.clickgui.listen.mouse.impl.MouseComponentExecutor
import ua.lycoris.client.utils.client.MathUtil
import ua.lycoris.client.utils.render2d.Rectangle
import ua.lycoris.client.utils.render2d.RenderUtils2D.drawRectAlpha
import ua.lycoris.client.utils.render2d.animation.Animation
import ua.lycoris.client.utils.render2d.animation.Easing
import java.awt.Color

/**
 * @author PunCakeCat/Kristofer
 */
class ModeComponent(val setting: EnumSetting, width: Int, height: Int) : Component(width, height) {

    private val hoverAlphaAnimation = Animation(
        animationSpeed.toDouble(),
        { easing },
        { hovered })

    init {
        MouseComponentExecutor(this)
            .setOnClick {action ->
                if(isDragging) return@setOnClick
                val dir = if(action.state == 0) -1 else action.state
                val values = setting.value.javaClass.enumConstants
                val size = values.size
                val id = setting.value.ordinal
                var value = id + dir
                value = if(value >= size) value - size else if(value < 0) value + size else value
                setting.value = values[value]
            }
    }

    override fun draw(mouseX: Int, mouseY: Int) {
        super.draw(mouseX, mouseY)
        drawComponentBase(this)
        drawRectAlpha(Rectangle(x, y, width, height), hover, hoverAlphaAnimation.getAnimationFactor().toFloat())
        fontManager.setCurrent(Lycoris.regularFontRenderer)
            .drawString(
                FontRenderer.Mode.Default,
                setting.name,
                (x + 2).toDouble(),
                (y + 5).toDouble(),
                Color(126,119,119,255)
            )
            .drawString(
                FontRenderer.Mode.Default,
                setting.value.toString(),
                (x + width - 10 - fontManager.getWidth(setting.value.toString())).toDouble(),
                (y + 5).toDouble(),
                white
            )
        hovered = false
    }

}