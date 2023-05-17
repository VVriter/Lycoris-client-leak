package ua.lycoris.client.settings.impl.primitive

import com.sun.org.apache.xpath.internal.operations.Bool
import ua.lycoris.client.settings.Setting
import java.awt.Color
import java.util.function.Supplier

/**
 * @author PunCakeCat/Kristofer
 */
class ColorSetting : Setting<Color> {
    private var isRainbow = false

    constructor(name: String, value: Color) : super(name, value)
    constructor(name: String, value: Color, visibility: Supplier<Boolean>) : super(
        name, value, visibility
    )

    fun withRainbow(rainbow: Boolean): Setting<Color> {
        isRainbow = rainbow
        /*
            Rainbow manager usage
         */return this
    }

    /**
     * @return red param from color value
     */
    val red: Int
        get() = value!!.red

    /**
     * @return green param from color value
     */
    val green: Int
        get() = value!!.green

    /**
     * @return blue param from color value
     */
    val blue: Int
        get() = value!!.blue

    /**
     * @return alpha param from color value
     */
    val alpha: Int
        get() = value!!.alpha

}