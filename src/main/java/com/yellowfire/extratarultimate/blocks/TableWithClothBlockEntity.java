package com.yellowfire.extratarultimate.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class TableWithClothBlockEntity extends BlockEntity implements Inventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public TableWithClothBlockEntity(BlockPos pos, BlockState state) {
        super(Blocks.TELDER_TABLE_WITH_CLOTH_ENTITY_TYPE, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        updateBlock();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return inventory.isEmpty();
    }

    public boolean hasItemStack() {
        return !isEmpty() && inventory.get(0) != ItemStack.EMPTY && !inventory.get(0).isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        var stack = Inventories.splitStack(inventory, slot, amount);
        if (inventory.get(slot).isEmpty()) {
            inventory.set(slot, ItemStack.EMPTY);
            updateBlock();
        }
        return stack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        var stack = Inventories.removeStack(inventory, slot);
        inventory.set(slot, ItemStack.EMPTY);
        updateBlock();
        return stack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        updateBlock();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }

    @Override
    public void clear() {
        inventory.set(0, ItemStack.EMPTY);
        updateBlock();
    }

    public void updateBlock() {
        if (world != null) {
            var state = world.getBlockState(pos);
            if (state.getBlock() == Blocks.TELDER_TABLE_WITH_CLOTH) {
                world.setBlockState(pos, state.with(Properties.LIT, hasItemStack()));
            }
        }
    }
}
