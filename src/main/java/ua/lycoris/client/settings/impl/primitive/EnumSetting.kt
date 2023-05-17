package ua.lycoris.client.settings.impl.primitive

import ua.lycoris.client.settings.Setting
import ua.lycoris.client.settings.SettingException
import java.util.function.Supplier

/**
 * @author PunCakeCat/Kristofer
 */
class EnumSetting : Setting<Enum<*>> {
    constructor(name: String, value: Enum<*>) : super(name, value)
    constructor(name: String, value: Enum<*>, visibility: Supplier<Boolean>) : super(
        name, value, visibility
    )

    fun withStringValue(str: String): EnumSetting {
        if (getEnumFromString(str) != null) super.setValue(getEnumFromString(str))
        return this
    }

    /**
     * @return enum that will be got from string according to initialized value
     */
    fun getEnumFromString(value: String) = this.value.javaClass.enumConstants.asList().stream().filter{ e -> e.name.equals(value, ignoreCase = true) }.findFirst().get()
}