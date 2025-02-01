package io.github.atilist.itemconsumptionapi.api;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Allows items to not be consumed immediately but instead take time and create effects in the process.
 */
public interface SlowlyConsumedItem {

    /**
     * Specifies for how many ticks an item needs to be used before being consumed.
     * @param itemStack ItemStack in case there is NBT relevant to usage duration.
     * @return Number of ticks until the item gets consumed.
     */
    int getUsageDuration(ItemStack itemStack);

    /**
     * Determines the interval at which the usage effect happens.
     * @param itemStack ItemStack in case there is NBT relevant to the interval.
     * @return Interval length in ticks.
     */
    int getUsageEffectInterval(ItemStack itemStack);

    /**
     * Creates an effect during item usage.
     * @param world World for effects to take place in.
     * @param user Entity which uses the item.
     * @param itemStack ItemStack in case there is NBT relevant to the effect.
     */
    void usageEffect(World world, Entity user, ItemStack itemStack);

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
