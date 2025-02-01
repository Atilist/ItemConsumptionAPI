package io.github.atilist.itemconsumptionapitest.items;

import io.github.atilist.itemconsumptionapi.api.SlowlyConsumedItem;
import net.minecraft.entity.Entity;
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
    public int getUsageDuration() {
        return 16;
    }

    @Override
    public void playUsageSound(World world, Entity user) {
        world.playSound(user, "random.fuse", 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public void playConsumptionSound(World world, Entity consumer) {
        world.playSound(consumer, "random.explode", 0.5F, world.random.nextFloat() * 0.1F + 0.9F);
    }

    @Override
    public ItemStack slowUse(ItemStack itemStack, Entity user, World world, int x, int y, int z) {
        if (itemStack == null) {
            return null;
        }
        itemStack.count--;
        return itemStack;
    }
}
