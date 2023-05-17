package ua.lycoris.client.systems.modules.impl.misc

import net.minecraft.client.entity.EntityOtherPlayerMP
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest

@ModuleManifest(
    name = "FakePlayer",
    category = Module.Category.MISC
)
class FakePlayerModule : Module() {
    private var fakePlayer: EntityOtherPlayerMP? = null

    override fun onEnable() {
        super.onEnable()
        fakePlayer = EntityOtherPlayerMP(mc.world, mc.player.gameProfile)
        fakePlayer!!.rotationYawHead = mc.player.rotationYawHead
        fakePlayer!!.copyLocationAndAnglesFrom(mc.player)
        mc.world.addEntityToWorld(-(Math.random() * 10000).toInt(), fakePlayer)
    }

    override fun onDisable() {
        super.onDisable()
        fakePlayer?.let { mc.world.removeEntityFromWorld(it.entityId) }
    }
}