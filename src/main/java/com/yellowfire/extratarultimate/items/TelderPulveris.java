package com.yellowfire.extratarultimate.items;

import com.yellowfire.extratarultimate.Tags;
import com.yellowfire.extratarultimate.blocks.Blocks;
import com.yellowfire.extratarultimate.blocks.TelderTableWithMagicClothBlock;
import com.yellowfire.extratarultimate.blocks.YellowlumeBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;

public class TelderPulveris extends Item {
    public TelderPulveris(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var world = context.getWorld();
        var pos = context.getBlockPos();
        var state = world.getBlockState(pos);
        if (state.getBlock() == Blocks.TELDER_TABLE_WITH_MAGIC_CLOTH) {
            if (world.isClient) {
                return ActionResult.SUCCESS;
            }
            var block = ((TelderTableWithMagicClothBlock) Blocks.TELDER_TABLE_WITH_MAGIC_CLOTH);
            var center = pos.up().toCenterPos().subtract(0, 0.4, 0);
            block.generateFood((ServerWorld) world, center);
            block.generateParticles((ServerWorld) world, center);
            consumeItem(context);
            return ActionResult.CONSUME;
        } else if (state.isIn(Tags.SOAKERS)) {
            if (world.isClient) {
                return ActionResult.SUCCESS;
            }
            var block = (YellowlumeBlock) Blocks.YELLOWLUME;
            var center = pos.toCenterPos().subtract(0, 0.4, 0);
            block.soakBlocks(world, pos);
            block.generateParticles((ServerWorld) world, center);
            consumeItem(context);
            return ActionResult.CONSUME;
        }
        return super.useOnBlock(context);
    }

    private void consumeItem(ItemUsageContext context) {
        var player = context.getPlayer();
        if (player != null && !player.isCreative()) {
            context.getStack().decrement(1);
        }
    }
}
