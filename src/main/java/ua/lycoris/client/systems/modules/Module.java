package ua.lycoris.client.systems.modules;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import ua.lycoris.client.Lycoris;
import ua.lycoris.client.settings.impl.primitive.BindSetting;
import ua.lycoris.client.settings.impl.primitive.BooleanSetting;
import ua.lycoris.client.settings.impl.primitive.DrawnSetting;
import ua.lycoris.client.settings.impl.primitive.ResetSetting;
import ua.lycoris.client.utils.interfaces.Globals;

import java.util.function.Supplier;

public class Module implements Globals {
    public Minecraft mc = Minecraft.getMinecraft();

    private String name;
    private Category category;
    private String description;

    public BooleanSetting isEnabled = new BooleanSetting("Enabled", false, () -> false);
    public BooleanSetting isOpened = new BooleanSetting("Opened", false, () -> false);
    public BindSetting bind = new BindSetting("Bind", Keyboard.KEY_NONE);

    public DrawnSetting drawn = new DrawnSetting("Drawn", true, displayDrawn());
    public ResetSetting reset = new ResetSetting("Reset", displayReset());

    public Module(){
        if(!getClass().isAnnotationPresent(ModuleManifest.class))
            throw new ModuleException("Didn't find any module manifest annotation");
        else {
            ModuleManifest manifest = getClass().getAnnotation(ModuleManifest.class);
            this.name = manifest.name();
            this.category = manifest.category();
            this.description = manifest.description();
        }
    }

    public void toggle(){
        if(isEnabled())
            onDisable();
        else
            onEnable();
    }

    public void onEnable(){
        setEnabled(true);
        Lycoris.EVENT_BUS.register(this);
        onToggle();
    }

    public void onDisable(){
        setEnabled(false);
        Lycoris.EVENT_BUS.unregister(this);
        onToggle();
    }

    public void onToggle(){}

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setEnabled(boolean enabled){
        this.isEnabled.setValue(enabled);
    }

    public boolean isEnabled(){
        return this.isEnabled.getValue();
    }

    public void setOpened(boolean opened){
        this.isOpened.setValue(opened);
    }

    public boolean isOpened(){
        return this.isOpened.getValue();
    }

    public void toggleOpened() {
        this.setOpened(!this.isOpened());
    }

    public void setBind(int integer){
        this.bind.setValue(integer);
    }

    public void setBind(String str){
        this.bind.setBindValue(str);
    }

    public int getBind(){
        return this.bind.getValue();
    }

    public String getDisplayInfo(){
        return "";
    }

    public Supplier<Boolean> displayReset(){
        return () -> false;
    }

    public Supplier<Boolean> displayDrawn(){
        return () -> false;
    }

    public boolean fullNullCheck(){
        return mc.player == null && mc.world == null;
    }

    public enum Category {
        CHAT,
        COMBAT,
        MOVEMENT,
        RENDER,
        MISC,
        CLIENT,
        HUD
    }

}