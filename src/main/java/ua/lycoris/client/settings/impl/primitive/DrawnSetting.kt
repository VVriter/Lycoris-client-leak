package ua.lycoris.client.settings.impl.primitive

import ua.lycoris.client.settings.Setting
import java.util.function.Supplier

/**
 * @author PunCakeCat/Kristofer
 */
class DrawnSetting(name: String, value: Boolean, visibility: Supplier<Boolean>) : Setting<Boolean>(
    name, value, visibility
)