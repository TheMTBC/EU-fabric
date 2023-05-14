package com.yellowfire.extratarultimate.blocks;

import net.minecraft.block.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class YellowlumeBlock extends FlowerBlock {
    public YellowlumeBlock(Settings settings) {
        super(StatusEffects.SPEED, 0, settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    }

    public void soakBlocks(World world, BlockPos blockPos) {
        var pos = blockPos.down();
        for (int i = 0; i < world.random.nextInt(10); i++) {
            var st = world.getBlockState(pos);
            if (st.isAir()) {
                break;
            }
            world.setBlockState(pos, soakBlock(st));
            pos = pos.down();
        }
    }

    public void generateParticles(ServerWorld world, Vec3d center) {
        var players = world.getPlayers().stream()
                .filter(player -> player.getPos().distanceTo(center) < 100)
                .toList();
        for (var player : players) {
            world.spawnParticles(
                    player, ParticleTypes.TOTEM_OF_UNDYING, true, center.x, center.y, center.z, 50, 0.1, 0.1, 0.1, 0);
        }
    }

    private BlockState soakBlock(BlockState state) {
        if (state.getBlock() == Blocks.STONE) {
            return com.yellowfire.extratarultimate.blocks.Blocks.ETHERSOAKED_STONE.getDefaultState();
        }
        if (state.getBlock() == Blocks.COBBLESTONE) {
            return com.yellowfire.extratarultimate.blocks.Blocks.ETHERSOAKED_COBBLESTONE.getDefaultState();
        }
        return state;
    }
}
