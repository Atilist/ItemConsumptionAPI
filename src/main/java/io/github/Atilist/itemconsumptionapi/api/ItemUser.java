package io.github.atilist.itemconsumptionapi.api;

import net.minecraft.item.ItemStack;

public interface ItemUser {
    void itemConsumptionAPI$setItemInSlowUse(ItemStack itemStack, int usageDuration);

    void itemConsumptionAPI$stopSlowlyUsingItem();
}
