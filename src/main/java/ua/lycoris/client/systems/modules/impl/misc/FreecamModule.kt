package ua.lycoris.client.systems.modules.impl.misc

import net.minecraft.client.entity.EntityOtherPlayerMP
import net.minecraft.network.play.client.CPacketPlayer
import net.minecraft.util.math.Vec3d
import ua.lycoris.client.asm.mixins.accessor.ICPacketPlayer
import ua.lycoris.client.events.network.PacketEvent
import ua.lycoris.client.events.player.SetOpaqueCubeEvent
import ua.lycoris.client.events.player.UpdateWalkingPlayerEvent
import ua.lycoris.client.settings.impl.numerical.IntegerSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest
import ua.lycoris.client.utils.player.MovementUtil
import ua.puncakecat.beet.Listener
import ua.puncakecat.beet.Subscribe

@ModuleManifest(
    name = "Freecam",
    category = Module.Category.MISC
)
class FreecamModule : Module() {

    private var startPos: Vec3d? = null
    private var fakePlayer: EntityOtherPlayerMP? = null
    private val speedSetting by IntegerSetting("Speed", 1, 0, 5)
    override fun onEnable() {
        super.onEnable()
        startPos = mc.player.positionVector
        fakePlayer = EntityOtherPlayerMP(mc.world, mc.player.gameProfile)
        fakePlayer!!.rotationYawHead = mc.player.rotationYawHead
        fakePlayer!!.copyLocationAndAnglesFrom(mc.player)
        mc.world.addEntityToWorld(-(Math.random() * 10000).toInt(), fakePlayer)
        mc.gameSettings.gammaSetting = 10000.0F
        mc.player.capabilities.isFlying = true
    }

   @Subscribe
    var lis = Listener { e: PacketEvent.Send ->
        if (e.packet is CPacketPlayer && fakePlayer != null) {
            val packetPlayer: ICPacketPlayer = e.packet as ICPacketPlayer
            packetPlayer.x = this.fakePlayer!!.posX;
            packetPlayer.y = this.fakePlayer!!.posY;
            packetPlayer.z = this.fakePlayer!!.posZ;
        }
    }


    @Subscribe
    var evvv = Listener { e: SetOpaqueCubeEvent ->
        e.isCancelled = true
    }

    @Subscribe
    var tick = Listener { e: UpdateWalkingPlayerEvent ->
        mc.player.capabilities.isFlying = true
        if (mc.gameSettings.keyBindJump.isKeyDown)
            mc.player.motionY = speedSetting.toDouble()
        if (mc.gameSettings.keyBindSneak.isKeyDown)
            mc.player.motionY = -speedSetting.toDouble()

        val calc = MovementUtil.directionSpeed(speedSetting)
        mc.player.motionX = calc[0]
        mc.player.motionZ = calc[1]
    }

    override fun onDisable() {
        super.onDisable()
        mc.player.capabilities.isFlying = false
        fakePlayer?.let { mc.world.removeEntityFromWorld(it.entityId) }
        startPos?.let { mc.player.setPosition(it.x,it.y,it.z) }
        mc.gameSettings.gammaSetting = 1F
    }
}