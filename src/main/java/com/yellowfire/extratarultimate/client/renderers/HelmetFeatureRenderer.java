package com.yellowfire.extratarultimate.client.renderers;

import com.yellowfire.extratarultimate.client.models.HelmetModel;
import com.yellowfire.extratarultimate.client.models.HelmetModels;
import com.yellowfire.extratarultimate.items.HelmetTypeA;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.util.Identifier;

public class HelmetFeatureRenderer<T extends LivingEntity, M extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
    public HelmetModel helmetTypeA = new HelmetModel(HelmetModels.getTexturedModelDataHelmetTypeA());

    public HelmetFeatureRenderer(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        var helmetStack = entity.getEquippedStack(EquipmentSlot.HEAD);
        if (!helmetStack.isEmpty() && helmetStack.getItem() instanceof HelmetTypeA helmetItem) {
            var helmetModelPart = helmetTypeA.getHelmet(getContextModel().head);
            var vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(getHelmetTexture(helmetItem.getMaterial())), false, false);
            helmetModelPart.render(matrices, vertexConsumer, light, 1);
        }
    }

    public Identifier getHelmetTexture(ArmorMaterial material) {
        return new Identifier("extratar_ultimate", "textures/model/armor/" + material.getName() + "_helmet.png");
    }
}
