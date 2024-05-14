package bagu_chan.bagus_lib.platform;

import bagu_chan.bagus_lib.Constants;
import bagu_chan.bagus_lib.platform.services.INetworkHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NeoForgeNetworkHelper implements INetworkHelper {
    @Override
    public <T extends CustomPacketPayload> void playClient(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> reader, INetworkHelper.NetworkHandler<T> handler) {
        FMLJavaModLoadingContext.get().getModEventBus().<RegisterPayloadHandlersEvent>addListener(event -> {
            PayloadRegistrar registrar = event.registrar(Constants.MOD_ID).versioned("1.0.0").optional();
            registrar.playToClient(type, reader, ((t, iPayloadContext) -> {
                handler.receive(t, new PacketContext() {
                    @Override
                    public Player getPlayer() {
                        return iPayloadContext.player();
                    }

                    @Override
                    public void queue(Runnable runnable) {
                        runnable.run();
                    }
                });
            }));
        });
    }

    @Override
    public <T extends CustomPacketPayload> void playServer(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> reader, INetworkHelper.NetworkHandler<T> handler) {
        FMLJavaModLoadingContext.get().getModEventBus().<RegisterPayloadHandlersEvent>addListener(event -> {
                    PayloadRegistrar registrar = event.registrar(Constants.MOD_ID).versioned("1.0.0").optional();
                    registrar.playToServer(type, reader, ((t, iPayloadContext) -> {
                        handler.receive(t, new PacketContext() {
                            @Override
                            public Player getPlayer() {
                                return iPayloadContext.player();
                            }

                            @Override
                            public void queue(Runnable runnable) {
                                runnable.run();
                            }
                        });
                    }));
                }
        );

    }

    @Override
    public <T extends CustomPacketPayload> void sendToClient(T type) {
        Minecraft.getInstance().getConnection().send(type);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToServer(ServerPlayer serverPlayer, T type) {
        serverPlayer.connection.send(type);
    }
}
