package com.yellowfire.extratarultimate.mixin;

import com.yellowfire.extratarultimate.blocks.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Inject(method = "getStrippedState(Lnet/minecraft/block/BlockState;)Ljava/util/Optional;", at = @At("HEAD"), cancellable = true)
    private void getStrippedState(BlockState state, CallbackInfoReturnable<Optional<BlockState>> cir) {
        if (state.getBlock() == Blocks.TELDER_LOG) {
            cir.cancel();
            state = Blocks.STRIPPED_TELDER_LOG.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS));
            cir.setReturnValue(Optional.ofNullable(state));
        }
    }
}
