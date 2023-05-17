package ua.lycoris.client.systems.modules.impl.misc

import net.minecraft.item.ItemAxe
import net.minecraft.item.ItemPickaxe
import net.minecraft.item.ItemSpade
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest
import ua.lycoris.client.utils.block.strictfacing.Calculator
import ua.lycoris.client.utils.timer.Timer
import ua.lycoris.client.utils.timer.Unit

/**
 * @author PunCakeCat/Kristofer
 */
@ModuleManifest(
    name = "SpeedBreak",
    category = Module.Category.MISC,
    description = "Helps you break faster"
)
class SpeedBreak : Module() {

    private val strict by BooleanSetting("Strict", true)

    private val pickaxe by BooleanSetting("Pickaxe", true)
    private val axe by BooleanSetting("Axe", false)
    private val shovel by BooleanSetting("Shovel", false)

    private var timer: Timer? = null

    override fun onEnable() {
        super.onEnable()
        timer = Timer(Unit.MS)
    }

    override fun onDisable() {
        super.onDisable()
        timer = null
    }

    fun canBreak(): Boolean{
        val item = mc.player.heldItemMainhand.item
        return (item is ItemPickaxe && pickaxe)
                || (item is ItemAxe && axe)
                || (item is ItemSpade && shovel)
    }

    fun getFacing(pos: BlockPos): EnumFacing? {
        return if(strict)
            Calculator.getStrictFacing(pos)?.facing
        else
            EnumFacing.DOWN
    }

}