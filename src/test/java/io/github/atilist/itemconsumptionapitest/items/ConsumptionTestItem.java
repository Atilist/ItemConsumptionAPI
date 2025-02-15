package io.github.atilist.itemconsumptionapitest.items;

import io.github.atilist.itemconsumptionapi.api.SlowlyConsumedItem;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class ConsumptionTestItem extends TemplateItem implements SlowlyConsumedItem {
    public ConsumptionTestItem(Identifier identifier) {
        super(identifier);
        setTranslationKey(identifier.namespace, identifier.path);
    }

    @Override
    public int getUsageDuration(ItemStack itemStack) {
        return 16;
    }

    @Override
    public int getUsageEffectInterval(ItemStack itemStack) {
        return 4;
    }

    @Override
    public void usageEffect(World world, Entity user, ItemStack itemStack) {
        world.playSound(user, "random.fuse", 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public void onStopUsage(World world, Entity user, ItemStack itemStack) {
        if (itemStack != null) {
            itemStack.itemId = Item.WOODEN_SHOVEL.id;
        }
    }

    @Override
    public ItemStack onConsumption(ItemStack itemStack, Entity consumer, World world, int x, int y, int z) {
        if (itemStack == null) {
            return null;
        }
        world.playSound(consumer, "random.explode", 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
        itemStack.count--;
        return itemStack;
    }
}
