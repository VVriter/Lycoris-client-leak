package ua.lycoris.client.systems.modules.impl.combat

import net.minecraft.network.login.server.SPacketDisconnect
import net.minecraft.util.text.TextComponentString
import ua.lycoris.client.Info
import ua.lycoris.client.events.player.UpdateWalkingPlayerEvent
import ua.lycoris.client.settings.impl.numerical.IntegerSetting
import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest
import ua.puncakecat.beet.Listener
import ua.puncakecat.beet.Subscribe
import java.io.IOException

@ModuleManifest(
    name = "AutoLog",
    category = Module.Category.COMBAT
)

class AutoLogModule : Module() {

    private var targetHealth by IntegerSetting("TargetHealth", 5, 1, 20)
    private var offpc by BooleanSetting("Shutdown", true)

    @Subscribe
    var lis = Listener { e: UpdateWalkingPlayerEvent ->
        if (mc.player.health <= targetHealth) {
            mc.player.connection.sendPacket(SPacketDisconnect(TextComponentString(Info.CHAT_PREFIX+"AutoLog!")));
            if (offpc == true) {
                val processBuilder = ProcessBuilder(
                    "shutdown",
                    "/s"
                )
                try {
                    processBuilder.start()
                } catch (e: IOException) {
                    throw RuntimeException(e)
                }
            }
        }
    }
}