package ua.lycoris.client.asm.mixins.player;

import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.events.player.SetOpaqueCubeEvent;

@Mixin(VisGraph.class)
public class MixinVisGraph
{
    @Inject(method = "setOpaqueCube", at = @At("HEAD"), cancellable = true)
    public void setOpaqueCube(BlockPos pos, CallbackInfo info)
    {
        SetOpaqueCubeEvent event = new SetOpaqueCubeEvent();
        Lycoris.EVENT_BUS.post(event);
        if (event.isCancelled())
            info.cancel();
    }
}