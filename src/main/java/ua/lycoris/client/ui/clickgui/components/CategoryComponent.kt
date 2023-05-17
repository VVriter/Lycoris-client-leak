package ua.lycoris.client.ui.clickgui.components

import net.minecraft.item.ItemStack
import ua.lycoris.client.font.FontRenderer
import ua.lycoris.client.systems.modules.Module
import ua.lycoris.client.ui.clickgui.Component
import ua.lycoris.client.ui.clickgui.Frame
import ua.lycoris.client.ui.clickgui.Gui
import ua.lycoris.client.utils.render2d.Rectangle
import ua.lycoris.client.utils.render2d.RenderUtils2D
import java.awt.Color

/**
 * @author PunCakeCat/Kristofer
 */
class CategoryComponent(val frame: Frame, val category: Module.Category, width: Int, height: Int) : Component(width, height) {
    override fun draw(mouseX: Int, mouseY: Int) {
        super.draw(mouseX, mouseY)
        RenderUtils2D.drawRectangle(Rectangle(x - 1, y, width + 2, frame.height), Romeo)
        RenderUtils2D.drawRectangle(Rectangle(x - 1, y, width + 2, frame.height), Color(1, 1, 1, 40))
        mc.renderItem.renderItemAndEffectIntoGUI(ItemStack(Gui.icons[category]), x + 2, y + 2)
        fontManager.setCurrent(regularFR)
            .drawString(FontRenderer.Mode.Shadow, category.name, x + 20.0, y + 7.0, white)
    }
}