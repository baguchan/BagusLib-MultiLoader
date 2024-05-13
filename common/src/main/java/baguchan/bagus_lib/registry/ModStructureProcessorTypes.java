package baguchan.bagus_lib.registry;

import baguchan.bagus_lib.platform.Services;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

import java.util.function.Supplier;

import static baguchan.bagus_lib.world.processor.BaseProcessor.CODEC;

public class ModStructureProcessorTypes {
    public static final Supplier<StructureProcessorType<?>> BASE = register("base", () -> () -> CODEC);

    public static void init() {
    }

    private static <T extends StructureProcessor> Supplier<StructureProcessorType<T>> register(String id, Supplier<StructureProcessorType<T>> supplier) {
        return Services.REGISTRY.registerStructureProcessor(id, supplier);
    }
}
