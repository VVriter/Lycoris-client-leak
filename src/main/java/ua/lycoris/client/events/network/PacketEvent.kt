package ua.lycoris.client.events.network

import net.minecraft.network.Packet
import ua.lycoris.client.events.network.PacketEvent
import ua.puncakecat.beet.Cancellable
import ua.puncakecat.beet.Event

@Cancellable
open class PacketEvent(val packet: Packet<*>) : Event() {

    class Receive(packet: Packet<*>) : PacketEvent(packet)
    class Send(packet: Packet<*>) : PacketEvent(packet)
}