package com.yellowfire.extratarultimate.client.models;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class HelmetModel extends Model {
    private final ModelPart root;
    public static final String HELMET = "helmet";

    public HelmetModel(TexturedModelData modelData) {
        super(RenderLayer::getArmorCutoutNoCull);
        this.root = modelData.createModel();
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        // Не используется
    }

    private ModelPart getHelmet() {
        return root.getChild(HELMET);
    }

    public ModelPart getHelmet(ModelPart head) {
        var helmet = getHelmet();
        helmet.copyTransform(head);
        helmet.xScale *= 1.2f;
        helmet.yScale *= 1.2f;
        helmet.zScale *= 1.2f;
        return helmet;
    }
}
