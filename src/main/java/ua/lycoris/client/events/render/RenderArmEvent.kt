package ua.lycoris.client.events.render

import net.minecraft.entity.player.EntityPlayer
import ua.puncakecat.beet.Event

class RenderArmEvent(stage: Stage?, val player: EntityPlayer, val renderArm: arm) : Event(stage) {

    enum class arm {
        Right, Left
    }
}