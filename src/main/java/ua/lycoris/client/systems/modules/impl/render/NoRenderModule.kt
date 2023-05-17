package ua.lycoris.client.systems.modules.impl.render

import ua.lycoris.client.events.norender.RenderExpBarEvent
import ua.lycoris.client.events.norender.RenderPotionEffectsEvent
import ua.lycoris.client.events.norender.RenderPumpkinOverlayEvent
import ua.lycoris.client.events.norender.RenderScoreBoardEvent
import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest
import ua.puncakecat.beet.Listener
import ua.puncakecat.beet.Subscribe

@ModuleManifest(
    name = "NoRender",
    category = Module.Category.RENDER
)
class NoRenderModule : Module() {

    val potionEffects by BooleanSetting("PotionEffects", true)
    val pumpkin by BooleanSetting("Pumpkin", true)
    val expbar by BooleanSetting("ExpBar", true)
    val scoreboard by BooleanSetting("ScoreBoard", true)

    @Subscribe
    var potionEffectsListener = Listener { e: RenderPotionEffectsEvent ->
        e.isCancelled = potionEffects
    }

    @Subscribe
    var pumpkinOverlayListener = Listener { e: RenderPumpkinOverlayEvent ->
        e.isCancelled = pumpkin
    }

    @Subscribe
    var expBarOverlayListener = Listener { e: RenderExpBarEvent ->
        e.isCancelled = expbar
    }

    @Subscribe
    var coreboardOverlayListener = Listener { e: RenderScoreBoardEvent ->
        e.isCancelled = scoreboard
    }
}