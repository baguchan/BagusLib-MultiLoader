package bagu_chan.bagus_lib.platform;

import bagu_chan.bagus_lib.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public ResourceLocation getArmorTexture(Entity entity, ItemStack stack, ArmorMaterial.Layer material, boolean usesInner, EquipmentSlot slot) {
        return material.texture(usesInner);
    }

    @Override
    public Model getArmorModel(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel model) {
        return model;
    }
}
