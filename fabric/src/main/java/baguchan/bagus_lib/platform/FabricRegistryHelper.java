package baguchan.bagus_lib.platform;

import baguchan.bagus_lib.platform.services.IRegistryHelper;
import baguchan.bagus_lib.registry.ModStructureProcessorTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

import java.util.function.Supplier;

import static baguchan.bagus_lib.Constants.MOD_ID;

public class FabricRegistryHelper implements IRegistryHelper {
    @Override
    public void init() {
        ModStructureProcessorTypes.init();
    }

    @Override
    public <T extends StructureProcessor> Supplier<StructureProcessorType<T>> registerStructureProcessor(String id, Supplier<StructureProcessorType<T>> supplier) {
        StructureProcessorType<T> structureProcessorType = Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, MOD_ID + ":" + id, supplier.get());

        return () -> structureProcessorType;
    }
}
