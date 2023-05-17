package ua.lycoris.client.utils.render3d

import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.RenderGlobal
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.culling.Frustum
import net.minecraft.client.renderer.culling.ICamera
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import org.lwjgl.opengl.GL11
import ua.lycoris.client.utils.interfaces.Globals
import java.awt.Color
import java.util.*


object RenderUtils3d {
    var camera: ICamera = Frustum()

    fun drawBoxESP(pos: BlockPos, color: Color, lineWidth: Float, outline: Boolean, box: Boolean, boxAlpha: Int) {
        val bb = AxisAlignedBB(
            pos.x - Globals.mc.renderManager.viewerPosX,
            pos.y - Globals.mc.renderManager.viewerPosY,
            pos.z - Globals.mc.renderManager.viewerPosZ,
            pos.x + 1 - Globals.mc.renderManager.viewerPosX,
            pos.y + 1 - Globals.mc.renderManager.viewerPosY,
            pos.z + 1 - Globals.mc.renderManager.viewerPosZ
        )
        Objects.requireNonNull(Globals.mc.renderViewEntity)?.let {
            camera.setPosition(
                it.posX,
                Globals.mc.renderViewEntity!!.posY,
                Globals.mc.renderViewEntity!!.posZ
            )
        }
        //if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(pos))) {
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.disableDepth()
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1)
        GlStateManager.disableTexture2D()
        GlStateManager.depthMask(false)
        GL11.glEnable(2848)
        GL11.glHint(3154, 4354)
        GL11.glLineWidth(lineWidth)
        if (box) {
            RenderGlobal.renderFilledBox(
                bb,
                color.red / 255.0f,
                color.green / 255.0f,
                color.blue / 255.0f,
                boxAlpha / 255.0f
            )
        }
        if (outline) {
            RenderGlobal.drawBoundingBox(
                bb.minX,
                bb.minY,
                bb.minZ,
                bb.maxX,
                bb.maxY,
                bb.maxZ,
                color.red / 255.0f,
                color.green / 255.0f,
                color.blue / 255.0f,
                color.alpha / 255.0f
            )
        }
        GL11.glDisable(2848)
        GlStateManager.depthMask(true)
        GlStateManager.enableDepth()
        GlStateManager.enableTexture2D()
        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
        //}
    }

    fun trace(e: BlockPos, color: Color, alpha: Int, lineWidth: Float) {
        if (Globals.mc.renderManager.renderViewEntity != null) {
            GL11.glDisable(GL11.GL_DEPTH_TEST)
            GL11.glDisable(GL11.GL_LIGHTING)
            GL11.glLineWidth(lineWidth)
            GL11.glPushMatrix()
            GL11.glDepthMask(false)
            GL11.glColor4d(
                (color.red / 255).toDouble(),
                (color.green / 255).toDouble(),
                (color.blue / 255).toDouble(),
                (alpha / 255).toDouble()
            )
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA)
            GL11.glDisable(GL11.GL_TEXTURE_2D)
            GL11.glBegin(GL11.GL_LINES)
            val r = Globals.mc.renderManager
            val v =
                Vec3d(0.0, 0.0, 1.0).rotatePitch(-Math.toRadians(Globals.mc.player.rotationPitch.toDouble()).toFloat())
                    .rotateYaw(-Math.toRadians(Globals.mc.player.rotationYaw.toDouble()).toFloat())
            GL11.glVertex3d(v.x, Globals.mc.player.getEyeHeight() + v.y, v.z)
            val x = e.x + 0.5
            val y = e.y + 0.5
            val z = e.z + 0.5
            GL11.glVertex3d(x - r.viewerPosX, y - r.viewerPosY, z - r.viewerPosZ)
            GL11.glEnd()
            GL11.glDepthMask(true)
            GL11.glEnable(GL11.GL_DEPTH_TEST)
            GL11.glEnable(GL11.GL_TEXTURE_2D)
            GL11.glPopMatrix()
        }
    }
}