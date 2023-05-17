package ua.lycoris.client.events.mc

import ua.puncakecat.beet.Event

open class InputEvent : Event() {
    class KeyInputEvent : InputEvent()
    class MouseInputEvent : InputEvent()
}