package com.yellowfire.extratarultimate.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class TelderArmorMaterial implements ArmorMaterial {
    public static ArmorMaterial TELDER = new TelderArmorMaterial();

    @Override
    public int getDurability(EquipmentSlot slot) {
        if (slot == EquipmentSlot.HEAD) {
            return 13 * 34;
        } else if (slot == EquipmentSlot.CHEST) {
            return 15 * 34;
        } else if (slot == EquipmentSlot.LEGS) {
            return 16 * 34;
        } else if (slot == EquipmentSlot.FEET) {
            return 11 * 34;
        }
        return 0;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        if (slot == EquipmentSlot.HEAD) {
            return 3;
        } else if (slot == EquipmentSlot.CHEST) {
            return 6;
        } else if (slot == EquipmentSlot.LEGS) {
            return 8;
        } else if (slot == EquipmentSlot.FEET) {
            return 3;
        }
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.TELDER_STEEL_INGOT);
    }

    @Override
    public String getName() {
        return "telder_steel";
    }

    @Override
    public float getToughness() {
        return 3;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.1f;
    }
}
