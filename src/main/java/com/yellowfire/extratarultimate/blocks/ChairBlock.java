package com.yellowfire.extratarultimate.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class ChairBlock extends WaterloggableAndFacingBlock {
    private static final VoxelShape SHAPE_WEST;
    private static final VoxelShape SHAPE_NORTH;
    private static final VoxelShape SHAPE_EAST;
    private static final VoxelShape SHAPE_SOUTH;

    public ChairBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        var direction = state.get(FACING);
        if (direction == Direction.WEST) {
            return SHAPE_WEST;
        }
        if (direction == Direction.EAST) {
            return SHAPE_EAST;
        }
        if (direction == Direction.SOUTH) {
            return SHAPE_SOUTH;
        }
        return SHAPE_NORTH;
    }

    static {
        VoxelShape BODY = Block.createCuboidShape(3, 0, 3, 13.0, 7.0, 13.0);
        VoxelShape SPINE_WEST = Block.createCuboidShape(10.0, 7.0, 3.0, 13.0, 16.0, 13.0);
        SHAPE_WEST = VoxelShapes.union(BODY, SPINE_WEST);
        VoxelShape SPINE_NORTH = Block.createCuboidShape(3.0, 7.0, 10.0, 13.0, 16.0, 13.0);
        SHAPE_NORTH = VoxelShapes.union(BODY, SPINE_NORTH);
        VoxelShape SPINE_EAST = Block.createCuboidShape(3.0, 7.0, 3.0, 6.0, 16.0, 13.0);
        SHAPE_EAST = VoxelShapes.union(BODY, SPINE_EAST);
        VoxelShape SPINE_SOUTH = Block.createCuboidShape(3.0, 7.0, 3.0, 13.0, 16.0, 6.0);
        SHAPE_SOUTH = VoxelShapes.union(BODY, SPINE_SOUTH);
    }
}
