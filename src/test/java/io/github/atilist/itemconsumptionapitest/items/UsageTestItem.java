package io.github.atilist.itemconsumptionapitest.items;

import io.github.atilist.itemconsumptionapi.api.SlowlyUsedItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class UsageTestItem extends TemplateItem implements SlowlyUsedItem {
    public UsageTestItem(Identifier identifier) {
        super(identifier);
        setTranslationKey(identifier.namespace, identifier.path);
    }

    @Override
    public int getUsageDuration(ItemStack itemStack) {
        return 32;
    }

    @Override
    public int getUsageEffectInterval(ItemStack itemStack) {
        return 8;
    }

    @Override
    public void usageEffect(World world, Entity user, ItemStack itemStack) {
        world.playSound(user, "tile.piston.out", 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public void onStopUsage(World world, Entity user, ItemStack itemStack) {
        world.playSound(user, "mob.chickenplop", 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
    }
}
