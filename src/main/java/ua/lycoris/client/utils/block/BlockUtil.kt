package ua.lycoris.client.utils.block

import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.init.Blocks
import net.minecraft.init.Enchantments
import net.minecraft.init.Enchantments.EFFICIENCY
import net.minecraft.init.MobEffects
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import ua.lycoris.client.utils.interfaces.Globals
import ua.lycoris.client.utils.interfaces.Globals.mc
import java.util.*


object BlockUtil {

    fun getBlock(pos: BlockPos) = Globals.mc.world.getBlockState(pos).block

    fun posToVec3d(pos: BlockPos) = Vec3d(pos)

    fun blockBreakSpeed(blockMaterial: IBlockState, tool: ItemStack): Float {
        var mineSpeed = tool.getDestroySpeed(blockMaterial)
        val efficiencyFactor = EnchantmentHelper.getEnchantmentLevel(EFFICIENCY, tool)
        mineSpeed =
            (if (mineSpeed > 1.0 && efficiencyFactor > 0) efficiencyFactor * efficiencyFactor + mineSpeed + 1.0 else mineSpeed).toFloat()
        if (mc.player.isPotionActive(MobEffects.HASTE)) {
            mineSpeed *= 1.0f + (((Objects.requireNonNull(mc.player.getActivePotionEffect(MobEffects.HASTE))
                ?.getAmplifier())?.times(0.2f) ?: null)!!)
        }
        if (mc.player.isPotionActive(MobEffects.MINING_FATIGUE)) {
            mineSpeed *= when (Objects.requireNonNull(mc.player.getActivePotionEffect(MobEffects.MINING_FATIGUE))
                ?.getAmplifier()) {
                0 -> 0.3f
                1 -> 0.09f
                2 -> 0.0027f
                else -> 0.00081f
            }
        }
        if (!mc.player.onGround || mc.player.isInWater && EnchantmentHelper.getEnchantmentLevel(
                Enchantments.AQUA_AFFINITY,
                mc.player.inventory.armorItemInSlot(0)) == 0
        )
            mineSpeed /= 5.0f
        return mineSpeed
    }

    fun offset(vec3d: Vec3d, facing: EnumFacing, scaleFactor: Float): Vec3d? {
        return Vec3d(
            vec3d.x + facing.xOffset * scaleFactor,
            vec3d.y + facing.yOffset * scaleFactor,
            vec3d.z + facing.zOffset * scaleFactor
        )
    }

    fun isAir(pos: BlockPos) = getBlock(pos) === Blocks.AIR

    fun getSphere(radius: Int, ignoreAir: Boolean): List<BlockPos>? {
        val sphere = ArrayList<BlockPos>()
        val pos = BlockPos(mc.player.getPositionVector())
        val posX: Int = pos.x
        val posY: Int = pos.y
        val posZ: Int = pos.z
        val radiuss = radius.toInt()
        var x = posX - radiuss
        while (x.toFloat() <= posX.toFloat() + radius) {
            var z = posZ - radiuss
            while (z.toFloat() <= posZ.toFloat() + radius) {
                var y = posY - radiuss
                while (y.toFloat() < posY.toFloat() + radius) {
                    if (((posX - x) * (posX - x) + (posZ - z) * (posZ - z) + (posY - y) * (posY - y)).toFloat() < radius * radius) {
                        val position = BlockPos(x, y, z)
                        if (!ignoreAir || mc.world.getBlockState(position).getBlock() !== Blocks.AIR) {
                            sphere.add(position)
                        }
                    }
                    ++y
                }
                ++z
            }
            ++x
        }
        return sphere
    }

}