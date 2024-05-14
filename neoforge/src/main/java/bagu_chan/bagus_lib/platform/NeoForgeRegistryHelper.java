package bagu_chan.bagus_lib.platform;

import bagu_chan.bagus_lib.Constants;
import bagu_chan.bagus_lib.client.dialog.DialogType;
import bagu_chan.bagus_lib.platform.services.IRegistryHelper;
import bagu_chan.bagus_lib.registry.ModDialogs;
import bagu_chan.bagus_lib.registry.ModStructureProcessorTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class NeoForgeRegistryHelper implements IRegistryHelper {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR = DeferredRegister.create(BuiltInRegistries.STRUCTURE_PROCESSOR, Constants.MOD_ID);
    public static final DeferredRegister<DialogType> DIALOG_TYPE = DeferredRegister.create(ModDialogs.DIALOG_REGISTRY_KEY, Constants.MOD_ID);

    @Override
    public void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        STRUCTURE_PROCESSOR.register(modEventBus);
        DIALOG_TYPE.register(modEventBus);

        ModDialogs.init();
        ModStructureProcessorTypes.init();
    }

    @Override
    public <T> Registry<T> registerNewRegistry(ResourceKey<Registry<T>> registryKey, ResourceLocation defaultId) {
        Registry<T> registry = new RegistryBuilder<>(registryKey).sync(true).defaultKey(defaultId).create();
        FMLJavaModLoadingContext.get().getModEventBus().<NewRegistryEvent>addListener(event -> event.register(registry));
        return registry;
    }

    @Override
    public <T extends StructureProcessor> Supplier<StructureProcessorType<T>> registerStructureProcessor(String id, Supplier<StructureProcessorType<T>> supplier) {
        STRUCTURE_PROCESSOR.register(id, supplier);
        return supplier;
    }

    @Override
    public <T extends DialogType> Supplier<T> registerDialogType(String id, Supplier<T> supplier) {
        DIALOG_TYPE.register(id, supplier);
        return supplier;
    }
}
