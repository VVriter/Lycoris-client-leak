package ua.lycoris.client.utils.block.strictfacing

import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import ua.lycoris.client.utils.block.BlockUtil
import ua.lycoris.client.utils.interfaces.Globals.mc


/**
 * @author PunCakeCat/Kristofer
 */
class FacingInfo(val facing: EnumFacing, val pos: BlockPos) {

    val hitVec = BlockUtil.offset(Vec3d(pos).add(0.5, -0.5, 0.5), facing, 0.5f)

    fun getOffsetFactor(): Int {
        return facing!!.xOffset + facing.yOffset + facing.zOffset
    }

    fun getTranslatedEyes(): Vec3d {
        val eyesPos: Vec3d = mc.player.positionVector.add(0.0, mc.player.eyeHeight.toDouble(), 0.0)
        return getTranslated(eyesPos)
    }

    fun getTranslated(vec: Vec3d): Vec3d {
        return Vec3d(
            vec.x * facing!!.xOffset,
            vec.y * facing.yOffset,
            vec.z * facing.zOffset
        )
    }

    fun eyesOffset() = offset(getTranslatedEyes())

    fun offset(vec: Vec3d) = vec.x + vec.y + vec.z
}