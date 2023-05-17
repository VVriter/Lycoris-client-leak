package ua.lycoris.client.ui.clickgui.components

import ua.lycoris.client.font.FontRenderer
import ua.lycoris.client.settings.Setting
import ua.lycoris.client.settings.impl.numerical.NumericalSetting
import ua.lycoris.client.settings.impl.primitive.BindSetting
import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.settings.impl.primitive.ColorSetting
import ua.lycoris.client.settings.impl.primitive.EnumSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.ui.clickgui.Component
import ua.lycoris.client.ui.clickgui.components.colorpicker.ColorComponent
import ua.lycoris.client.ui.clickgui.listen.mouse.impl.MouseComponentExecutor
import ua.lycoris.client.utils.render2d.Rectangle
import ua.lycoris.client.utils.render2d.RenderUtils2D
import ua.lycoris.client.utils.render2d.animation.Animation
import java.awt.Color
import java.util.function.Consumer

/**
 * @author PunCakeCat/Kristofer
 */
class ModuleComponent(var module: Module, width: Int, height: Int) : Component(width, height) {
    private val components: MutableList<Component> = ArrayList()
    private val alphaAnimation = Animation(
        animationSpeed.toDouble(),
        { easing },
        { module.isEnabled() || hovered })
    private val expandAnimation = Animation(
        animationSpeed.toDouble(),
        { easing },
        { module.isOpened() })


    init {
        settingManager.getSettings(module).forEach(Consumer { setting: Setting<*>? ->
            var component: Component? = null
            when(setting){
                is BindSetting -> component = BindComponent(setting, 110, 15)
                is NumericalSetting<*> -> component = SliderComponent(setting, 110, 15)
                is BooleanSetting -> component = BoolComponent(setting, 110, 15)
                is EnumSetting -> component = ModeComponent(setting, 110, 15)
                is ColorSetting -> component = ColorComponent(setting, 110, 15)
            }
            if(component != null) {
                component.setVisibility { setting!!.isVisible && expandAnimation.getAnimationFactor() != 0.0 }
                components.add(component)
            }
        })
        MouseComponentExecutor(this)
            .setOnClick{onClick ->
                val state: Int = onClick.state
                if(isDragging) return@setOnClick
                when(state){
                    1 -> module.toggleOpened()
                    0 -> module.toggle()
                }
            }
    }

    override fun draw(mouseX: Int, mouseY: Int) {
        super.draw(mouseX, mouseY)
        RenderUtils2D.drawRectangle(Rectangle(x, y, width, height),
            Color(1, 1, 1, 136 - (56 * alphaAnimation.getAnimationFactor()).toInt()))
        val textColor = if (module.isEnabled()) white else lightGray
        fontManager.setCurrent(regularFR)
            .drawString(FontRenderer.Mode.Default, module.name, x + 3.0, y + 6.0, textColor)
            .drawString(FontRenderer.Mode.Default, if(module.isOpened()) "=" else "+", x + width - 8.0, y + 6.0, textColor)

        RenderUtils2D.pushScissor(Rectangle(x, y + height, width, componentsOffset()))
        components.stream().filter { component: Component -> component.isVisible }
            .forEach { component -> component.draw(mouseX, mouseY) }
        RenderUtils2D.popScissor()

        hovered = false;
    }

    override fun update(minX: Int, minY: Int) {
        super.update(minX, minY)
        var offset = height
        for (component in components) {
            if (!component.isVisible) continue
            component.update(minX, minY + offset)
            offset += component.offset
        }
    }

    override fun getOffset(): Int {
        return height + componentsOffset()
    }

    fun componentsOffset(): Int{
        return (components.stream().filter { component: Component -> component.isVisible }
            .mapToInt { obj: Component -> obj.offset }.sum() * expandAnimation.getAnimationFactor()).toInt()
    }
}