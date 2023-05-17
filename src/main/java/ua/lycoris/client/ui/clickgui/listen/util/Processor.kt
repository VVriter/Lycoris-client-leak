package ua.lycoris.client.ui.clickgui.listen.util

/**
 * @author PunCakeCat/Kristofer
 */
@FunctionalInterface
interface Processor<T> {
    fun accept(obj: T): Boolean
}