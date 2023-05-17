package ua.lycoris.client.events.player

import net.minecraft.entity.MoverType
import ua.puncakecat.beet.Cancellable
import ua.puncakecat.beet.Event

@Cancellable
class PlayerMoveEvent(var type: MoverType, var x: Double, var y: Double, var z: Double) : Event()