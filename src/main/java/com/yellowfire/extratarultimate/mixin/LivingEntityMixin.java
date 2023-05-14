package com.yellowfire.extratarultimate.mixin;

import com.yellowfire.extratarultimate.ExtratarUltimate;
import com.yellowfire.extratarultimate.effects.Effects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract Collection<StatusEffectInstance> getStatusEffects();

    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"), cancellable = true)
    private void applyDamage(DamageSource source, float amount, CallbackInfo ci) {
        for (var effect : getStatusEffects()) {
            if (effect.getEffectType() == Effects.INVULNERABILITY) {
                ci.cancel();
            }
        }
    }
}
