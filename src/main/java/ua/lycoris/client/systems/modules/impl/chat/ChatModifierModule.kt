package ua.lycoris.client.systems.modules.impl.chat

import ua.lycoris.client.settings.impl.primitive.BooleanSetting
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.systems.modules.ModuleManifest

/**
 * @author PunCakeCat/Kristofer
 */
@ModuleManifest(
    name = "ChatModifier",
    category = Module.Category.CHAT
)
class ChatModifierModule : Module() {

    /**
     * @see MixinGuiChat.java
     */
    companion object{
        val fill by BooleanSetting("Fill", true)
        val outline by BooleanSetting("Outline", true)
    }
}