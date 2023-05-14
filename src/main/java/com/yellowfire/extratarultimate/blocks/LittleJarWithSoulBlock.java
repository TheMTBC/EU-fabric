package com.yellowfire.extratarultimate.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SoulParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class LittleJarWithSoulBlock extends LittleJarBlock {
    public LittleJarWithSoulBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextFloat() > 0.7 && world.isClient) {
            var p = pos.toCenterPos().subtract(0, 0.3, 0);
            world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, p.x, p.y, p.z, 0,0,0);
        }
    }
}
