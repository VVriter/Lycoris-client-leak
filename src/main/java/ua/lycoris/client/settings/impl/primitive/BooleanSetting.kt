package ua.lycoris.client.settings.impl.primitive

import ua.lycoris.client.settings.Setting
import java.util.function.Supplier

/**
 * @author PunCakeCat/Kristofer
 */
class BooleanSetting : Setting<Boolean> {
    constructor(name: String, value: Boolean) : super(name, value)
    constructor(name: String, value: Boolean, visibility: Supplier<Boolean>) : super(
        name, value, visibility
    )
}