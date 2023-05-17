package ua.lycoris.client.ui.clickgui.listen

/**
 * @author PunCakeCat/Kristofer
 */
class Action{
    class Mouse(val x: Int, val y: Int, val state: Int)
    class Key(val bind: Int)
}