package bagu_chan.bagus_lib.platform;

import bagu_chan.bagus_lib.platform.services.IPlatformHelper;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.ClientHooks;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public ResourceLocation getArmorTexture(Entity entity, ItemStack stack, ArmorMaterial.Layer material, boolean usesInner, EquipmentSlot slot) {
        return ClientHooks.getArmorTexture(entity, stack, material, usesInner, slot);
    }

    @Override
    public Model getArmorModel(LivingEntity entity, ItemStack itemStack, EquipmentSlot slot, HumanoidModel model) {
        return ClientHooks.getArmorModel(entity, itemStack, slot, model);
    }
}