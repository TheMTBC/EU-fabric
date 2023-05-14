package com.yellowfire.extratarultimate.mixin;

import com.yellowfire.extratarultimate.blocks.TelderSamovarBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlintAndSteelItem.class)
public class FlintAndSteelMixin {

    @Inject(method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;", at = @At("HEAD"), cancellable = true)
    private void useOn(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        var world = context.getWorld();
        var blockPos = context.getBlockPos();
        var state = world.getBlockState(blockPos);
        var player = context.getPlayer();
        if (TelderSamovarBlock.canBeLit(state)) {
            world.playSound(player, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
            world.setBlockState(blockPos, state.with(Properties.LIT, true));
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
            if (player != null) {
                context.getStack().damage(1, player, (p) -> {
                    p.sendToolBreakStatus(context.getHand());
                });
            }
            cir.setReturnValue(ActionResult.success(world.isClient));
            cir.cancel();
        }
    }
}
