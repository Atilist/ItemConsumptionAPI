package io.github.atilist.itemconsumptionapi.api;

import net.minecraft.item.ItemStack;

/**
 * Used by PlayerMixin to add methods related to slow item consumption.
 * ItemStackMixin accesses the methods in this interface.
 */
public interface ItemUser {

    /**
     * Starts slow consumption of an item.
     * @param itemStack ItemStack to be slowly consumed.
     * @param usageDuration Determines how long the consumptions takes in ticks.
     */
    void itemConsumptionAPI$setItemInSlowUse(ItemStack itemStack, int usageDuration);

    /**
     * Stops the slow consumption of an item.
     */
    void itemConsumptionAPI$stopSlowlyUsingItem();

    /**
     * Used for checking if anything is being consumed.
     * @return True when an item is in use and false where there is no item.
     */
    boolean itemConsumptionAPI$isSlowlyUsingItem();
}
