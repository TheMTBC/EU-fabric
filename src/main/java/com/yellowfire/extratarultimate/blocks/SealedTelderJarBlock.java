package com.yellowfire.extratarultimate.blocks;

import com.yellowfire.extratarultimate.items.Items;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class SealedTelderJarBlock extends AbstractTelderJarBlock {
    public SealedTelderJarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (world.random.nextFloat() < 0.5) {
            if (world.random.nextFloat() < 0.5) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 10 * 20));
            }
            if (world.random.nextFloat() < 0.5 && !world.isClient) {
                if (world.getDifficulty() != Difficulty.PEACEFUL) {
                    EntityType.SILVERFISH.spawn((ServerWorld) world, pos, SpawnReason.MOB_SUMMONED);
                }
            }
        }
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (player != null && !player.isCreative()) {
            var posCatchingStructure = pos.up();
            if (world.getBlockState(posCatchingStructure).getBlock() == Blocks.SOUL_CATCHING_STRUCTURE && !world.isClient) {
                var center = posCatchingStructure.toCenterPos();

                world.setBlockState(posCatchingStructure, net.minecraft.block.Blocks.AIR.getDefaultState());
                world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, posCatchingStructure);
                if (world.random.nextFloat() < 0.2) {
                    var itemEntity = new ItemEntity(world, center.x, center.y, center.z, new ItemStack(Blocks.LITTLE_JAR_WITH_SOUL));
                    itemEntity.setVelocity(Items.getItemVelocity(world));
                    world.spawnEntity(itemEntity);
                } else {
                    var itemEntity = new ItemEntity(world, center.x, center.y, center.z, new ItemStack(Blocks.SOUL_CATCHING_STRUCTURE));
                    itemEntity.setVelocity(Items.getItemVelocity(world));
                    world.spawnEntity(itemEntity);
                }
            }
        }
        super.onBreak(world, pos, state, player);
    }
}
