package ua.lycoris.client.utils.render2d.animation

import java.util.function.Supplier
import kotlin.math.sign

/**
 * @author PunCakeCat/Kristofer
 */
class Animation(private val duration: Double, private val easing: Supplier<Easing>, private val task: Supplier<Any>) {

    //Start value
    private var linearValue = Util.convertValue(task.get())
    private var lastMs = 0L

    /**
     * @return animation factor in range from 0 to 1 base on duration and time passed science last calling
     */
    fun getAnimationFactor(): Double {
        val offset = if (lastMs == 0L) 0.0 else ((System.currentTimeMillis() - lastMs) / duration)
        lastMs = System.currentTimeMillis()

        //Negative factor, tells should we add or subtract the offset
        val negativeFactor = sign(Util.convertValue(task.get()) - linearValue)

        //Calculating new linear value basing on the nF and on the offset
        linearValue = Util.clamp(linearValue + negativeFactor*offset, 0.0, 1.0)
        //linear value but with applied easing this time
        return Util.clamp(applyEasing(linearValue), 0.0, 1.0)
    }

    private fun applyEasing(x: Double) = easing.get().easing.invoke(x)
}