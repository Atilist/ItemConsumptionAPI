package io.github.atilist.itemconsumptionapi.mixin;

import io.github.atilist.itemconsumptionapi.api.ItemUser;
import io.github.atilist.itemconsumptionapi.api.SlowlyUsedItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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

    @Shadow public World world;

    @Inject(at = @At("HEAD"), method = "tick")
    public void stopUsing(CallbackInfo ci) {
        if (!(currentScreen == null || currentScreen.passEvents)) {
            return;
        }
        if (player instanceof ItemUser itemUser) {
            if (!(itemUser.itemConsumptionAPI$isSlowlyUsingItem() && !Mouse.isButtonDown(1) && !Mouse.getEventButtonState())) {
                return;
            }
            ItemStack usedItemStack = itemUser.itemConsumptionAPI$getItemInUse();
            if (usedItemStack != null) {
                Item usedItem = usedItemStack.getItem();
                if (usedItem instanceof SlowlyUsedItem slowlyUsedItem) {
                    slowlyUsedItem.onStopUsage(world, player, usedItemStack);
                }
            }
            itemUser.itemConsumptionAPI$stopSlowlyUsingItem();
        }
    }
}
