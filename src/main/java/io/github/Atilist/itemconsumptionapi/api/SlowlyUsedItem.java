package io.github.atilist.itemconsumptionapi.api;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Allows items to not be used immediately but instead take time and create effects in the process.
 */
public interface SlowlyUsedItem {

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
     * Gets called when the usage is abruptly stopped.
     * @param world World for effects to take place in.
     * @param user Entity which uses the item.
     * @param itemStack ItemStack in case there is NBT relevant to the effect.
     */
    void onStopUsage(World world, Entity user, ItemStack itemStack);
}
