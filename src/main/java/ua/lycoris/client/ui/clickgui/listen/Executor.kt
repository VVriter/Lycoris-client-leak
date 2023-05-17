package ua.lycoris.client.ui.clickgui.listen

import ua.lycoris.client.ui.clickgui.Gui
import ua.lycoris.client.ui.clickgui.listen.util.LinkedProcess


/**
 * @author PunCakeCat/Kristofer
 */
open class Executor<T>() : IHasProcess<T> {
    override val process: LinkedProcess<T> = LinkedProcess()

    init {
        Gui.register(this)
    }
}