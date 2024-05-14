package bagu_chan.bagus_lib.platform;

import bagu_chan.bagus_lib.client.dialog.DialogType;
import bagu_chan.bagus_lib.platform.services.IRegistryHelper;
import bagu_chan.bagus_lib.registry.ModDialogs;
import bagu_chan.bagus_lib.registry.ModStructureProcessorTypes;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

import java.util.function.Supplier;

import static bagu_chan.bagus_lib.Constants.MOD_ID;

public class FabricRegistryHelper implements IRegistryHelper {
    @Override
    public void init() {
        ModDialogs.init();
        ModStructureProcessorTypes.init();
    }

    @Override
    public <T extends StructureProcessor> Supplier<StructureProcessorType<T>> registerStructureProcessor(String id, Supplier<StructureProcessorType<T>> supplier) {
        StructureProcessorType<T> structureProcessorType = Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, MOD_ID + ":" + id, supplier.get());

        return supplier;
    }

    @Override
    public <T> Registry<T> registerNewRegistry(ResourceKey<Registry<T>> registryKey, ResourceLocation defaultId) {
        return FabricRegistryBuilder.createDefaulted(registryKey, defaultId).buildAndRegister();
    }

    @Override
    public <T extends DialogType> Supplier<T> registerDialogType(String id, Supplier<T> supplier) {
        DialogType dialogType = Registry.register(ModDialogs.getRegistry(), MOD_ID + ":" + id, supplier.get());

        return supplier;
    }
}
