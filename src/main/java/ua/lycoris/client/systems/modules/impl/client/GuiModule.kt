package ua.lycoris.client.systems.modules.impl.client

import org.lwjgl.input.Keyboard
import ua.lycoris.client.settings.impl.numerical.IntegerSetting
import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.settings.impl.primitive.ColorSetting
import ua.lycoris.client.settings.impl.primitive.EnumSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest
import ua.lycoris.client.ui.clickgui.Gui
import ua.lycoris.client.utils.render2d.animation.Easing
import java.awt.Color

@ModuleManifest(name = "Gui", category = Module.Category.CLIENT)
class GuiModule : Module() {

    companion object {
        val juliet by ColorSetting("Juliet", Color(142, 4, 154, 144))
        val romeo by ColorSetting("Romeo", Color(180, 51, 217, 255))
        val animationSpeed by IntegerSetting("AnimationSpeed", 270, 1, 300)
        val easing by EnumSetting("Easing", Easing.InOutCubic)
    }

    init {
        setBind(Keyboard.KEY_U)
    }

    override fun onEnable() {
        super.onEnable()
        mc.displayGuiScreen(Gui())
        onDisable()
    }

}