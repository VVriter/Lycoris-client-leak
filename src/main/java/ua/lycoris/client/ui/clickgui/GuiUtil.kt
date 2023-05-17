package ua.lycoris.client.ui.clickgui

import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import ua.lycoris.client.systems.modules.impl.client.GuiModule.Companion.juliet
import ua.lycoris.client.systems.modules.impl.client.GuiModule.Companion.romeo
import ua.lycoris.client.utils.interfaces.Globals
import ua.lycoris.client.utils.render2d.Rectangle
import ua.lycoris.client.utils.render2d.RenderUtils2D
import java.awt.Color
import java.awt.Toolkit

object GuiUtil {

    val white = Color(255, 255, 255)
    var lightGray = Color(213, 213, 213)
    var hover = Color(217, 115, 210)

    private val settingPrefix: String = ">"

    fun drawComponentBase(component: Component){
        RenderUtils2D.drawRectangle(Rectangle(component.x, component.y, component.width, component.height),
            Color(1, 1, 1, 136))
    }

    fun stringValue(str: String): String? {
        return settingPrefix + str
    }

    fun getRomeo() = romeo

    fun getJuliet() = juliet

    fun getGuiScaleFactor(): Float {
        val resolution = Toolkit.getDefaultToolkit().screenSize
        GlStateManager.pushMatrix()
        val guiScale = (Globals.mc.displayWidth / resolution.getWidth()).toFloat()
        return getGuiScale(guiScale)
    }

    private fun getGuiScale(start: Float): Float {
        var start = start
        val scaledResolution = ScaledResolution(Globals.mc)
        if (scaledResolution.scaleFactor == 1) start += 1 * start else if (scaledResolution.scaleFactor == 3) start += (-0.3335 * start).toFloat() else if (scaledResolution.scaleFactor == 4) start += (-0.5 * start).toFloat() else if (scaledResolution.scaleFactor == 6) start += (-0.6669 * start).toFloat()
        return start
    }

    fun isHovering(component: Component, mX: Int, mY: Int): Boolean{
        return mX > component.x && mX < component.x + component.width && mY > component.y && mY < component.y + component.height
    }
}