package ua.lycoris.client.events.player

import ua.puncakecat.beet.Cancellable
import ua.puncakecat.beet.Event

@Cancellable
class UpdateWalkingPlayerEvent(stage: Stage?) : Event(stage)