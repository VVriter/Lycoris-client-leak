package ua.lycoris.client.utils.timer

import net.minecraft.network.play.server.SPacketTimeUpdate
import ua.lycoris.client.Lycoris
import ua.lycoris.client.events.network.PacketEvent
import ua.puncakecat.beet.Listener
import ua.puncakecat.beet.Subscribe

class TpsSyncTimer {
    var times = 0

    @Subscribe
    var event = Listener { e: PacketEvent.Receive ->
        if (e.packet is SPacketTimeUpdate) {
            times++
        }
    }

    init {
        Lycoris.EVENT_BUS.register(this)
    }

    fun hasPassedTicks(ticks: Int): Boolean {
        return times >= ticks
    }

    fun reset() {
        times = 0
    }
}