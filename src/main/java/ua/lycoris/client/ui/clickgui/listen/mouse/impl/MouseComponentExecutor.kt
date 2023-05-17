package ua.lycoris.client.ui.clickgui.listen.mouse.impl

import ua.lycoris.client.ui.clickgui.Component
import ua.lycoris.client.ui.clickgui.GuiUtil
import ua.lycoris.client.ui.clickgui.listen.Action
import ua.lycoris.client.ui.clickgui.listen.mouse.MouseExecutor
import ua.lycoris.client.ui.clickgui.listen.util.Processor
import java.util.function.Consumer

/**
 * @author PunCakeCat/Kristofer
 */
class MouseComponentExecutor(component: Component) : MouseExecutor() {

    init {
        process.append(
            object : Processor<Action.Mouse> {
                override fun accept(action: Action.Mouse): Boolean {
                    return GuiUtil.isHovering(component, action.x,  action.y) && component.isVisible
                }
            }
        )
        this.setOnMove{component.hovered = true}
    }
}