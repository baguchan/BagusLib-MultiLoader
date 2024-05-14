package bagu_chan.bagus_lib.platform.services;

import bagu_chan.bagus_lib.client.dialog.DialogType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

import java.util.function.Supplier;

public interface IRegistryHelper {

    void init();

    <T extends StructureProcessor> Supplier<StructureProcessorType<T>> registerStructureProcessor(String id, Supplier<StructureProcessorType<T>> supplier);

    <T extends DialogType> Supplier<T> registerDialogType(String id, Supplier<T> supplier);

    <T> Registry<T> registerNewRegistry(ResourceKey<Registry<T>> registryKey, ResourceLocation defaultId);
}