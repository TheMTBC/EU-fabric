package com.yellowfire.extratarultimate.items;

import com.yellowfire.extratarultimate.ExtratarUltimate;
import com.yellowfire.extratarultimate.blocks.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ClothItem extends Item {
    public ClothItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var blockPos = context.getBlockPos();
        var state = context.getWorld().getBlockState(blockPos);
        if (state.getBlock() == Blocks.TELDER_TABLE) {
            useOnTable(context, state, blockPos);
            if (context.getPlayer() == null || !context.getPlayer().isCreative()) {
                context.getStack().decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    protected void useOnTable(ItemUsageContext context, BlockState state, BlockPos pos) {
        context.getWorld().setBlockState(pos, Blocks.TELDER_TABLE_WITH_CLOTH.getDefaultState()
                .with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED)));
    }
}
