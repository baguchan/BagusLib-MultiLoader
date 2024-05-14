package bagu_chan.bagus_lib;

import bagu_chan.bagus_lib.client.overlay.DialogOverlay;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class BagusLibClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((matrixStack, delta) -> {
            new DialogOverlay().render(matrixStack, delta);
        });

    }
}
