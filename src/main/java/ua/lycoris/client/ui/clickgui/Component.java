package ua.lycoris.client.ui.clickgui;

import ua.lycoris.client.utils.interfaces.Globals;

import java.util.function.Supplier;

/**
 * @author PunCakeCat/Kristofer
 */
public class Component implements Globals {
    public int x;
    public int y;

    public int width;
    public int height;

    private Supplier<Boolean> visibility = () -> true;

    public boolean hovered = false;

    public Component(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Component(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void update(int minX, int minY){
        this.x = minX;
        this.y = minY;
    }

    public void draw(int mouseX, int mouseY){}

    public void setDragging(){
        Gui.isDragging = true;
    }

    public boolean isDragging(){
        return Gui.isDragging;
    }

    public int getOffset(){
        return this.height;
    }

    public void setVisibility(Supplier<Boolean> visibility){
        this.visibility = visibility;
    }

    public boolean isVisible(){
        return visibility.get();
    }

    public void onKeyTyped(final int keyCode){

    }
}
