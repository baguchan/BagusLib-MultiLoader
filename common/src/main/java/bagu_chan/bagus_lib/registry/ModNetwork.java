package bagu_chan.bagus_lib.registry;

import bagu_chan.bagus_lib.api.IBaguPacket;
import bagu_chan.bagus_lib.client.dialog.DialogType;
import bagu_chan.bagus_lib.message.DialogMessage;
import bagu_chan.bagus_lib.message.RemoveAllDialogMessage;
import bagu_chan.bagus_lib.message.SyncEntityPacketToServer;
import bagu_chan.bagus_lib.platform.Services;
import bagu_chan.bagus_lib.util.DialogHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class ModNetwork {
    public static void init() {
        Services.NETWORK.playClient(DialogMessage.TYPE, DialogMessage.STREAM_CODEC, (packet, context) -> {
            context.queue(() -> {
                DialogType dialogType = packet.type;
                dialogType.readTag(packet.tag);
                DialogHandler.INSTANCE.addOrReplaceDialogType("Command", dialogType);
            });
        });
        Services.NETWORK.playClient(RemoveAllDialogMessage.TYPE, RemoveAllDialogMessage.STREAM_CODEC, (packet, context) -> {
            context.queue(() -> {
                DialogHandler.INSTANCE.removeAllDialogType();
            });
        });
        Services.NETWORK.playServer(SyncEntityPacketToServer.TYPE, SyncEntityPacketToServer.STREAM_CODEC, (packet, context) -> {
            context.queue(() -> {
                Player player = context.getPlayer();
                Entity entity = ((ServerLevel) player.level()).getEntity(packet.uuid);
                if (entity instanceof IBaguPacket baguPacket) {
                    baguPacket.resync(entity);
                }
            });
        });

    }
}
