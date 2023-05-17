package ua.lycoris.client.asm.mixins.gui;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.lycoris.client.Info;
import ua.lycoris.client.systems.modules.impl.chat.ChatModifierModule;
import ua.lycoris.client.systems.modules.impl.client.GuiModule;
import ua.lycoris.client.utils.render2d.Rectangle;
import ua.lycoris.client.utils.render2d.RenderUtils2D;

@Mixin(GuiChat.class)
public abstract class MixinGuiChat extends GuiScreen {
    @Shadow protected GuiTextField inputField;

    @Inject(method = "drawScreen",at = @At(value = "HEAD"))
    private void drawScreenPre(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        if(!this.inputField.getText().startsWith(Info.COMMAND_PREFIX)) return;
        boolean fill = ChatModifierModule.Companion.getFill();
        if(fill) RenderUtils2D.INSTANCE.drawRectangle(new Rectangle(2, this.height - 14, this.width - 2, this.height - 2), GuiModule.Companion.getJuliet());
    }

    @Inject(method = "drawScreen",at = @At(value = "RETURN"))
    private void drawScreenPost(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        if(!this.inputField.getText().startsWith(Info.COMMAND_PREFIX)) return;
        boolean outline = ChatModifierModule.Companion.getOutline();
        if(outline) RenderUtils2D.INSTANCE.drawRectangleOutline(new Rectangle(1, this.height - 15, this.width, this.height), 1, GuiModule.Companion.getRomeo());
    }
}