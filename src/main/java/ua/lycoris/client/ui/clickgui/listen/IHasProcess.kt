package ua.lycoris.client.ui.clickgui.listen

import ua.lycoris.client.ui.clickgui.listen.util.LinkedProcess

/**
 * @author PunCakeCat/Kristofer
 */
interface IHasProcess<T> {
    val process: LinkedProcess<T>
}