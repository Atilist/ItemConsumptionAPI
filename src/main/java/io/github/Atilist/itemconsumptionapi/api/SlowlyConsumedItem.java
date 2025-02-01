package io.github.atilist.itemconsumptionapi.api;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface SlowlyConsumedItem {

    int getUsageDuration(ItemStack itemStack);

    int getUsageSoundInterval(ItemStack itemStack);

    void playUsageSound(World world, Entity user, ItemStack itemStack);

    void playConsumptionSound(World world, Entity consumer, ItemStack itemStack);

    ItemStack slowUse(ItemStack itemStack, Entity user, World world, int x, int y, int z);
}
