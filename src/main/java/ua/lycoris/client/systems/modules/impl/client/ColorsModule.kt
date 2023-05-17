package ua.lycoris.client.systems.modules.impl.client

import ua.lycoris.client.settings.impl.primitive.ColorSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest
import java.awt.Color

@ModuleManifest(
    name = "Colors",
    category = Module.Category.CLIENT
)
class ColorsModule : Module() {
    companion object{
        val chest by ColorSetting("Chest", Color(218,160,50))
        val enderChest by ColorSetting("EnderChest", Color.magenta)
    }
}