package ua.lycoris.client.ui.clickgui.listen.mouse

import ua.lycoris.client.ui.clickgui.listen.Action
import ua.lycoris.client.ui.clickgui.listen.Executor
import java.util.function.Consumer

/**
 * @author PunCakeCat/Kristofer
 */
open class MouseExecutor() : Executor<Action.Mouse>() {

    var onClick: Consumer<Action.Mouse>? = null
        private set
    var onRelease: Consumer<Action.Mouse>? = null
        private set
    var onHover: Consumer<Action.Mouse>? = null
        private set

    fun setOnClick(onClick: Consumer<Action.Mouse>): MouseExecutor{
        this.onClick = onClick
        return this
    }

    fun setOnRelease(onRelease: Consumer<Action.Mouse>): MouseExecutor{
        this.onRelease = onRelease
        return this
    }

    fun setOnMove(onMove: Consumer<Action.Mouse>): MouseExecutor{
        this.onHover = onMove
        return this
    }
}