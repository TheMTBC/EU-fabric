package com.yellowfire.extratarultimate.entities;

import com.yellowfire.extratarultimate.ExtratarUltimate;
import com.yellowfire.extratarultimate.client.models.FennecEntityModel;
import com.yellowfire.extratarultimate.client.renderers.FennecEntityRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class Entities {
    public static EntityType<FennecEntity> FENNEC = FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FennecEntity::new)
            .dimensions(EntityDimensions.fixed(0.6F, 0.85F)).build();

    public static void register() {
        Registry.register(Registries.ENTITY_TYPE, ExtratarUltimate.id("fennec"), FENNEC);
        FabricDefaultAttributeRegistry.register(FENNEC, FennecEntity.createFennecAttributes());
    }

    public static void clientRegister() {
        EntityRendererRegistry.register(Entities.FENNEC, FennecEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(FennecEntityRenderer.MODEL_FENNEC_LAYER, FennecEntityModel::getTexturedModelData);
    }
}
