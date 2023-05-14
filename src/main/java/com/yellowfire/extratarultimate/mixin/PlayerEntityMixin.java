package com.yellowfire.extratarultimate.mixin;

import com.yellowfire.extratarultimate.blocks.AnchorBlock;
import com.yellowfire.extratarultimate.blocks.Blocks;
import com.yellowfire.extratarultimate.effects.Effects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntityMixin {
    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"), cancellable = true)
    private void applyDamage(DamageSource source, float amount, CallbackInfo ci) {
        for (var effect : getStatusEffects()) {
            if (effect.getEffectType() == Effects.INVULNERABILITY) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "findRespawnPosition(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;FZZ)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
    private static void findRespawnPosition(ServerWorld world, BlockPos pos, float angle, boolean forced, boolean alive, CallbackInfoReturnable<Optional<Vec3d>> cir) {
        var state = world.getBlockState(pos);
        if (state.getBlock() instanceof AnchorBlock block) {
            cir.setReturnValue(AnchorBlock.findRespawnPosition(EntityType.PLAYER, world, pos));
            cir.cancel();
        }
    }
}
