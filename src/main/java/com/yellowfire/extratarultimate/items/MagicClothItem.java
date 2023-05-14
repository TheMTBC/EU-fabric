package com.yellowfire.extratarultimate.items;

import com.google.common.collect.ImmutableList;
import com.yellowfire.extratarultimate.blocks.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class MagicClothItem extends ClothItem{
    public MagicClothItem(Settings settings) {
        super(settings);
    }

    @Override
    protected void useOnTable(ItemUsageContext context, BlockState state, BlockPos pos) {
        context.getWorld().setBlockState(pos, Blocks.TELDER_TABLE_WITH_MAGIC_CLOTH.getDefaultState()
                .with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED)));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
