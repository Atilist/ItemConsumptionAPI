package io.github.atilist.itemconsumptionapi.mixin;

import io.github.atilist.itemconsumptionapi.api.ItemUser;
import io.github.atilist.itemconsumptionapi.api.SlowlyUsedItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract Item getItem();

    @Inject(at = @At("HEAD"), method = "use")
    public void initiateSlowUse(World world, PlayerEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (getItem() instanceof SlowlyUsedItem slowlyConsumedItem && user instanceof ItemUser itemUser) {
            itemUser.itemConsumptionAPI$setItemInSlowUse(ItemStack.class.cast(this), slowlyConsumedItem.getUsageDuration(ItemStack.class.cast(this)));
        }
    }
}
