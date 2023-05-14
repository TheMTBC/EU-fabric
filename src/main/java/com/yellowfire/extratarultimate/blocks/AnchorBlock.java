package com.yellowfire.extratarultimate.blocks;

import com.google.common.collect.ImmutableList;
import com.yellowfire.extratarultimate.ExtratarUltimate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Dismounting;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.CollisionView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AnchorBlock extends Block {
    public static DirectionProperty FACING = Properties.FACING;
    public static EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;


    public AnchorBlock(Settings settings) {
        super(settings);

        setDefaultState(getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(HALF, DoubleBlockHalf.LOWER));
    }

    public static VoxelShape SHAPE = Block.createCuboidShape(5, 0, 5, 11, 16, 11);

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        var blockPos = ctx.getBlockPos().up();
        var world = ctx.getWorld();
        if (!world.getBlockState(blockPos).canReplace(ctx) || blockPos.getY() >= world.getTopY() - 1) {
            return null;
        }
        var state = super.getPlacementState(ctx);
        return state != null ? state.with(FACING, ctx.getPlayerFacing().getOpposite()) : null;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        BlockPos blockPos = pos.up();
        world.setBlockState(blockPos, state.with(HALF, DoubleBlockHalf.UPPER));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
        builder.add(HALF);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.CONSUME;
        }
        var generalBlockPos = getGeneralBlockPos(world, pos);
        if (generalBlockPos.isPresent() && !pos.equals(generalBlockPos.get())) {
            return onUse(world.getBlockState(generalBlockPos.get()), world, generalBlockPos.get(), player, hand, hit);
        }
        var serverPlayer = (ServerPlayerEntity) player;
        serverPlayer.setSpawnPoint(world.getRegistryKey(), pos, 0, true, true);
        return ActionResult.SUCCESS;
    }

    public Optional<BlockPos> getGeneralBlockPos(World world, BlockPos pos) {
        var state = world.getBlockState(pos);
        if (state.getBlock() != com.yellowfire.extratarultimate.blocks.Blocks.ANCHOR) {
            return Optional.empty();
        }
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            var lowerBlockPos = pos.down();
            var lowerBlockState = world.getBlockState(lowerBlockPos);
            if (lowerBlockState.getBlock() == com.yellowfire.extratarultimate.blocks.Blocks.ANCHOR && lowerBlockState.get(HALF) == DoubleBlockHalf.LOWER) {
                return Optional.of(lowerBlockPos);
            }
        }
        return Optional.of(pos);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            onBreakInCreative(world, pos, state, player);
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            var blockPos = pos.down();
            var st = world.getBlockState(blockPos);
            if (st.isAir()) {
                return net.minecraft.block.Blocks.AIR.getDefaultState();
            }
        }
        return state;
    }

    public void onBreakInCreative(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (doubleBlockHalf == DoubleBlockHalf.UPPER) {
            BlockPos lowerBlock = pos.down();
            BlockState lowerBlockState = world.getBlockState(lowerBlock);
            if (lowerBlockState.isOf(state.getBlock()) && lowerBlockState.get(HALF) == DoubleBlockHalf.LOWER) {
                world.setBlockState(lowerBlock, Blocks.AIR.getDefaultState(), 35);
                world.syncWorldEvent(player, 2001, lowerBlock, Block.getRawIdFromState(lowerBlockState));
            }
        }

    }

    // Непонятный код для респавнинга
    private static final ImmutableList<Vec3i> VALID_SPAWN_OFFSETS;

    public static Optional<Vec3d> findRespawnPosition(EntityType<?> entity, CollisionView world, BlockPos pos) {
        Optional<Vec3d> optional = findRespawnPosition(entity, world, pos, true);
        return optional.isPresent() ? optional : findRespawnPosition(entity, world, pos, false);
    }

    private static Optional<Vec3d> findRespawnPosition(EntityType<?> entity, CollisionView world, BlockPos pos, boolean ignoreInvalidPos) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        var validPositions = VALID_SPAWN_OFFSETS.iterator();

        Vec3d finalPosition;
        do {
            if (!validPositions.hasNext()) {
                return Optional.empty();
            }

            Vec3i position = validPositions.next();
            mutable.set(pos).move(position);
            finalPosition = Dismounting.findRespawnPos(entity, world, mutable, ignoreInvalidPos);
        } while(finalPosition == null);

        return Optional.of(finalPosition);
    }

    static {
        var VALID_HORIZONTAL_SPAWN_OFFSETS = ImmutableList.of(new Vec3i(0, 0, -1), new Vec3i(-1, 0, 0), new Vec3i(0, 0, 1), new Vec3i(1, 0, 0), new Vec3i(-1, 0, -1), new Vec3i(1, 0, -1), new Vec3i(-1, 0, 1), new Vec3i(1, 0, 1));
        VALID_SPAWN_OFFSETS = (new ImmutableList.Builder()).addAll(VALID_HORIZONTAL_SPAWN_OFFSETS).addAll(VALID_HORIZONTAL_SPAWN_OFFSETS.stream().map(Vec3i::down).iterator()).addAll(VALID_HORIZONTAL_SPAWN_OFFSETS.stream().map(Vec3i::up).iterator()).add(new Vec3i(0, 1, 0)).build();
    }
}
