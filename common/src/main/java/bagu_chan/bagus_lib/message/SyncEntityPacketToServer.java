package bagu_chan.bagus_lib.message;

import bagu_chan.bagus_lib.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;


/*
 * client on entity spawn sends packet to server asking for data. Server gets data and sends packet to client. Client stores data.
 * So I created this to make that process easier
 *
 */
public class SyncEntityPacketToServer implements CustomPacketPayload {

    public static final StreamCodec<FriendlyByteBuf, SyncEntityPacketToServer> STREAM_CODEC = CustomPacketPayload.codec(
            SyncEntityPacketToServer::write, SyncEntityPacketToServer::new
    );
    public static final Type<SyncEntityPacketToServer> TYPE = CustomPacketPayload.createType(Constants.prefix("sync_entity").toString());


    public final UUID uuid;

    public SyncEntityPacketToServer(UUID uuid) {
        this.uuid = uuid;
    }

    public SyncEntityPacketToServer(FriendlyByteBuf buf) {
        this(buf.readUUID());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUUID(this.uuid);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}