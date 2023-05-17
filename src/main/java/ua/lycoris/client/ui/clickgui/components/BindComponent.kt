package ua.lycoris.client.ui.clickgui.components

import ua.lycoris.client.Lycoris
import ua.lycoris.client.font.FontRenderer
import ua.lycoris.client.settings.impl.primitive.BindSetting
import ua.lycoris.client.ui.clickgui.Component
import ua.lycoris.client.ui.clickgui.listen.mouse.impl.MouseComponentExecutor
import ua.lycoris.client.utils.render2d.Rectangle
import ua.lycoris.client.utils.render2d.RenderUtils2D
import ua.lycoris.client.utils.render2d.animation.Animation
import java.awt.Color

class BindComponent(val setting: BindSetting, width: Int, height: Int): Component(width, height) {

    private val hoverAlphaAnimation = Animation(
            animationSpeed.toDouble(),
            { easing },
            { hovered })
    private val animation = Animation(
            animationSpeed.toDouble(),
            { easing },
            { setting.value })

    var isListening = false;

    init {
        MouseComponentExecutor(this)
                .setOnClick {
                    isListening = true;
                }
    }

    override fun onKeyTyped(keyCode: Int) {
        super.onKeyTyped(keyCode)
        println("Typed!")
        if (isListening) {
            setting.value = keyCode
            isListening = false
        }
    }

    override fun draw(mouseX: Int, mouseY: Int) {
        super.draw(mouseX, mouseY)
        RenderUtils2D.drawRectangle(Rectangle(x, y, width, height),
                Color(1, 1, 1, 136 - (56 * hoverAlphaAnimation.getAnimationFactor()).toInt()))
        RenderUtils2D.drawRectangle(Rectangle(x, y, width * animation.getAnimationFactor(), height), Juliet)
        RenderUtils2D.drawRectAlpha(Rectangle(x, y, width * animation.getAnimationFactor(), height), Juliet, hoverAlphaAnimation.getAnimationFactor().toFloat())

        if (!isListening) {
            fontManager.setCurrent(Lycoris.regularFontRenderer)
                    .drawString(FontRenderer.Mode.Default, "Bind     " + setting.bindName, x.toDouble() + 3, y + 5.0, Color.white)
        } else {
            fontManager.setCurrent(Lycoris.regularFontRenderer)
                    .drawString(FontRenderer.Mode.Default, "LISTENING", x.toDouble() + 3, y + 5.0, Color.white)
        }
        hovered = false
    }

}