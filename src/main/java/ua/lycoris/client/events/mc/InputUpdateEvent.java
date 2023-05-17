package ua.lycoris.client.events.mc;

import net.minecraft.util.MovementInput;
import ua.puncakecat.beet.Event;

public class InputUpdateEvent extends Event {
    private final MovementInput movementInput;

    public InputUpdateEvent(MovementInput movementInput) {
        this.movementInput = movementInput;
    }

    public MovementInput getMovementInput() {
        return movementInput;
    }
}