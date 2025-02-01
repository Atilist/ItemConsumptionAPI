package io.github.atilist.itemconsumptionapi.mixin;

import io.github.atilist.itemconsumptionapi.api.ItemUser;
import io.github.atilist.itemconsumptionapi.api.SlowlyConsumedItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntity implements ItemUser {

    @Shadow public PlayerInventory inventory;
    @Unique
    private ItemStack itemInSlowUse;
    @Unique
    private int usageDuration;

    public PlayerMixin(World world) {
        super(world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void updateItemUse(CallbackInfo ci) {
        if (itemInSlowUse != null) {
            ItemStack itemstack = inventory.getSelectedItem();
            if (itemstack == null) {
                clearItemInSlowUse();
            } else if (itemstack != itemInSlowUse) {
                clearItemInSlowUse();
            } else if (itemInSlowUse.getItem() instanceof SlowlyConsumedItem slowlyConsumedItem) {
                if (usageDuration % 4 == 0) {
                    slowlyConsumedItem.playUsageSound(world, this);
                }
                if (--usageDuration == 0 && !world.isRemote) {
                    finishConsumption(slowlyConsumedItem);
                }
            }
        }
    }

    @Unique
    public void finishConsumption(SlowlyConsumedItem slowlyConsumedItem) {
        slowlyConsumedItem.playConsumptionSound(world, this);
        int itemInSlowUseCount = itemInSlowUse.count;
        ItemStack itemstack = slowlyConsumedItem.slowUse(itemInSlowUse, this, world, (int) x, (int) y, (int) z);
        if (itemstack != itemInSlowUse || itemstack != null && itemstack.count != itemInSlowUseCount) {
            this.inventory.main[this.inventory.selectedSlot] = itemstack;
            if (itemstack == null || itemstack.count <= 0) {
                this.inventory.main[this.inventory.selectedSlot] = null;
            }
        }
        clearItemInSlowUse();
    }

    @Unique
    public void itemConsumptionAPI$setItemInSlowUse(ItemStack itemStack, int usageDuration) {
        if (itemInSlowUse == itemStack) {
            return;
        }
        itemInSlowUse = itemStack;
        this.usageDuration = usageDuration;
    }

    @Unique
    public void itemConsumptionAPI$stopSlowlyUsingItem() {
        clearItemInSlowUse();
    }

    @Unique
    public void clearItemInSlowUse() {
        itemInSlowUse = null;
        usageDuration = 0;
    }
}
