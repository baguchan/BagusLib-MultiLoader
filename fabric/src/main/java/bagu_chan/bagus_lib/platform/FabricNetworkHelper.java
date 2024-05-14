package bagu_chan.bagus_lib.platform;

import bagu_chan.bagus_lib.platform.services.INetworkHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class FabricNetworkHelper implements INetworkHelper {
    @Override
    public <T extends CustomPacketPayload> void playClient(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> reader, INetworkHelper.NetworkHandler<T> handler) {
        PayloadTypeRegistry.playS2C().register(type, reader);
        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            handler.receive(payload, new PacketContext() {
                @Override
                public Player getPlayer() {
                    return context.player();
                }

                @Override
                public void queue(Runnable runnable) {
                    runnable.run();
                }
            });
        });
    }

    @Override
    public <T extends CustomPacketPayload> void playServer(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> reader, INetworkHelper.NetworkHandler<T> handler) {
        PayloadTypeRegistry.playC2S().register(type, reader);
        ServerPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            handler.receive(payload, new PacketContext() {
                @Override
                public Player getPlayer() {
                    return context.player();
                }

                @Override
                public void queue(Runnable runnable) {
                    runnable.run();
                }
            });
        });
    }

    @Override
    public <T extends CustomPacketPayload> void sendToClient(T type) {
        ClientPlayNetworking.send(type);
    }

    @Override
    public <T extends CustomPacketPayload> void sendToServer(ServerPlayer serverPlayer, T type) {
        ServerPlayNetworking.send(serverPlayer, type);
    }
}
