package ua.lycoris.client.settings.impl.primitive

import org.lwjgl.input.Keyboard
import ua.lycoris.client.settings.Setting
import java.util.function.Supplier

/**
 * @author PunCakeCat/Kristofer
 */
class BindSetting : Setting<Int> {
    constructor(name: String, value: Int) : super(name, value)
    constructor(name: String, value: Int, visibility: Supplier<Boolean>) : super(
        name, value, visibility
    )

    /**
     * @return keyboard key as name
     */
    val bindName: String
        get() = Keyboard.getKeyName(value)

    /**
     * @param str new bind value that will be converted to int
     */
    fun setBindValue(str: String): BindSetting {
        this.setValue(Keyboard.getKeyIndex(str))
        return this
    }
}