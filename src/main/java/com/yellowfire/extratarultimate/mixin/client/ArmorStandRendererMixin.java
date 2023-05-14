package com.yellowfire.extratarultimate.mixin.client;

import com.yellowfire.extratarultimate.client.renderers.HelmetFeatureRenderer;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ArmorStandArmorEntityModel;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandEntityRenderer.class)
public class ArmorStandRendererMixin extends LivingEntityRendererMixin<ArmorStandEntity, ArmorStandArmorEntityModel> {
    protected ArmorStandRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Shadow
    public Identifier getTexture(ArmorStandEntity entity) {
        return null;
    }

    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRendererFactory$Context;)V", at=@At("TAIL"))
    private void constructor(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature(
                new HelmetFeatureRenderer<>((FeatureRendererContext<ArmorStandEntity, ArmorStandArmorEntityModel>) this)
        );
    }
}
