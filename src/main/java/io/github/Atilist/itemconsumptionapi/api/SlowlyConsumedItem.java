package io.github.atilist.itemconsumptionapi.api;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface SlowlyConsumedItem {

    int getUsageDuration();

    int getUsageSoundInterval();

    void playUsageSound(World world, Entity user);

    void playConsumptionSound(World world, Entity consumer);

    ItemStack slowUse(ItemStack itemStack, Entity user, World world, int x, int y, int z);
}
