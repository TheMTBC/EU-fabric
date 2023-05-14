package com.yellowfire.extratarultimate.client.renderers;

import com.yellowfire.extratarultimate.ExtratarUltimate;
import com.yellowfire.extratarultimate.entities.FennecEntity;
import com.yellowfire.extratarultimate.client.models.FennecEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class FennecEntityRenderer extends MobEntityRenderer<FennecEntity, FennecEntityModel> {
    public static final EntityModelLayer MODEL_FENNEC_LAYER = new EntityModelLayer(ExtratarUltimate.id("fennec"), "main");

    public FennecEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new FennecEntityModel(context.getPart(MODEL_FENNEC_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(FennecEntity entity) {
        if (entity.getName().getString().equalsIgnoreCase("Faradey")) {
            return ExtratarUltimate.id("textures/entity/fennec_faradey.png");
        }
        return ExtratarUltimate.id("textures/entity/fennec.png");
    }
}
