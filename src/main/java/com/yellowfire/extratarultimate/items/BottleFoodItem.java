package com.yellowfire.extratarultimate.items;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class BottleFoodItem extends HoneyBottleItem {
    private boolean hasGlint = false;

    public BottleFoodItem(Settings settings) {
        super(settings);
    }

    public BottleFoodItem(Settings settings, boolean hasGlint) {
        this(settings);
        this.hasGlint = hasGlint;
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return eat(this, stack, world, user, Items.GLASS_BOTTLE);
    }

    public static ItemStack eat(Item item, ItemStack stack, World world, LivingEntity user, Item defaultItem) {
        if (item.isFood())
            user.eatFood(world, stack);

        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(item));
        }
        if (stack.isEmpty()) {
            return new ItemStack(defaultItem);
        } else {
            if (user instanceof PlayerEntity playerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
                ItemStack itemStack = new ItemStack(defaultItem);
                if (!playerEntity.getInventory().insertStack(itemStack)) {
                    playerEntity.dropItem(itemStack, false);
                }
            }
            return stack;
        }
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return getDrinkSound();
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return hasGlint;
    }
}
