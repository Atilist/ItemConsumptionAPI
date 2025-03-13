package io.github.atilist.itemconsumptionapitest.events.init;

import io.github.atilist.itemconsumptionapitest.items.ConsumptionTestItem;
import io.github.atilist.itemconsumptionapitest.items.UsageTestItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;

public class ItemListener {

    public static ConsumptionTestItem consumptionTest;
    public static UsageTestItem usageTest;

    @Entrypoint.Namespace
    public static Namespace namespace;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        consumptionTest = new ConsumptionTestItem(Identifier.of(namespace, "consumption_test"));
        usageTest = new UsageTestItem(Identifier.of(namespace, "usage_test"));
    }
}
