package baguchan.bagus_lib.platform.services;

import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

import java.util.function.Supplier;

public interface IRegistryHelper {

    void init();

    <T extends StructureProcessor> Supplier<StructureProcessorType<T>> registerStructureProcessor(String id, Supplier<StructureProcessorType<T>> supplier);
}