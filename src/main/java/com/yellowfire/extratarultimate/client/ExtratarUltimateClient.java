package com.yellowfire.extratarultimate.client;

import com.yellowfire.extratarultimate.ExtratarUltimate;
import com.yellowfire.extratarultimate.blocks.Blocks;
import com.yellowfire.extratarultimate.entities.Entities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.registry.Registries;

@Environment(EnvType.CLIENT)
public class ExtratarUltimateClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Entities.clientRegister();
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> FoliageColors.getDefaultColor(), Registries.ITEM.get(ExtratarUltimate.id("lightelet")));
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null ? BiomeColors.getGrassColor(world, pos) : FoliageColors.getDefaultColor(), Blocks.LIGHTELET);
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TELDER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TELDER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SEALED_TELDER_JAR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TELDER_TABLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TELDER_TABLE_WITH_CLOTH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TELDER_TABLE_WITH_MAGIC_CLOTH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TELDER_CRATE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.TELDER_SAMOVAR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.YELLOWLUME_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.LITTLE_JAR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.PEACH_JAM_JAR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.BERRY_JAM_JAR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.LITTLE_JAR_WITH_SOUL, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.LITTLE_JAR_WITH_DIRT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.PEACH_PIE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.SOUL_CATCHING_STRUCTURE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ANCHOR_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ANCHOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.LIGHTELET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ALLIUMINANCE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.ASTRA, RenderLayer.getCutout());
    }
}
