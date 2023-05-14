package com.yellowfire.extratarultimate.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class TelderSamovarBlock extends WaterloggableAndFacingBlock {
    private static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 15, 12);

    public static BooleanProperty LIT = Properties.LIT;

    public TelderSamovarBlock(Settings settings) {
        super(settings);

        setDefaultState(getDefaultState()
                        .with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LIT);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public static boolean canBeLit(BlockState state) {
        if (state.getBlock() != Blocks.TELDER_SAMOVAR) {
            return false;
        }
        return !state.get(WATERLOGGED) && !state.get(LIT);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT) && random.nextFloat() > 0.5 && world.isClient) {
            var p = pos.toCenterPos()
                    .subtract(0, 0.45, 0)
                    .add(
                            random.nextDouble() * 0.2 - 0.1,
                            random.nextDouble() * 0.2 - 0.1,
                            random.nextDouble() * 0.2 - 0.1
                    );
            world.addParticle(ParticleTypes.FLAME, p.x, p.y, p.z, 0,0,0);
        }
    }

    @Override
    public boolean tryFillWithFluid(WorldAccess world, BlockPos pos, BlockState state, FluidState fluidState) {
        var b = super.tryFillWithFluid(world, pos, state, fluidState);
        if (b && state.get(LIT)) {
            world.setBlockState(pos, state.with(LIT, false), 3);
        }
        return b;
    }
}
