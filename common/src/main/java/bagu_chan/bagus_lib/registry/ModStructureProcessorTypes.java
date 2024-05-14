package bagu_chan.bagus_lib.registry;

import bagu_chan.bagus_lib.platform.Services;
import bagu_chan.bagus_lib.world.processor.BaseProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

import java.util.function.Supplier;

import static bagu_chan.bagus_lib.world.processor.BaseProcessor.CODEC;

public class ModStructureProcessorTypes {
    public static final Supplier<StructureProcessorType<BaseProcessor>> BASE = register("base", () -> () -> CODEC);

    public static void init() {
    }

    private static <T extends StructureProcessor> Supplier<StructureProcessorType<T>> register(String id, Supplier<StructureProcessorType<T>> supplier) {
        return Services.REGISTRY.registerStructureProcessor(id, supplier);
    }
}
