package ua.lycoris.client.utils.block.strictfacing

import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import ua.lycoris.client.utils.block.BlockUtil

/**
 * @author PunCakeCat/Kristofer
 */
object Calculator {

    fun getStrictFacing(pos: BlockPos): FacingInfo? {
        for (side in EnumFacing.values()) {
            val strictFacing = FacingInfo(side, pos)
            if (BlockUtil.isAir(pos.offset(side)))
                if (Math.signum(strictFacing.getOffsetFactor() * strictFacing.eyesOffset()
                            - strictFacing.getOffsetFactor() * strictFacing.offset(
                        strictFacing.getTranslated(strictFacing.hitVec!!))
                    ) == strictFacing.getOffsetFactor().toDouble()
                ) return strictFacing
        }

        return null
    }
}