package bagu_chan.bagus_lib.message;

import bagu_chan.bagus_lib.Constants;
import bagu_chan.bagus_lib.client.dialog.DialogType;
import bagu_chan.bagus_lib.registry.ModDialogs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class DialogMessage implements CustomPacketPayload {

    public static final StreamCodec<FriendlyByteBuf, DialogMessage> STREAM_CODEC = CustomPacketPayload.codec(
            DialogMessage::write, DialogMessage::new
    );
    public static final Type<DialogMessage> TYPE = CustomPacketPayload.createType(Constants.prefix("dialog").toString());

    public final DialogType type;
    public final CompoundTag tag;

    public DialogMessage(DialogType type, CompoundTag tag) {
        this.type = type;
        this.tag = tag;
    }

    public DialogMessage(FriendlyByteBuf buf) {
        this(ModDialogs.getRegistry().get(buf.readResourceLocation()), buf.readNbt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeUtf(ModDialogs.getRegistry().getKey(this.type).toString());
        buf.writeNbt(this.tag);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}