package io.github.atilist.itemconsumptionapitest.events.init;

import io.github.atilist.itemconsumptionapitest.items.ConsumptionTestItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ItemListener {

    public static ConsumptionTestItem consumptionTest;

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        consumptionTest = new ConsumptionTestItem(Identifier.of(NAMESPACE, "consumption_test"));
    }
}
