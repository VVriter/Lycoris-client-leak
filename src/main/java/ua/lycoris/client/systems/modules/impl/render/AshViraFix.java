package ua.lycoris.client.systems.modules.impl.render;

import ua.lycoris.client.events.render.Render3dEvent;
import ua.lycoris.client.systems.modules.Module;
import ua.lycoris.client.systems.modules.ModuleManifest;
import ua.puncakecat.beet.Listener;
import ua.puncakecat.beet.Subscribe;

@ModuleManifest(
        name = "Ashvira fix",
        category = Module.Category.RENDER
)
public class AshViraFix extends Module {
    @Subscribe
    public Listener<Render3dEvent> render3dEventListener = new Listener<>(e-> {

    });
}
