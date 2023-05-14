package com.yellowfire.extratarultimate.mixin.client;

import com.yellowfire.extratarultimate.client.renderers.HelmetFeatureRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerRendererMixin extends LivingEntityRendererMixin<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    protected PlayerRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Shadow
    public Identifier getTexture(AbstractClientPlayerEntity entity) {
        return null;
    }
    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRendererFactory$Context;Z)V", at=@At("TAIL"))
    private void constructor(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        addFeature(new HelmetFeatureRenderer<>(
                (FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>>) this
        ));
    }
}
