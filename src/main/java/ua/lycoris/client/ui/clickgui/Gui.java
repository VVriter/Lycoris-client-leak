package ua.lycoris.client.ui.clickgui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import ua.lycoris.client.systems.modules.Module;
import ua.lycoris.client.ui.clickgui.listen.Executor;
import ua.lycoris.client.ui.clickgui.listen.Action;
import ua.lycoris.client.ui.clickgui.listen.mouse.MouseExecutor;

import java.io.IOException;
import java.util.*;

/**
 * @author PunCakeCat/Kristofer
 */
public class Gui extends GuiScreen {

    public static final Map<Module.Category, Item> icons = new HashMap<Module.Category, Item>() {{
        put(Module.Category.COMBAT, Items.END_CRYSTAL);
        put(Module.Category.MOVEMENT, Items.ELYTRA);
        put(Module.Category.RENDER, Items.ENDER_EYE);
        put(Module.Category.MISC, Items.LAVA_BUCKET);
        put(Module.Category.CLIENT, Items.PAPER);
        put(Module.Category.HUD, Items.COMPASS);
    }};

    public static List<Frame> frames;

    public static List<Executor> actions;

    public static boolean isDragging = false;

    public Gui(){
        frames = new ArrayList<>();
        actions = new ArrayList<>();

        for(Module.Category category : Module.Category.values())
            frames.add(new Frame(category, 50 + category.ordinal()*120, 20));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        frames.forEach(Frame::update);
        frames.forEach(frame -> frame.draw(mouseX, mouseY));

        actions.stream().filter(executor -> executor instanceof MouseExecutor && ((MouseExecutor)executor).getOnHover() != null)
                .forEach(executor -> ((MouseExecutor) executor).getProcess().accept(((MouseExecutor) executor).getOnHover()).apply(new Action.Mouse(mouseX, mouseY, 3)));

        if(isDragging)
            mouseClicked(mouseX, mouseY, 0);

    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int state) {
        actions.stream().filter(executor -> executor instanceof MouseExecutor && ((MouseExecutor)executor).getOnClick() != null)
                .forEach(executor -> ((MouseExecutor) executor).getProcess().accept(((MouseExecutor) executor).getOnClick()).apply(new Action.Mouse(mouseX, mouseY, state)));
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        actions.stream().filter(executor -> (executor instanceof MouseExecutor && ((MouseExecutor) executor).getOnRelease() != null ))
                .forEach(executor -> ((MouseExecutor) executor).getProcess().accept(((MouseExecutor) executor).getOnRelease()).apply(new Action.Mouse(mouseX, mouseY, state)));
        isDragging = false;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        frames.forEach(frame -> frame.components.forEach(e-> {e.onKeyTyped(keyCode);}));
    }

    public static void register(Executor runnable){
        actions.add(runnable);
    }
}