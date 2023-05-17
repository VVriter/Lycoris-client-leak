package ua.lycoris.client.utils.player

import ua.lycoris.client.utils.interfaces.Globals.mc
import kotlin.math.sign
object MovementUtil {
    fun directionSpeed(speed: Int): DoubleArray {
        var forward: Float = mc.player.movementInput.moveForward
        var side: Float = mc.player.movementInput.moveStrafe
        var yaw: Float =
            mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.renderPartialTicks
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += (if (forward > 0.0f) -45 else 45).toFloat()
            } else if (side < 0.0f) {
                yaw += (if (forward > 0.0f) 45 else -45).toFloat()
            }
            side = 0.0f
            forward = sign(forward)
        }
        val sin = Math.sin(Math.toRadians((yaw + 90.0f).toDouble()))
        val cos = Math.cos(Math.toRadians((yaw + 90.0f).toDouble()))
        val posX = forward.toDouble() * speed * cos + side.toDouble() * speed * sin
        val posZ = forward.toDouble() * speed * sin - side.toDouble() * speed * cos
        return doubleArrayOf(posX, posZ)
    }

    fun directionSpeed(speed: Double): DoubleArray? {
        var forward = mc.player.movementInput.moveForward
        var side = mc.player.movementInput.moveStrafe
        var yaw =
            mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.renderPartialTicks
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += (if (forward > 0.0f) -45 else 45).toFloat()
            } else if (side < 0.0f) {
                yaw += (if (forward > 0.0f) 45 else -45).toFloat()
            }
            side = 0.0f
            if (forward > 0.0f) {
                forward = 1.0f
            } else if (forward < 0.0f) {
                forward = -1.0f
            }
        }
        val sin = Math.sin(Math.toRadians((yaw + 90.0f).toDouble()))
        val cos = Math.cos(Math.toRadians((yaw + 90.0f).toDouble()))
        val posX = forward * speed * cos + side * speed * sin
        val posZ = forward * speed * sin - side * speed * cos
        return doubleArrayOf(posX, posZ)
    }
}