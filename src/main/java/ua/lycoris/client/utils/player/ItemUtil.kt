package ua.lycoris.client.utils.player

import net.minecraft.item.ItemStack
import kotlin.math.floor

/**
 * @author PunCakeCat/Kristofer
 */
object ItemUtil {

    fun getItemStackPercentage(itemStack: ItemStack) = floor(itemStack.count.toDouble() / itemStack.maxStackSize.toDouble() * 100)
}