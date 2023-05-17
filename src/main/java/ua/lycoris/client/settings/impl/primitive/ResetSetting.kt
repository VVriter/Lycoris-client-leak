package ua.lycoris.client.settings.impl.primitive

import ua.lycoris.client.settings.Setting
import java.util.function.Supplier

/**
 * @author PunCakeCat/Kristofer
 */
class ResetSetting : Setting<Boolean> {
    constructor(name: String) : super(name, false)
    constructor(name: String, visibility: Supplier<Boolean>) : super(name, false, visibility)
}