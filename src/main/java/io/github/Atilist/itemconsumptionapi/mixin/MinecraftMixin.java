package io.github.atilist.itemconsumptionapi.mixin;

import io.github.atilist.itemconsumptionapi.api.ItemUser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow public Screen currentScreen;

    @Shadow public ClientPlayerEntity player;

    @Inject(at = @At("HEAD"), method = "tick")
    public void stopUsing(CallbackInfo ci) {
        if (currentScreen == null || currentScreen.passEvents) {
            if (player instanceof ItemUser itemUser) {
                if (itemUser.itemConsumptionAPI$isSlowlyUsingItem() && !Mouse.isButtonDown(1) && !Mouse.getEventButtonState()) {
                    itemUser.itemConsumptionAPI$stopSlowlyUsingItem();
                }
            }
        }
    }
}
