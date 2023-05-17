package ua.lycoris.client.utils.client

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper




object MathUtil {
    fun getPlayerPos(player: EntityPlayer): BlockPos {
        return BlockPos(Math.floor(player.posX), Math.floor(player.posY), Math.floor(player.posZ))
    }
    fun booleanToNumber(b: Boolean): Number {
        return 1 and (b.hashCode() shr 1)
    }
    fun finterpTo(oldValue: Float, newValue: Float, ticks: Float, speed: Float): Float {
        if (speed <= 0f) {
            return newValue
        }
        val dist = newValue - oldValue
        if (Math.pow(dist.toDouble(), 2.0) < 20) {
            return newValue
        }
        val deltaMove = dist * MathHelper.clamp(ticks * speed, 0f, 1f)
        return oldValue + deltaMove
    }
}