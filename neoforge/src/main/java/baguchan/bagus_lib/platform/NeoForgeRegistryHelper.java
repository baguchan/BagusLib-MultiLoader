package baguchan.bagus_lib.platform;

import baguchan.bagus_lib.Constants;
import baguchan.bagus_lib.platform.services.IRegistryHelper;
import baguchan.bagus_lib.registry.ModStructureProcessorTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeRegistryHelper implements IRegistryHelper {
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSOR = DeferredRegister.create(BuiltInRegistries.STRUCTURE_PROCESSOR, Constants.MOD_ID);

    @Override
    public void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        STRUCTURE_PROCESSOR.register(modEventBus);

        ModStructureProcessorTypes.init();
    }

    @Override
    public <T extends StructureProcessor> Supplier<StructureProcessorType<T>> registerStructureProcessor(String id, Supplier<StructureProcessorType<T>> supplier) {
        STRUCTURE_PROCESSOR.register(id, supplier);
        return supplier;
    }

}
