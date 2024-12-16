package com.example.examplemod.network;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.network.handlers.ServerPayloadHandler;
import com.example.examplemod.network.payload.ItemMouseScrolledPayload;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = ExampleMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetworkInitialization {

    @SubscribeEvent
    public static void registerPayloadHandlers(final RegisterPayloadHandlersEvent event){
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(
                ItemMouseScrolledPayload.TYPE,
                ItemMouseScrolledPayload.STREAM_CODEC,
                ServerPayloadHandler::handle
        );
    }
}
