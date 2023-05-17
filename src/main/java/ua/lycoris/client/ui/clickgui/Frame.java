package ua.lycoris.client.ui.clickgui;

import ua.lycoris.client.Lycoris;
import ua.lycoris.client.systems.modules.Module;
import ua.lycoris.client.ui.clickgui.components.CategoryComponent;
import ua.lycoris.client.ui.clickgui.components.ModuleComponent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.IntSupplier;

/**
 * @author PunCakeCat/Kristofer
 */
public class Frame {

    List<Component> components = new ArrayList<>();

    private int x;
    private int y;

    private int height;

    public Frame(Module.Category category, int x, int y){
        this.x = x;
        this.y = y;

        components.add(new CategoryComponent(this, category,110, 20));
        for(Module module : Lycoris.moduleManager.getModules(category))
            components.add(new ModuleComponent(module, 110, 15));
    }

    public void update(){
        int offset = 0;
        for(Component component : components){
            component.update(x, y + offset);
            offset += component.getOffset() + 1;
        }
        height = offset;
    }

    public void draw(int mouseX, int mouseY){
        components.forEach(component -> component.draw(mouseX, mouseY));
    }

    public int getHeight(){
        return this.height;
    }
}
