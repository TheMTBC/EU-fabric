package com.yellowfire.extratarultimate.blocks;

import com.yellowfire.extratarultimate.items.Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TelderTableWithClothBlock extends TelderTableBlock implements BlockEntityProvider {
    private static final BooleanProperty LIT = Properties.LIT;

    public TelderTableWithClothBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
                .with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LIT);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TableWithClothBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            if (state.get(LIT)) {
                return ActionResult.SUCCESS;
            } else if (!player.getStackInHand(hand).isEmpty()) {
                return ActionResult.SUCCESS;
            }
        }
        if (world.getBlockEntity(pos) instanceof TableWithClothBlockEntity blockEntity && blockEntity.hasItemStack()) {
            dropStack(world, pos, player, blockEntity.getStack(0));
            blockEntity.clear();
            return ActionResult.CONSUME;
        } else if (!player.getStackInHand(hand).isEmpty() && world.getBlockEntity(pos) instanceof TableWithClothBlockEntity blockEntity) {
            var stack = player.getStackInHand(hand);
            player.getInventory().removeOne(stack);
            blockEntity.setStack(0, stack);
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    private void dropStack(World world, BlockPos pos, @Nullable PlayerEntity player, ItemStack stack) {
        var velocity = Vec3d.ZERO;
        var center = pos.up().toCenterPos();
        if (player != null) {
            var direction = player.getBlockPos().up().toCenterPos().subtract(center).normalize();
            velocity = new Vec3d(1, 1, 1).multiply(0.15).multiply(direction);
        } else {
            velocity = Items.getItemVelocity(world);
        }
        var itemEntity = new ItemEntity(world, center.x, center.y, center.z, stack);
        itemEntity.setVelocity(velocity);
        world.spawnEntity(itemEntity);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TableWithClothBlockEntity clothBlockEntity && clothBlockEntity.hasItemStack()) {
                dropStack(world, pos, (PlayerEntity) null, clothBlockEntity.getStack(0));
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
