package bagu_chan.bagus_lib;


import bagu_chan.bagus_lib.command.DialogCommand;
import bagu_chan.bagus_lib.register.ModLootModifiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(Constants.MOD_ID)
public class BagusLib {

    public BagusLib(ModContainer modContainer, IEventBus eventBus) {

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        ModLootModifiers.LOOT_MODIFIERS.register(eventBus);
        BagusLibCommon.init();
        NeoForge.EVENT_BUS.addListener(this::registerCommands);

        modContainer.registerConfig(ModConfig.Type.CLIENT, BagusConfigs.CLIENT_SPEC);
    }

    private void registerCommands(RegisterCommandsEvent evt) {
        DialogCommand.register(evt.getDispatcher(), evt.getBuildContext());
    }
}