package bagu_chan.bagus_lib;

import bagu_chan.bagus_lib.command.DialogCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class BagusLib implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        BagusLibCommon.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, selection) -> {
            DialogCommand.register(dispatcher, dedicated);
        });
    }
}
