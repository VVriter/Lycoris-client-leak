package ua.lycoris.client.utils.timer

/**
 * @author PunCakeCat/Kristofer
 */
class Timer(private val unit: Unit) {

    private var millis = System.currentTimeMillis()

    fun reset() {
        millis = System.currentTimeMillis()
    }

    fun passed(time: Number) = time.toLong() * unit.time >= System.currentTimeMillis() - millis
}