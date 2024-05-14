package bagu_chan.bagus_lib.platform.services;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public interface INetworkHelper {
    <T extends CustomPacketPayload> void playClient(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> reader, NetworkHandler<T> handler);

    <T extends CustomPacketPayload> void playServer(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> reader, NetworkHandler<T> handler);

    <T extends CustomPacketPayload> void sendToClient(T type);

    <T extends CustomPacketPayload> void sendToServer(ServerPlayer serverPlayer, T type);

    @FunctionalInterface
    public interface NetworkHandler<T extends CustomPacketPayload> {
        void receive(T packet, PacketContext context);
    }

    public interface PacketContext {
        Player getPlayer();

        void queue(Runnable runnable);

    }
}
