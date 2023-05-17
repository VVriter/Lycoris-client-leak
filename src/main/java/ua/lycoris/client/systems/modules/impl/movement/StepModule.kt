package ua.lycoris.client.systems.modules.impl.movement

import net.minecraft.block.Block
import net.minecraft.init.Blocks
import net.minecraft.network.play.client.CPacketPlayer
import net.minecraft.util.math.BlockPos
import ua.lycoris.client.asm.mixins.accessor.IEntityPlayerSP
import ua.lycoris.client.asm.mixins.accessor.IMinecraft
import ua.lycoris.client.asm.mixins.accessor.ITimer
import ua.lycoris.client.events.player.UpdateWalkingPlayerEvent
import ua.lycoris.client.settings.impl.numerical.FloatSetting
import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest
import ua.lycoris.client.utils.block.BlockUtil
import ua.puncakecat.beet.Listener
import ua.puncakecat.beet.Subscribe


/**
 * @author PunCakeCat/Kristofer
 */
@ModuleManifest(
    name = "Step",
    category = Module.Category.MOVEMENT,
)
class StepModule : Module() {

    private val maxHeight by FloatSetting("MaxHeight", 1f, 0.875f, 2f)
    private val smooth by BooleanSetting("Smooth", true)
    private val timerSpeed by FloatSetting("Timer", 0.1f, 1f, 3f) { smooth }
    private val web_check by BooleanSetting("Web check", true)

    var timer = false

    private val offsets: Map<Double, DoubleArray> = hashMapOf(
        0.875 to doubleArrayOf(0.39, 0.7, 0.875),
        1.0 to doubleArrayOf(0.42, 0.75, 1.0),
        1.5 to doubleArrayOf(0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43),
        2.0 to doubleArrayOf(0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.919)
    )

    @Subscribe
    private val eventListener = Listener { event: UpdateWalkingPlayerEvent ->
        val height = getHeight()
        if(fullNullCheck()) return@Listener
        if (web_check) {
            if (BlockUtil.getBlock(BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ))) == Blocks.WEB) {
                return@Listener
            }
        }
        if(timer && (mc.player.onGround || mc.player.motionY < 0)) {
            ((mc as IMinecraft).timer as ITimer).tickLength = 50f
            timer = false
        }
        if(!canStep(height)) return@Listener
        offsets[height]?.let { offsets ->
            ((mc as IMinecraft).timer as ITimer).tickLength = timerSpeed * 100
            offsets.forEach {offset ->
                mc.player.connection.sendPacket(CPacketPlayer.Position(mc.player.posX, mc.player.posY + offset, mc.player.posZ, false))
            }
            mc.player.setPosition(mc.player.posX, mc.player.posY + offsets[offsets.lastIndex], mc.player.posZ);
            timer = true
        }
        event.isCancelled = true
    }

    private fun canStep(height: Double): Boolean {
        if (height > maxHeight) return false
        val box = mc.player.entityBoundingBox.offset(0.0, 0.05, 0.0)
        return (mc.world.getCollisionBoxes(mc.player, box.offset(0.0, height, 0.0)).isEmpty()
                && !mc.player.isOnLadder && !mc.player.isInWater && !mc.player.isInLava
                && mc.player.onGround && (mc.player as IEntityPlayerSP).prevOnGround
                && mc.player.collidedHorizontally)
    }

    private fun getHeight(): Double {
        var maxY = 0.0
        val grow = mc.player.entityBoundingBox.offset(0.0, 0.05, 0.0).grow(0.05)
        for (aabb in mc.world.getCollisionBoxes(mc.player, grow))
            if (aabb.maxY > maxY)
                maxY = aabb.maxY
        return maxY - mc.player.posY
    }
}