package ua.lycoris.client.settings.impl.numerical

import java.util.function.Supplier

/**
 * @author PunCakeCat/Kristofer
 */
class IntegerSetting : NumericalSetting<Int> {
    constructor(name: String, value: Int, min: Int, max: Int) : super(name, value, min, max)
    constructor(name: String, value: Int, min: Int, max: Int, visibility: Supplier<Boolean>) : super(
        name, value, min, max, visibility
    )
}