package ua.lycoris.client.ui.clickgui.components

import ua.lycoris.client.Lycoris
import ua.lycoris.client.font.FontRenderer
import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.ui.clickgui.Component
import ua.lycoris.client.ui.clickgui.GuiUtil
import ua.lycoris.client.ui.clickgui.listen.mouse.impl.MouseComponentExecutor
import ua.lycoris.client.utils.render2d.Rectangle
import ua.lycoris.client.utils.render2d.RenderUtils2D
import ua.lycoris.client.utils.render2d.animation.Easing
import ua.lycoris.client.utils.render2d.animation.Animation
import java.awt.Color

/**
 * @author PunCakeCat/Kristofer
 */
class BoolComponent(val setting: BooleanSetting, width: Int, height: Int) : Component(width, height) {

    private val hoverAlphaAnimation = Animation(
        animationSpeed.toDouble(),
        { easing },
        { hovered })
    private val animation = Animation(
        animationSpeed.toDouble(),
        { easing },
        { setting.value })

    init {
        MouseComponentExecutor(this)
            .setOnClick {
                setting.value = !setting.value
            }
    }

    override fun draw(mouseX: Int, mouseY: Int) {
        super.draw(mouseX, mouseY)
        RenderUtils2D.drawRectangle(Rectangle(x, y, width, height),
            Color(1, 1, 1, 136 - (56 * hoverAlphaAnimation.getAnimationFactor()).toInt()))
        RenderUtils2D.drawRectangle(Rectangle(x, y, width * animation.getAnimationFactor(), height), Juliet)
        RenderUtils2D.drawRectAlpha(Rectangle(x, y, width * animation.getAnimationFactor(), height), Juliet, hoverAlphaAnimation.getAnimationFactor().toFloat())
        val textColor = if (setting.value) white else lightGray
        fontManager.setCurrent(Lycoris.regularFontRenderer)
            .drawString(FontRenderer.Mode.Default, setting.name,x + (width - fontManager.getWidth(setting.name)) / 2.0, y + 5.0, textColor)
        hovered = false
    }
}