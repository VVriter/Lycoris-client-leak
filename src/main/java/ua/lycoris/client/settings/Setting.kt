package ua.lycoris.client.settings

import java.util.function.Supplier
import kotlin.reflect.KProperty

/**
 * @author PunCakeCat/Kristofer
 */
open class Setting<T> {
    /**
     * @return setting value
     */
    //Value
    open var value: T

    //Visibility interface
    private var visibility: Supplier<Boolean>

    /**
     * @return Setting name
     */
    //Setting name
    var name: String
        private set

    constructor(name: String, value: T) {
        this.name = name
        this.value = value
        visibility = Supplier { true }
    }

    constructor(name: String, value: T, visibility: Supplier<Boolean>) {
        this.name = name
        this.value = value
        this.visibility = visibility
    }

    /**
     * @return is visible
     */
    val isVisible: Boolean
        get() = visibility.get()

    open fun setValue(value: T): Setting<T>? {
        this.value = value
        return this
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.setValue(value)
    }
}