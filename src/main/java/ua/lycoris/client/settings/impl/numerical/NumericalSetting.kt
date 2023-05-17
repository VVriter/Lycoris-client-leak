package ua.lycoris.client.settings.impl.numerical

import ua.lycoris.client.settings.Setting
import ua.lycoris.client.settings.SettingException
import java.util.function.Supplier

/**
 * @author PunCakeCat/Kristofer
 */
open class NumericalSetting<T : Number> : Setting<T> {
    private var min: T
    private var max: T

    constructor(name: String, value: T, min: T, max: T) : super(name, value) {
        this.min = min
        this.max = max
    }

    constructor(name: String, value: T, min: T, max: T, visibility: Supplier<Boolean>) : super(
        name, value, visibility) {
        this.min = min
        this.max = max
    }

    fun getMin(): T {
        return min
    }

    fun getMax(): T {
        return max
    }

    override var value: T
        get() = super.value
        set(value) {
            super.value = value
        }

    override fun setValue(value: T): Setting<T>? {
        return if (isInsideTheBound(value)) super.setValue(value) else throw SettingException("The value is outside of the bounds")
    }

    private fun isInsideTheBound(value: T): Boolean {
        return value.toLong() >= min.toLong() && value.toLong() <= max.toLong()
    }
}