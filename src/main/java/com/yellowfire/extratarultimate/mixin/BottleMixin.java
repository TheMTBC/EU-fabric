package com.yellowfire.extratarultimate.mixin;

import com.yellowfire.extratarultimate.ExtratarUltimate;
import com.yellowfire.extratarultimate.Sounds;
import com.yellowfire.extratarultimate.blocks.Blocks;
import com.yellowfire.extratarultimate.blocks.TelderSamovarBlock;
import com.yellowfire.extratarultimate.items.Items;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlassBottleItem.class)
public abstract class BottleMixin extends Item {
    public BottleMixin(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        var world = context.getWorld();
        var player = context.getPlayer();
        var blockPos = context.getBlockPos();
        var state = world.getBlockState(blockPos);
        var stack = context.getStack();
        if (state.getBlock() == Blocks.TELDER_SAMOVAR && state.get(TelderSamovarBlock.LIT)) {
            if (player != null) {
                var droppedStack = new ItemStack(Items.BOILED_WATER_BOTTLE);
                if (stack.getCount() == 1) {
                    player.getInventory().setStack(player.getInventory().getSlotWithStack(stack), droppedStack);
                } else {
                    stack.decrement(1);
                    if (!player.getInventory().insertStack(droppedStack)) {
                        player.dropItem(droppedStack, false);
                    }
                }
            }
            world.playSound(null, context.getBlockPos(), Sounds.TELDER_SAMOVAR_USE, SoundCategory.BLOCKS, 1f, 1f);
            world.setBlockState(blockPos, state.with(TelderSamovarBlock.LIT, false));
            return ActionResult.success(world.isClient);
        }
        return ActionResult.PASS;
    }
}
