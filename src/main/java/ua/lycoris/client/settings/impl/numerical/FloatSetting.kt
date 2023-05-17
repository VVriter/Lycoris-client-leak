package ua.lycoris.client.settings.impl.numerical

import java.util.function.Supplier

/**
 * @author PunCakeCat/Kristofer
 */
class FloatSetting : NumericalSetting<Float> {
    constructor(name: String, value: Float, min: Float, max: Float) : super(name, value, min, max)
    constructor(name: String, value: Float, min: Float, max: Float, visibility: Supplier<Boolean>) : super(
        name, value, min, max, visibility
    )
}