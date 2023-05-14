package com.yellowfire.extratarultimate.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class HelmetTypeA extends ArmorItem {
    public HelmetTypeA(ArmorMaterial material, Settings settings) {
        super(material, EquipmentSlot.HEAD, settings);
    }
}
