package com.yellowfire.extratarultimate.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class Panacea extends Item {
    public Panacea(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 10;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        var st = super.finishUsing(stack, world, user);
        if (!world.isClient) {
            for (var effect : new ArrayList<>(user.getStatusEffects())) {
                if (isBadEffect(effect.getEffectType())) {
                    user.removeStatusEffect(effect.getEffectType());
                }
            }
        }
        return st;
    }

    private boolean isBadEffect(StatusEffect effect) {
        if (effect == StatusEffects.BAD_OMEN) {
            return true;
        }
        if (effect == StatusEffects.BLINDNESS) {
            return true;
        }
        if (effect == StatusEffects.HUNGER) {
            return true;
        }
        if (effect == StatusEffects.NAUSEA) {
            return true;
        }
        if (effect == StatusEffects.POISON) {
            return true;
        }
        if (effect == StatusEffects.WEAKNESS) {
            return true;
        }
        if (effect == StatusEffects.SLOWNESS) {
            return true;
        }
        return effect == StatusEffects.WITHER;
    }
}
