package io.github.atilist.itemconsumptionapi.api;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Adds all functionality of SlowlyUsedItem while also adding a consumption step when the usage duration is reached.
 */
public interface SlowlyConsumedItem extends SlowlyUsedItem {

    /**
     * Called when the item has been used for long enough and is getting consumed.
     * @param itemStack ItemStack to be used in the consumption process.
     * @param consumer Entity which is consuming the item.
     * @param world World in which the consumption happens.
     * @param x X coordinate of the consumption location.
     * @param y Y coordinate of the consumption location.
     * @param z Z coordinate of the consumption location.
     * @return ItemStack after the consumption process.
     */
    ItemStack onConsumption(ItemStack itemStack, Entity consumer, World world, int x, int y, int z);
}
