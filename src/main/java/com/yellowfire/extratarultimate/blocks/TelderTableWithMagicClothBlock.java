package com.yellowfire.extratarultimate.blocks;

import com.yellowfire.extratarultimate.ExtratarUltimate;
import com.yellowfire.extratarultimate.items.Items;
import net.minecraft.entity.ItemEntity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class TelderTableWithMagicClothBlock extends TelderTableBlock {

    public TelderTableWithMagicClothBlock(Settings settings) {
        super(settings);
    }


    private static final Identifier LOOT_TABLE_ID = ExtratarUltimate.id("food");

    public void generateFood(ServerWorld world, Vec3d center) {
        var lootTable = world.getServer().getLootManager().getTable(LOOT_TABLE_ID);
        var context = new LootContext.Builder(world)
                .random(world.random)
                .build(LootContextTypes.EMPTY);
        var stacks = lootTable.generateLoot(context);
        for (var stack : stacks) {
            var itemEntity = new ItemEntity(world, center.x, center.y, center.z, stack);
            itemEntity.setVelocity(Items.getItemVelocity(world));
            world.spawnEntity(itemEntity);
        }
    }

    public void generateParticles(ServerWorld world, Vec3d center) {
        var players = world.getPlayers().stream()
                .filter(player -> player.getPos().distanceTo(center) < 100)
                .toList();
        for (var player : players) {
            world.spawnParticles(
                    player, ParticleTypes.TOTEM_OF_UNDYING, true, center.x, center.y, center.z, 50, 0.2, 0.1, 0.2, 0);
        }
    }
}
