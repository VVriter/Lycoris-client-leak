package ua.lycoris.client.systems.modules.impl.movement

import net.minecraft.entity.Entity
import net.minecraft.item.ItemShield
import net.minecraft.network.play.client.CPacketPlayer
import net.minecraft.network.play.client.CPacketPlayerDigging
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import ua.lycoris.client.events.mc.InputUpdateEvent
import ua.lycoris.client.events.network.PacketEvent
import ua.lycoris.client.events.player.UpdateWalkingPlayerEvent
import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest
import ua.puncakecat.beet.Listener
import ua.puncakecat.beet.Subscribe

@ModuleManifest(
        name = "NoSlowDown",
        category = Module.Category.MOVEMENT,
        description = "Prevents slowing from items and shields / blocks"
)
class NoSlow : Module() {

    private val items by BooleanSetting("Items", true)
    private val strict by BooleanSetting("Strict", true)

    @Subscribe
    private val listener = Listener{ event: UpdateWalkingPlayerEvent ->
        if (items && mc.player.isHandActive && mc.player.getHeldItem(mc.player.activeHand).item is ItemShield) {
            if (mc.player.movementInput.moveStrafe.toInt() !== 0 || mc.player.movementInput.moveForward.toInt() !== 0 && mc.player.itemInUseMaxCount >= 8) {
                mc.player.connection.sendPacket(CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.horizontalFacing))
            }
        }
    }

    @Subscribe
    private val listener2 = Listener{ event: InputUpdateEvent ->
        if (items && mc.player.isHandActive && !mc.player.isRiding) {
            event.movementInput.moveStrafe /= 0.2f
            event.movementInput.moveForward /= 0.2f
        }
    }

    @Subscribe
    private val listener3 = Listener{ event: PacketEvent.Send ->
        if (event.packet is CPacketPlayer && strict && items
                && mc.player.isHandActive && !mc.player.isRiding) {
            mc.player.connection.sendPacket(CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, getFlooredPos(mc.player), EnumFacing.DOWN))
        }
    }

    fun getFlooredPos(entity: Entity): BlockPos? {
        return BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ))
    }
}