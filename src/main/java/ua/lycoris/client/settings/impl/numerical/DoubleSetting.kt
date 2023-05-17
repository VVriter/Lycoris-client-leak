package ua.lycoris.client.settings.impl.numerical

import java.util.function.Supplier

/**
 * @author PunCakeCat/Kristofer
 */
class DoubleSetting : NumericalSetting<Double> {
    constructor(name: String, value: Double, min: Double, max: Double) : super(name, value, min, max)
    constructor(
        name: String,
        value: Double,
        min: Double,
        max: Double,
        visibility: Supplier<Boolean>
    ) : super(name, value, min, max, visibility)
}