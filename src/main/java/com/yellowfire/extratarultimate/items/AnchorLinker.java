package com.yellowfire.extratarultimate.items;

import com.yellowfire.extratarultimate.ExtratarUltimate;
import com.yellowfire.extratarultimate.blocks.AnchorBlock;
import com.yellowfire.extratarultimate.blocks.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.TotemParticle;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Optional;

public class AnchorLinker extends Item {
    public AnchorLinker(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        var world = context.getWorld();
        var clickedBlockPos = context.getBlockPos();
        var generalBlockPos = ((AnchorBlock) Blocks.ANCHOR).getGeneralBlockPos(world, clickedBlockPos);
        if (generalBlockPos.isEmpty()) {
            return ActionResult.PASS;
        }
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        var blockPos = generalBlockPos.get();
        var coordinates = new int[]{blockPos.getX(), blockPos.getY(), blockPos.getZ()};
        var stack = context.getStack();
        var nbt = stack.getNbt();
        if (nbt == null) {
            nbt = new NbtCompound();
        }
        nbt.putIntArray("anchor", coordinates);
        stack.setNbt(nbt);
        return ActionResult.CONSUME;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        var nbt = stack.getNbt();
        return nbt != null && getAnchorPos(stack).isPresent();
    }

    public Optional<BlockPos> getAnchorPos(ItemStack stack) {
        var nbt = stack.getNbt();
        if (nbt == null) {
            return Optional.empty();
        }
        var coordinates = nbt.getIntArray("anchor");
        if (coordinates.length != 3) {
            return Optional.empty();
        }
        return Optional.of(new BlockPos(coordinates[0], coordinates[1], coordinates[2]));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (getAnchorPos(itemStack).isEmpty()) {
            return TypedActionResult.pass(itemStack);
        }
        var position = getTeleportPosition(world, itemStack);
        if (position.isPresent()) {
            user.getItemCooldownManager().set(this, 20 * 5);
        }
        if (world.isClient) {
            MinecraftClient.getInstance().gameRenderer.showFloatingItem(itemStack);
            return position.isPresent() ? TypedActionResult.success(itemStack) : TypedActionResult.pass(itemStack);
        }
        ///
        if (position.isEmpty()) {
            user.sendMessage(Text.translatable("chat.extratar_ultimate.anchor_linker.connection_lost"));
            return TypedActionResult.pass(itemStack);
        }
        generateParticles((ServerWorld) world, (ServerPlayerEntity) user);
        if (!user.isCreative()) {
            if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
                itemStack = ItemStack.EMPTY;
            }
            itemStack.damage(1, world.random, (ServerPlayerEntity) user);
        }
        var pos = position.get();
        user.requestTeleport(pos.x, pos.y, pos.z);
        generateParticles((ServerWorld) world, (ServerPlayerEntity) user);
        return TypedActionResult.consume(itemStack);
    }

    public void generateParticles(ServerWorld world, ServerPlayerEntity pl) {
        var center = pl.getPos().add(0, 1.5, 0);
        var players = world.getPlayers().stream()
                .filter(player -> player.getPos().distanceTo(center) < 100)
                .toList();
        for (var player : players) {
            world.spawnParticles(
                    player, ParticleTypes.SOUL_FIRE_FLAME, true, center.x, center.y, center.z, 100, 0.3, 0.5, 0.3, 0);
        }
    }

    private Optional<Vec3d> getTeleportPosition(World world, ItemStack stack) {
        var anchorPos = getAnchorPos(stack);
        if (anchorPos.isEmpty()) {
            return Optional.empty();
        }
        var blockPos = anchorPos.get();
        var anchor = world.getBlockState(blockPos);
        if (anchor.getBlock() != Blocks.ANCHOR) {
            return Optional.empty();
        }
        return AnchorBlock.findRespawnPosition(EntityType.PLAYER, world, blockPos);
    }
}
