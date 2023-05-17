package ua.lycoris.client.events.render

import net.minecraft.client.gui.ScaledResolution
import net.minecraftforge.client.event.RenderGameOverlayEvent
import ua.puncakecat.beet.Cancellable
import ua.puncakecat.beet.Event

@Cancellable
class RenderGameOverlayEvent(
    val type: RenderGameOverlayEvent.ElementType,
    val partialTicks: Float,
    val resolution: ScaledResolution
) : Event()