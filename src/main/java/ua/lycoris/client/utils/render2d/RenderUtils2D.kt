package ua.lycoris.client.utils.render2d

import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import org.lwjgl.opengl.GL11
import ua.lycoris.client.ui.clickgui.GuiUtil
import ua.lycoris.client.utils.interfaces.Globals
import java.awt.Color
import java.awt.geom.Point2D

object RenderUtils2D : Globals {
    fun drawGradientLine(one: Point2D.Double, two: Point2D.Double, startColor: Color, endColor: Color, lineWidth: Int) {
        GlStateManager.disableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.disableAlpha()
        GlStateManager.tryBlendFuncSeparate(
            GlStateManager.SourceFactor.SRC_ALPHA,
            GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
            GlStateManager.SourceFactor.ONE,
            GlStateManager.DestFactor.ZERO
        )
        GlStateManager.glLineWidth(lineWidth.toFloat())
        GlStateManager.shadeModel(7425)
        Globals.bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR)
        Globals.bufferbuilder.pos(one.getX(), one.getY(), 0.0)
            .color(startColor.red, startColor.green, startColor.blue, startColor.alpha).endVertex()
        Globals.bufferbuilder.pos(two.getX(), two.getY(), 0.0)
            .color(endColor.red, endColor.green, endColor.blue, endColor.alpha).endVertex()
        Globals.tessellator.draw()
        GlStateManager.shadeModel(7424)
        GlStateManager.disableBlend()
        GlStateManager.enableAlpha()
        GlStateManager.enableTexture2D()
    }

    fun drawGradientRectVertical(rectangle: Rectangle, startColor: Color, endColor: Color) {
        val zLevel = 0.0
        val f = startColor.red.toFloat() / 255.0f
        val f1 = startColor.green.toFloat() / 255.0f
        val f2 = startColor.blue.toFloat() / 255.0f
        val f3 = startColor.alpha.toFloat() / 255.0f
        val f4 = endColor.red.toFloat() / 255.0f
        val f5 = endColor.green.toFloat() / 255.0f
        val f6 = endColor.blue.toFloat() / 255.0f
        val f7 = endColor.alpha.toFloat() / 255.0f
        val x = rectangle.x.toDouble()
        val y = rectangle.y.toDouble()
        val w = rectangle.width.toDouble()
        val h = rectangle.height.toDouble()
        GlStateManager.disableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.disableAlpha()
        GlStateManager.tryBlendFuncSeparate(
            GlStateManager.SourceFactor.SRC_ALPHA,
            GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
            GlStateManager.SourceFactor.ONE,
            GlStateManager.DestFactor.ZERO
        )
        GlStateManager.shadeModel(7425)
        Globals.bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR)
        Globals.bufferbuilder.pos(x, y + h, zLevel).color(f, f1, f2, f3).endVertex()
        Globals.bufferbuilder.pos(x + w, y + h, zLevel).color(f, f1, f2, f3).endVertex()
        Globals.bufferbuilder.pos(x + w, y, zLevel).color(f4, f5, f6, f7).endVertex()
        Globals.bufferbuilder.pos(x, y, zLevel).color(f4, f5, f6, f7).endVertex()
        Globals.tessellator.draw()
        GlStateManager.shadeModel(7424)
        GlStateManager.disableBlend()
        GlStateManager.enableAlpha()
        GlStateManager.enableTexture2D()
    }

    fun drawGradientRectHorizontal(rectangle: Rectangle, startColor: Color, endColor: Color) {
        val zLevel = 0.0
        val f = startColor.red.toFloat() / 255.0f
        val f1 = startColor.green.toFloat() / 255.0f
        val f2 = startColor.blue.toFloat() / 255.0f
        val f3 = startColor.alpha.toFloat() / 255.0f
        val f4 = endColor.red.toFloat() / 255.0f
        val f5 = endColor.green.toFloat() / 255.0f
        val f6 = endColor.blue.toFloat() / 255.0f
        val f7 = endColor.alpha.toFloat() / 255.0f
        val x = rectangle.x.toDouble()
        val y = rectangle.y.toDouble()
        val w = rectangle.width.toDouble()
        val h = rectangle.height.toDouble()
        GlStateManager.disableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.disableAlpha()
        GlStateManager.tryBlendFuncSeparate(
            GlStateManager.SourceFactor.SRC_ALPHA,
            GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
            GlStateManager.SourceFactor.ONE,
            GlStateManager.DestFactor.ZERO
        )
        GlStateManager.shadeModel(7425)
        Globals.bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR)
        Globals.bufferbuilder.pos(x, y + h, zLevel).color(f4, f5, f6, f7).endVertex()
        Globals.bufferbuilder.pos(x + w, y + h, zLevel).color(f, f1, f2, f3).endVertex()
        Globals.bufferbuilder.pos(x + w, y, zLevel).color(f, f1, f2, f3).endVertex()
        Globals.bufferbuilder.pos(x, y, zLevel).color(f4, f5, f6, f7).endVertex()
        Globals.tessellator.draw()
        GlStateManager.shadeModel(7424)
        GlStateManager.disableBlend()
        GlStateManager.enableAlpha()
        GlStateManager.enableTexture2D()
    }

    fun drawRectAlpha(rectangle: Rectangle, color: Color, alpha: Float) {
        val zLevel = 0.0
        val f = color.red.toFloat() / 255.0f
        val f1 = color.green.toFloat() / 255.0f
        val f2 = color.blue.toFloat() / 255.0f
        val x = rectangle.x.toDouble()
        val y = rectangle.y.toDouble()
        val w = rectangle.width.toDouble()
        val h = rectangle.height.toDouble()
        GlStateManager.disableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.disableAlpha()
        GlStateManager.tryBlendFuncSeparate(
            GlStateManager.SourceFactor.SRC_ALPHA,
            GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
            GlStateManager.SourceFactor.ONE,
            GlStateManager.DestFactor.ZERO
        )
        GlStateManager.shadeModel(7425)
        Globals.bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR)
        Globals.bufferbuilder.pos(x, y + h, zLevel).color(f, f1, f2, alpha).endVertex()
        Globals.bufferbuilder.pos(x + w, y + h, zLevel).color(f, f1, f2, alpha).endVertex()
        Globals.bufferbuilder.pos(x + w, y, zLevel).color(f, f1, f2, alpha).endVertex()
        Globals.bufferbuilder.pos(x, y, zLevel).color(f, f1, f2, alpha).endVertex()
        Globals.tessellator.draw()
        GlStateManager.shadeModel(7424)
        GlStateManager.disableBlend()
        GlStateManager.enableAlpha()
        GlStateManager.enableTexture2D()
    }

    fun drawRectangle(rectangle: Rectangle, color: Color) {
        GlStateManager.disableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        val x = rectangle.x.toDouble()
        val y = rectangle.y.toDouble()
        val w = rectangle.width.toDouble()
        val h = rectangle.height.toDouble()
        Globals.bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR)
        Globals.bufferbuilder.pos(x, y + h, 0.0).color(color.red, color.green, color.blue, color.alpha).endVertex()
        Globals.bufferbuilder.pos(x + w, y + h, 0.0).color(color.red, color.green, color.blue, color.alpha).endVertex()
        Globals.bufferbuilder.pos(x + w, y, 0.0).color(color.red, color.green, color.blue, color.alpha).endVertex()
        Globals.bufferbuilder.pos(x, y, 0.0).color(color.red, color.green, color.blue, color.alpha).endVertex()
        Globals.tessellator.draw()
        GlStateManager.enableTexture2D()
        GlStateManager.disableBlend()
    }

    fun drawRectangleOutlineLinesMode(rectangle: Rectangle, width: Float, color: Color) {
        GlStateManager.disableTexture2D()
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        GlStateManager.glLineWidth(width)
        val x = rectangle.x.toDouble()
        val y = rectangle.y.toDouble()
        val w = rectangle.width.toDouble()
        val h = rectangle.height.toDouble()
        Globals.bufferbuilder.begin(GL11.GL_LINE_LOOP, DefaultVertexFormats.POSITION_COLOR)
        Globals.bufferbuilder.pos(x, y + h, 0.0).color(color.red, color.green, color.blue, color.alpha).endVertex()
        Globals.bufferbuilder.pos(x + w, y + h, 0.0).color(color.red, color.green, color.blue, color.alpha).endVertex()
        Globals.bufferbuilder.pos(x + w, y, 0.0).color(color.red, color.green, color.blue, color.alpha).endVertex()
        Globals.bufferbuilder.pos(x, y, 0.0).color(color.red, color.green, color.blue, color.alpha).endVertex()
        Globals.tessellator.draw()
        GlStateManager.enableTexture2D()
        GlStateManager.disableBlend()
    }

    fun drawRectangleOutline(rectangle: Rectangle, width: Float, color: Color) {
        val x = rectangle.x.toDouble()
        val y = rectangle.y.toDouble()
        val w = rectangle.width.toDouble()
        val h = rectangle.height.toDouble()

        //Left side
        drawRectangle(Rectangle(x, y + width, width, h - width), color)
        //Right side
        drawRectangle(Rectangle(x + w - width, y + width, width, h - width), color)
        //Up side
        drawRectangle(Rectangle(x, y, w, width), color)
        //Down side
        drawRectangle(Rectangle(x, y + h - width, w, width), color)
    }

    fun drawGradientRectangleOutline(rectangle: Rectangle, width: Float, color1: Color, color2: Color) {
        val x = rectangle.x.toDouble()
        val y = rectangle.y.toDouble()
        val w = rectangle.width.toDouble()
        val h = rectangle.height.toDouble()

        //Left side
        drawGradientRectVertical(Rectangle(x, y + width, width, h - width), color2, color1)
        //Right side
        drawGradientRectVertical(Rectangle(x + w - width, y + width, width, h - width), color2, color1)
        //Up side
        drawRectangle(Rectangle(x, y, w, width), color1)
        //Down side
        drawRectangle(Rectangle(x, y + h, w, width), color2)
    }

    fun pushScissor(rectangle: Rectangle) {
        GL11.glEnable(GL11.GL_SCISSOR_TEST)
        val resolution = ScaledResolution(Globals.mc)
        val sx: Int = rectangle.x.toInt() * resolution.getScaleFactor()
        val sy: Int = (resolution.getScaledHeight() - (rectangle.y.toInt() + rectangle.height.toInt())) * resolution.getScaleFactor()
        val sWidth: Int = (rectangle.x.toInt() + rectangle.width.toInt() - rectangle.x.toInt()) * resolution.getScaleFactor()
        val sHeight: Int = (rectangle.y.toInt() + rectangle.height.toInt() - rectangle.y.toInt()) * resolution.getScaleFactor()
        GL11.glScissor(sx, sy, sWidth, sHeight)
    }

    fun popScissor() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST)
    }
}