package io.github.atilist.itemconsumptionapitest.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;

public class TextureListener {

    @Entrypoint.Namespace
    public static Namespace namespace;

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        ItemListener.consumptionTest.setTexture(Identifier.of(namespace, "item/consumptionTest"));
        ItemListener.usageTest.setTexture(Identifier.of(namespace, "item/usageTest"));
    }
}
