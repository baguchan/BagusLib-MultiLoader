package bagu_chan.bagus_lib.message;

import bagu_chan.bagus_lib.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class RemoveAllDialogMessage implements CustomPacketPayload {

    public static final StreamCodec<FriendlyByteBuf, RemoveAllDialogMessage> STREAM_CODEC = CustomPacketPayload.codec(
            RemoveAllDialogMessage::write, RemoveAllDialogMessage::new
    );
    public static final Type<RemoveAllDialogMessage> TYPE = CustomPacketPayload.createType(Constants.prefix("remove_all_dialog").toString());


    public RemoveAllDialogMessage() {
    }

    public RemoveAllDialogMessage(FriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}