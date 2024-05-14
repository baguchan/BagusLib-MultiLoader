package bagu_chan.bagus_lib.registry;

import bagu_chan.bagus_lib.Constants;
import bagu_chan.bagus_lib.client.dialog.DialogType;
import bagu_chan.bagus_lib.client.dialog.ImageDialogType;
import bagu_chan.bagus_lib.client.dialog.ItemDialogType;
import bagu_chan.bagus_lib.client.dialog.WinDialogType;
import bagu_chan.bagus_lib.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

import static net.minecraft.resources.ResourceKey.createRegistryKey;

public class ModDialogs {
    public static final ResourceKey<Registry<DialogType>> DIALOG_REGISTRY_KEY = createRegistryKey(new ResourceLocation(Constants.MOD_ID, "dialog"));

    public static final Supplier<DialogType> DIALOGS = Services.REGISTRY.registerDialogType("dialog", DialogType::new);
    public static final Supplier<DialogType> IMAGE_DIALOG = Services.REGISTRY.registerDialogType("image_dialog", ImageDialogType::new);
    public static final Supplier<DialogType> ITEM_DIALOG = Services.REGISTRY.registerDialogType("item_dialog", ItemDialogType::new);
    public static final Supplier<DialogType> WIN_DIALOG = Services.REGISTRY.registerDialogType("win_dialog", WinDialogType::new);


    private static Registry<DialogType> registry = Services.REGISTRY.registerNewRegistry(DIALOG_REGISTRY_KEY, new ResourceLocation(Constants.MOD_ID, "dialog"));

    public static Registry<DialogType> getRegistry() {
        if (registry == null) {
            throw new IllegalStateException("Registry not yet initialized");
        }
        return registry;
    }
}