package com.yellowfire.extratarultimate.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TelderTableBlock extends WaterloggableBlock {
    private static final VoxelShape TOP;
    private static final VoxelShape BOTTOM;
    private static final VoxelShape SHAPE;

    public TelderTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    static {
        BOTTOM = Block.createCuboidShape(2,0, 2, 14, 11, 14);
        TOP = Block.createCuboidShape(0,11, 0, 16, 16, 16);
        SHAPE = VoxelShapes.union(BOTTOM, TOP);
    }
}
