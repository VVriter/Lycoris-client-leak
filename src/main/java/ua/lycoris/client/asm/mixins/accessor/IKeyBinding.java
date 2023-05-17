package ua.lycoris.client.asm.mixins.accessor;

import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(KeyBinding.class)
public interface IKeyBinding {

    @Accessor(value = "pressed")
    boolean pressed();

    @Accessor(value = "pressed")
    void setPressed(boolean value);
}