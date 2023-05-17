package ua.lycoris.client.systems.modules.impl.client

import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest

@ModuleManifest(
    name = "Targets",
    category = Module.Category.CLIENT
)
class TargetsModule : Module() {
    companion object {
        val player by BooleanSetting("Player", true)
        val mobs by BooleanSetting("Mobs", true)
        val animals by BooleanSetting("Animals", true)
        val boat by BooleanSetting("Boat", true)
        val armourStand by BooleanSetting("ArmourStand", true)
    }
}