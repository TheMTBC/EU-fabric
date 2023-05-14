package com.yellowfire.extratarultimate.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class SoulCatchingStructureBlock extends WaterloggableBlock {
    private static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 8, 12);

    public SoulCatchingStructureBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() == Blocks.SEALED_TELDER_JAR;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!neighborPos.equals(pos.down())){
            return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        }
        if (neighborState.getBlock() != Blocks.SEALED_TELDER_JAR) {
            world.breakBlock(pos, false);
        }
        return net.minecraft.block.Blocks.AIR.getDefaultState();
    }
}
