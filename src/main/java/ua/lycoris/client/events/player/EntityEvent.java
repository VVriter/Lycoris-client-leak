package ua.lycoris.client.events.player;

import net.minecraft.entity.Entity;
import ua.puncakecat.beet.Event;

public class EntityEvent extends Event {
    public Entity entity;

    public Entity get_entity() {
        return this.entity;
    }

    public EntityEvent(final Entity obf) {
        this.entity = obf;
    }

    public static class EventColision extends EntityEvent
    {
        public double z;
        public double x;
        public double y;

        public double get_y() {
            return this.y;
        }

        public double get_z() {
            return this.z;
        }

        public void set_z(final double n) {
            this.z = this.z;
        }

        public EventColision(final Entity entity, final double x, final double y, final double z) {
            super(entity);
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double get_x() {
            return this.x;
        }

        public void set_y(final double y) {
            this.y = y;
        }

        public void set_x(final double x) {
            this.x = x;
        }
    }
}
