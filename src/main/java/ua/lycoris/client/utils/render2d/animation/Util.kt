package ua.lycoris.client.utils.render2d.animation

import kotlin.math.max
import kotlin.math.min

/**
 * @author PunCakeCat/Kristofer
 */
object Util {

    fun clamp(value: Double, min: Double, max: Double) = max(min, min(max, value))

    fun convertValue(value: Any): Double {
        return when(value){
            is Boolean -> booleanToNum(value).toDouble()
            is Number -> value.toDouble()
            else -> 0.0
        }
    }

    private fun booleanToNum(b: Boolean) = 1 and (b.hashCode() shr 1)
}